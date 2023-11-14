package mdt.k9mod.entity;

import io.wispforest.owo.util.ImplementedInventory;
import mdt.k9mod.client.inventory.IK9Inventory;
import mdt.k9mod.client.screen.screen_handler.K9ScreenHandler;
import mdt.k9mod.client.screen.screen_handler.ScreenHandlerInit;
import mdt.k9mod.item.ItemInit;
import mdt.k9mod.sounds.SoundsInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class K9Entity extends TameableEntity implements Angerable, NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY);
    private static final TrackedData<Integer> COLLAR_COLOR = DataTracker.registerData(K9Entity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> BATTERY_LEVEL = DataTracker.registerData(K9Entity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(K9Entity.class, TrackedDataHandlerRegistry.INTEGER);
    private int hopperCountdown,hopperItem = 0;
    private int numeral = 0;

    public static final Predicate<LivingEntity> FOLLOW_TAMED_PREDICATE = entity -> {
        EntityType<?> entityType = entity.getType();
        return entityType == EntityType.SHEEP || entityType == EntityType.RABBIT || entityType == EntityType.FOX;
    };
    private static final float WILD_MAX_HEALTH = 8.0f;
    private static final float TAMED_MAX_HEALTH = 20.0f;
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    @Nullable
    private UUID angryAt;

    public K9Entity(EntityType<? extends K9Entity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false);
        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1.0f);
    }

    private final PropertyDelegate properties = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return getBatteryLevel();
        }

        @Override
        public void set(int index, int value) {
            setBattery(value);
        }

        @Override
        public int size() {
            return 1;
        }
    };

    private boolean isOnCooldown() {
        return this.hopperCountdown > 0;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        //this.goalSelector.add(1, new WolfEntity.WolfEscapeDangerGoal(1.5));
        this.goalSelector.add(2, new SitGoal(this));
        //this.goalSelector.add(3, new WolfEntity.AvoidLlamaGoal<LlamaEntity>(this, LlamaEntity.class, 24.0f, 1.5, 1.5));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4f));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 10.0f, 2.0f, false));
        this.goalSelector.add(7, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        //this.goalSelector.add(9, new WolfBegGoal(this, 8.0f));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
        this.targetSelector.add(4, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(5, new UntamedActiveTargetGoal<AnimalEntity>(this, AnimalEntity.class, false, FOLLOW_TAMED_PREDICATE));
        this.targetSelector.add(6, new UntamedActiveTargetGoal<TurtleEntity>(this, TurtleEntity.class, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
        this.targetSelector.add(7, new ActiveTargetGoal<AbstractSkeletonEntity>((MobEntity)this, AbstractSkeletonEntity.class, false));
        this.targetSelector.add(8, new UniversalAngerGoal<K9Entity>(this, true));
    }

    public static DefaultAttributeContainer.Builder createK9Attributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f).add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLLAR_COLOR, DyeColor.RED.getId());
        this.dataTracker.startTracking(BATTERY_LEVEL, 100);
        this.dataTracker.startTracking(ANGER_TIME, 0);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15f, 1.0f);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        Inventories.writeNbt(nbt, items);
        nbt.putInt("deadTime", this.numeral);
        //nbt.putInt("hurtCount", this.hurtCount);
        nbt.putInt("battery", this.getBatteryLevel());
        nbt.putByte("CollarColor", (byte)this.getCollarColor().getId());
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        Inventories.readNbt(nbt, items);
        if(nbt.contains("deadTime")) {
            this.numeral = nbt.getInt("deadTime");
        }
        if (nbt.contains("CollarColor", NbtElement.NUMBER_TYPE)) {
            this.setCollarColor(DyeColor.byId(nbt.getInt("CollarColor")));
        }
        if (nbt.contains("battery")) {
            this.setBattery(nbt.getInt("battery"));
        }
        this.readAngerFromNbt(this.getWorld(), nbt);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.hasAngerTime()) {
            return SoundsInit.K9_DEFENSE;
        }
        if (this.random.nextInt(3) == 0) {
            if (this.isTamed() && this.getHealth() < 10.0f) {
                return SoundsInit.K9_MASTER;
            }
            return SoundEvents.ENTITY_WOLF_PANT;
        }
        return SoundEvents.ENTITY_WOLF_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsInit.K9_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsInit.K9_DIE;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.getWorld().isClient) {
            if(this.getBatteryLevel() <= 0) {
                this.setAiDisabled(true);
            } else {
                this.setAiDisabled(false);
            }
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
            if(!this.isSitting())
                if(this.random.nextInt(199) == 0) // 1 in 200 chance of detracting battery percentage
                    if(this.getBatteryLevel() > 0)
                        this.setBattery(this.getBatteryLevel() - 1);
        }
    }

    public static List<ItemEntity> getNearbyItems(K9Entity entity,double radius) {
        return entity.getWorld().getEntitiesByClass(ItemEntity.class, entity.getBoundingBox().expand(radius), ((Predicate<ItemEntity>) ItemEntity::cannotPickup).negate());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isAlive()) {
            return;
        }

        if (!this.getWorld().isClient) {
            // check if hopper below
            BlockPos bpos = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
            Inventory inventory = this;
            if (this.getWorld().getBlockState(bpos).getBlock() != null) {
                this.getNavigation().startMovingTo(bpos.getX(), bpos.getY(), bpos.getZ(), EntityAttributes.GENERIC_MOVEMENT_SPEED.getDefaultValue());
                // goes through every item in the inventory and drops it if the countdowns okay
                if (!isOnCooldown()) {
                    if (this.getWorld().getBlockEntity(bpos) instanceof HopperBlockEntity hopperBlockEntity && this.isSitting()) {
                        if (this.hopperItem >= inventory.size()) {
                            this.hopperItem = 0;
                        }
                        this.dropStack(inventory.getStack(this.hopperItem), 2);
                        this.hopperItem++;
                        this.hopperCountdown = 20; // * 20 times by the length in seconds
                    }
                    this.hopperCountdown--;
                }
            }

            // check for nearby items and pick them up
            List<ItemEntity> entities = getNearbyItems(this, 1.5D);
            for (ItemEntity entity : entities) {
                if (entity.isOnGround()) {
                    // stop if on a hopper
                    if (this.getWorld().getBlockState(bpos).getBlock() instanceof HopperBlock) {
                        break;
                    }
                    if(inventory.size() == 27) {
                        for (int i = 0; i < inventory.size(); i++) {
                            if (inventory.getStack(i).isEmpty() || this.areItemsEqual(inventory.getStack(i), entity.getStack())) {
                                inventory.setStack(i, entity.getStack());
                                //this.getItems().add(inventory.getStack(i));
                                entity.kill();
                                break;
                            }
                        }
                    }
                    //for(int i = 0; i < this.getItems().size(); i++) {
                    //    if(this.getItems().get(i).isEmpty() || this.areItemsEqual(this.getItems().get(i), entity.getStack().copy())) {
                    //        this.items.add(i, entity.getStack().copy());
                    //        entity.kill();
                    //    }
                    //}
                    //System.out.println(this.items.size());
                }
            }
            // move towards the first item in the list thats on the ground
            List<ItemEntity> nearbyItems = getNearbyItems(this, 4);
            if (!nearbyItems.isEmpty()) {
                for (ItemEntity nearbyItem : nearbyItems) {
                    // stop if on a hopper
                    if (this.getWorld().getBlockState(bpos).getBlock() instanceof HopperBlock) {
                        break;
                    }
                    if (nearbyItem.isOnGround()) {
                        this.getNavigation().startMovingTo(nearbyItem.getX(), nearbyItem.getY(), nearbyItem.getZ(), EntityAttributes.GENERIC_MOVEMENT_SPEED.getDefaultValue());
                        break;
                    }
                }
            }
            // @TODO Move towards blocks that are in its target list
        }
        if (this.isAiDisabled()) {
            if (this.numeral >= 200) {
                this.numeral = 0;
            } else {
                ++this.numeral;
            }
            if (this.numeral >= 100 && this.numeral <= 125) {
                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), SoundCategory.AMBIENT, 1F, 0.25F);
            }
            if (this.numeral >= 100 && this.numeral < 150) {
                this.getWorld().addParticle(ParticleTypes.SMALL_FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0.05, 0);
            }
            this.getWorld().addParticle(ParticleTypes.LARGE_SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0.1, 0);
        } else {
            this.numeral = 0;
        }
        if (this.isTouchingWater()) {
            this.getWorld().addParticle(ParticleTypes.LARGE_SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0.1, 0);
            this.getWorld().addParticle(ParticleTypes.SMALL_FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0.05, 0);
        }
    }

    private boolean areItemsEqual(ItemStack stack, ItemStack stack2) {
        return ItemStack.areItemsEqual(stack, stack2) && !this.areDifferentPotions(stack, stack2);
    }

    private boolean areDifferentPotions(ItemStack stack, ItemStack stack2) {
        boolean bl2;
        boolean bl;
        NbtCompound nbtCompound = stack.getNbt();
        boolean bl3 = bl = nbtCompound != null && nbtCompound.contains("Potion");
        if (!bl) {
            return false;
        }
        NbtCompound nbtCompound2 = stack2.getNbt();
        boolean bl4 = bl2 = nbtCompound2 != null && nbtCompound2.contains("Potion");
        if (!bl2) {
            return true;
        }
        NbtElement nbtElement = nbtCompound.get("Potion");
        NbtElement nbtElement2 = nbtCompound2.get("Potion");
        return nbtElement != null && nbtElement2 != null && !nbtElement.equals(nbtElement2);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        ItemScatterer.spawn(this.getWorld(), this.getBlockPos(), this);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.8f;
    }

    @Override
    public int getMaxLookPitchChange() {
        if (this.isInSittingPose()) {
            return 20;
        }
        return super.getMaxLookPitchChange();
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        Entity entity = source.getAttacker();
        if (!this.getWorld().isClient) {
            this.setSitting(false);
        }
        if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof PersistentProjectileEntity)) {
            amount = (amount + 1.0f) / 2.0f;
        }
        if(this.isTamed()) {
            if ((this.getHealth() - amount < 20.0f)) {
                this.setAiDisabled(this.getBatteryLevel() <= 0);
                this.getWorld().playSound(null, this.getBlockPos(), SoundsInit.K9_DIE, SoundCategory.AMBIENT, 7.0f, 1.0f);
                //this.remove(RemovalReason.KILLED);
                //return super.damage(source, 0.0f);
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(this.getDamageSources().mobAttack(this), (int)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
        if (bl) {
            this.applyDamageEffects(this, target);
        }
        return bl;
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            this.setHealth(20.0f);
        } else {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(16.0);
        }
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(5.0);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        System.out.println(this.getOwner());
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.getWorld().isClient) {
            boolean bl = this.isOwner(player) || this.isTamed() || itemStack.isOf(Items.IRON_INGOT) && !this.isTamed() && !this.hasAngerTime();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        }
        if (this.isTamed()) {
            ActionResult actionResult;
            if (itemStack == ItemInit.K9_LITHIUM_CELL.getDefaultStack() && this.getHealth() < this.getMaxHealth()) {
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                this.heal(1);
                return ActionResult.SUCCESS;
            }
            if (item instanceof DyeItem) {
                DyeItem dyeItem = (DyeItem)item;
                if (this.isOwner(player)) {
                    DyeColor dyeColor = dyeItem.getColor();
                    if (dyeColor == this.getCollarColor()) return super.interactMob(player, hand);
                    this.setCollarColor(dyeColor);
                    if (player.getAbilities().creativeMode) return ActionResult.SUCCESS;
                    itemStack.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }
            if (item == ItemInit.K9_WRENCH && !player.isSneaking()) {
                if(!this.isAiDisabled()) {
                    SoundEvent soundEvent = this.getHealth() == this.getMaxHealth() ? SoundEvents.BLOCK_ANVIL_USE : SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE.value();
                    float necessaryPitch = soundEvent == SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE.value() ? 0.05F * this.getHealth() : 1F;
                    this.getWorld().playSound(null, this.getBlockPos(), soundEvent, SoundCategory.NEUTRAL, 7, necessaryPitch);
                } else {
                    this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.NEUTRAL, 7, 1);
                }
                if (!player.getAbilities().creativeMode && this.getHealth() < this.getMaxHealth())
                    itemStack.setDamage(itemStack.getDamage() + 1);
                this.heal(this.getMaxHealth());
                this.setBattery(100);
                return ActionResult.SUCCESS;
            }
            if(itemStack.isEmpty() && player.isSneaking()) {
                    NamedScreenHandlerFactory sHF = new SimpleNamedScreenHandlerFactory(
                            (syncId, inventory, playerx) -> this.createMenu(
                                    syncId, inventory, player), Text.of("K9"));
                        player.openHandledScreen(sHF);
                return ActionResult.SUCCESS;
            }
            if ((actionResult = super.interactMob(player, hand)).isAccepted() && !this.isBaby() || !this.isOwner(player)) return actionResult;
            this.setSitting(!this.isSitting());
            this.jumping = false;
            this.navigation.stop();
            this.setTarget(null);
            return ActionResult.SUCCESS;
        }
        if (!itemStack.isOf(Items.IRON_INGOT) || this.hasAngerTime()) return super.interactMob(player, hand);
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        if (this.random.nextInt(3) == 0) {
            this.setOwner(player);
            this.navigation.stop();
            this.setTarget(null);
            this.setSitting(true);
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
            return ActionResult.SUCCESS;
        } else {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
        }
        return ActionResult.SUCCESS;
    }

    public float getTailAngle() {
        if (this.hasAngerTime()) {
            return 1.5393804f;
        }
        if (this.isTamed()) {
            return (0.55f - (this.getMaxHealth() - this.getHealth()) * 0.02f) * (float)Math.PI;
        }
        return 0.62831855f;
    }

    @Override
    public int getLimitPerChunk() {
        return 8;
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Override
    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.dataTracker.get(COLLAR_COLOR));
    }

    public void setCollarColor(DyeColor color) {
        this.dataTracker.set(COLLAR_COLOR, color.getId());
    }

    public int getBatteryLevel() {
        return this.dataTracker.get(BATTERY_LEVEL);
    }

    public void setBattery(int battery) {
        this.dataTracker.set(BATTERY_LEVEL, battery);
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        return false;
    }

    @Override
    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (target instanceof CreeperEntity || target instanceof GhastEntity) {
            return false;
        }
        if (target instanceof K9Entity k9Entity) {
            return !k9Entity.isTamed() || k9Entity.getOwner() != owner;
        }
        if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
            return false;
        }
        if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
            return false;
        }
        return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return !this.hasAngerTime() && super.canBeLeashedBy(player);
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0, 0.6f * this.getStandingEyeHeight(), this.getWidth() * 0.4f);
    }

    @Override
    protected Vector3f getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        return new Vector3f(0.0f, dimensions.height - 0.03125f * scaleFactor, -0.0625f * scaleFactor);
    }

    public static boolean canSpawn(EntityType<K9Entity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(BlockTags.WOLVES_SPAWNABLE_ON) && K9Entity.isLightLevelValidForNaturalSpawn(world, pos);
    }

    @Override
    @Nullable
    public K9Entity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        UUID uUID;
        K9Entity k9Entity = EntityInit.K9_ENTITY_TYPE.create(serverWorld);
        if (k9Entity != null && (uUID = this.getOwnerUuid()) != null) {
            k9Entity.setOwnerUuid(uUID);
            k9Entity.setTamed(true);
        }
        return k9Entity;
    }

    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        //K9ScreenHandler k9ScreenHandler = new K9ScreenHandler(ScreenHandlerInit.K9_INVENTORY_HANDLER, syncId, playerInventory, this, 3, properties);
        //K9mod.K9_NET_CHANNEL.serverHandle(this.getCommandSource().getWorld(), this.getBlockPos()).send(new PacketInit.K9Battery(this.getBatteryLevel(), this, new Identifier(K9mod.MOD_ID, "." + this.getUuid())));
        return new K9ScreenHandler(ScreenHandlerInit.K9_INVENTORY_HANDLER, syncId, playerInventory, this, properties);
    }

    class WolfEscapeDangerGoal
            extends EscapeDangerGoal {
        public WolfEscapeDangerGoal(double speed) {
            super(K9Entity.this, speed);
        }

        @Override
        protected boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire();
        }
    }
}