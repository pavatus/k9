package com.mdt.k9mod.common.entities;

import com.mdt.k9mod.common.items.BoneItem;
import com.mdt.k9mod.common.container.K9InventoryContainer;
import com.mdt.k9mod.core.init.K9modItems;
import com.mdt.k9mod.core.init.K9modSounds;
import com.mdt.k9mod.util.NBTUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class K9Entity extends Wolf {
    public final ItemStackHandler inventory = new ItemStackHandler(27);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(K9Entity.class, EntityDataSerializers.BYTE);
    private int numeral = 0;
    public int battery = 100;
    private final Item TAME_ITEM = Items.IRON_INGOT;
    private int hopperCountdown,hopperItem = 0;
    private double hurtCount = 0;

    public K9Entity(EntityType<K9Entity> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public static List<ItemEntity> getNearbyItems(K9Entity entity, double radius) {
        List<ItemEntity> list = entity.level.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(radius));
        return list;
    }

    private void moveTowardsItemAndPickup() {
        // check for nearby items and pick them up
        List<ItemEntity> entities = getNearbyItems(this, 1.5);
        for (ItemEntity entity : entities) {
            if (entity.isOnGround()) {
                // stop if on a hopper
                if (this.level.getBlockState(this.getOnPos()).getBlock() instanceof HopperBlock) {
                    break;
                }

                ItemHandlerHelper.insertItemStacked(this.inventory, entity.getItem(), false);
                entity.kill();
            }
        }
        // move towards the first item in the list thats on the ground
        List<ItemEntity> nearbyItems = getNearbyItems(this, 4);
        if (nearbyItems.size() != 0) {
            for (ItemEntity nearbyItem : nearbyItems) {
                // stop if on a hopper
                if (this.level.getBlockState(this.getOnPos()).getBlock() instanceof HopperBlock) {
                    break;
                }
                if (nearbyItem.isOnGround()) {
                    this.getNavigation().moveTo(nearbyItem.getX(), nearbyItem.getY(), nearbyItem.getZ(), Attributes.MOVEMENT_SPEED.getDefaultValue());
                    break;
                }
            }
        }
    }

    private void checkForHopperAndDrop() {
        // check if hopper below
        if (this.level.getBlockState(this.getOnPos()).getBlock() instanceof HopperBlock) {
            if (this.hopperItem >= this.inventory.getSlots()) {
                this.hopperItem = 0;
            }

            this.setOrderedToSit(true);

            // goes through every item in the inventory and drops it if the countdowns okay
            if (!this.isOnCooldown()) {

                Containers.dropItemStack(this.level, this.getOnPos().getX(), this.getOnPos().getY() + 1, this.getOnPos().getZ(), this.inventory.getStackInSlot(this.hopperItem));

                this.hopperItem++;
                this.hopperCountdown = 1*20; // * 20 times by the length in seconds
            }

            this.hopperCountdown--;
        }
    }

    private boolean isOnCooldown() {
        return this.hopperCountdown > 0;
    }

    @Override
    public void tick() {
        if (this.isTame() && !this.isNoAi()) {
            this.checkForHopperAndDrop();
            this.moveTowardsItemAndPickup();
            // @TODO Move towards blocks that are in its target list
        }
//        if (this.isNoAi()) {
//            //this.setHealth(getMaxHealth());
//            if(numeral >= 200) {
//                numeral = 0;
//            } else {
//                ++numeral;
//            }
//            if(numeral >= 100 && numeral <= 125) {
//                level.playSound(null, this.getOnPos(), SoundEvents.NOTE_BLOCK_BASS.get(), SoundSource.MASTER, 1F, 0.25F);
//            }
//            if(numeral >= 100 && numeral < 150) {
//                level.addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0.05,0);
//            }
//            level.addParticle(ParticleTypes.LARGE_SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0.1, 0);
//        } else {
//            numeral = 0;
//        }
//        int dying = 0;
        if(this.isInWater()) {
            level.addParticle(ParticleTypes.LARGE_SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0.1, 0);
            level.addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0.05,0);
        }
        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putInt("deadTime", this.numeral);
        nbt.putDouble("hurtCount", this.hurtCount);
        nbt.putInt("Battery", this.battery);
        NBTUtil.inventoryToNBT(this.inventory, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.numeral = nbt.getInt("deadTime");
        this.hurtCount = nbt.getDouble("hurtCount");
        this.battery = nbt.getInt("batteryLevel");
        NBTUtil.inventoryFromNBT(this.inventory, nbt);
    }

    public static AttributeSupplier.Builder createK9Attributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return K9modSounds.K9_GROWL.get();
        } else if (this.random.nextInt(3) == 0) {
            return this.isTame() && this.getHealth() < 10.0F ? K9modSounds.K9_WHINE.get() : K9modSounds.K9_PANT.get();
        } else {
            return K9modSounds.K9_AMBIENT.get();
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.IRON_GOLEM_HURT;//K9modSounds.K9_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return /*SoundEvents.IRON_GOLEM_DEATH;*/K9modSounds.K9_DEATH.get();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
//        this.goalSelector.addGoal(1, new Wolf.WolfPanicGoal(1.5D));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
//        this.goalSelector.addGoal(3, new Wolf.WolfAvoidEntityGoal<>(this, Llama.class, 24.0F, 1.5D, 1.5D));
//        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return super.isFood(new ItemStack(Items.REDSTONE));
    }

    @Override
    protected void onChangedBlock(BlockPos p_184594_1_) {
        super.onChangedBlock(p_184594_1_);
//        if (this.random.nextInt(9) == 0) { // 0.1% chance of it draining battery
//            if (this.battery > 0) {
//                this.battery--;
//            }
//        }
    }

    private void tameAndStop(Player pPlayer) {
        this.tame(pPlayer);
        this.navigation.stop();
        this.setTarget(null);
        this.setOrderedToSit(true);
    }

    // Oh god now this part of the code. Mercy me Lord. :pray:
    @Override
    public boolean hurt(DamageSource source, float damage) {
        // If we have brain then
        // Add the damage to the hurtcount
        // Play hurt sound
        if (!this.isNoAi()) {
            hurtCount += damage;
            level.playSound(null, this.getOnPos(), K9modSounds.K9_HURT.get(), SoundSource.MASTER, 5, 1);
            damage = 0;
        }

        // If the hurtcount is over 35 then
        // Do death sound
        // Disable AI
        // Sit down
        if (hurtCount >= 35) {
            level.playSound(null, this.getOnPos(), K9modSounds.K9_DEATH.get(), SoundSource.MASTER, 6, 1F);
            this.setNoAi(true);
            this.setInSittingPose(false);
        }

        if (!(source.getEntity() instanceof ServerPlayer)) { return super.hurt(source, damage); }

        ServerPlayer pPlayer = (ServerPlayer) source.getEntity();
        ItemStack itemstack = pPlayer.getMainHandItem();
        Item item = itemstack.getItem();

        if (!(item == K9modItems.K9_WRENCH.get())) { return super.hurt(source, damage); }
        // From this point on we know the items a wrench ^

        // If we dont have a brain then restart
        if (this.isNoAi()) {
            if(!pPlayer.getAbilities().instabuild) {
                itemstack.setDamageValue(1);
            }

            restartHeal(pPlayer,this);
        }
        // if we do then heal normally
        else {
            itemstack.hurtAndBreak(1, pPlayer, player1 -> player1.broadcastBreakEvent(pPlayer.getUsedItemHand()));

            heal(pPlayer, this);
        }

        return super.hurt(source,damage);
    }

    public void heal(Player pPlayer, K9Entity k9) {
        k9.setHealth(20.0F);
        pPlayer.sendSystemMessage(Component.translatable("K9 is back to full health!")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA).withItalic(true)));
        k9.hurtCount = 0;
        level.playSound(null, this.getOnPos(), SoundEvents.ANVIL_HIT, SoundSource.MASTER, 4, 1);
    }

    public void restartHeal(Player pPlayer, K9Entity k9) {
        k9.setHealth(20.0F);
        k9.hurtCount = 0;
        k9.setNoAi(false);
        k9.level.playSound(null, this.getOnPos(), K9modSounds.K9_RESTART.get(), SoundSource.MASTER, 4, 1);

        pPlayer.sendSystemMessage(Component.translatable("K9 is back to full health!")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA).withItalic(true)));
    }

    public static void sendBatteryChat(Player pPlayer, K9Entity k9) {
        pPlayer.sendSystemMessage(Component.translatable("Remaining Battery: " + k9.battery + "%")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN).withItalic(true).withBold(true)));
    }

    public static void sendNotOwnerChat(Player pPlayer) {
        pPlayer.sendSystemMessage(Component.translatable("This K9 is not yours!")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED).withItalic(true)));
    }

    public static void openInventoryMenu(Player pPlayer, K9Entity k9) {
        if (k9.level.isClientSide) {return;}

        final MenuProvider container = new SimpleMenuProvider(K9InventoryContainer.getServerContainer(k9,k9.battery), Component.translatable("screen.k9mod.k9_gui"));
        NetworkHooks.openScreen((ServerPlayer) pPlayer, container);
        k9.level.playSound(null, k9.getOnPos(), K9modSounds.K9_MASTER.get(), SoundSource.MASTER, 5, 1);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();

        // I dont even know
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || item == Items.IRON_INGOT && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        }
        // We know that everything past this point is on the server as we returned in ^ that bit of code above

        // mentally prepare yourself if you're gonna work on this bit of the code - Bo
        // Don't worry, I annotated and re-did some of the Loqor disease.
        if (this.isTame()) {

            // if shift right-clicking and we have a brain
            if (pPlayer.isCrouching() && !this.isNoAi()) {
                if(this.getOwner() == pPlayer) {
                    // Link the bone if holding metal bone
                    if (item == K9modItems.K9_BONE.get()) {
                        ((BoneItem) item).linkBone(this, pPlayer);
                    }
                    // If holding wrench then send battery chat
                    else if (item == K9modItems.K9_WRENCH.get()) {
                        sendBatteryChat(pPlayer,this);
                    }
                }
                // If this K9 is not owned by that player we send an error message
                else {
                    sendNotOwnerChat(pPlayer);
                }
                // If the player's hand is empty, we open the inventory menu
                if (item == Items.AIR) {
                    openInventoryMenu(pPlayer,this);
                }
            }
            // If the item is food AND ( the health isnt full OR the hurt count (whatever TF that is) is greater than 0) we heal the K9
            if (this.isFood(itemstack) && (this.getHealth() < this.getMaxHealth() || this.hurtCount > 0) ) {
                // Only shrink in non-creative
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.heal((float)item.getFoodProperties().getNutrition());
                return InteractionResult.SUCCESS;
            }

            // Dye the K9 if the item is a dye
            if (item instanceof DyeItem) {
                DyeColor dyecolor = ((DyeItem) item).getDyeColor();
                if (dyecolor != this.getCollarColor()) {
                    this.setCollarColor(dyecolor);
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        //System.out.println(this.getCollarColor());
                    }

                    return InteractionResult.SUCCESS;
                }
            }
            // Otherwise do normal things
            else {
                InteractionResult interactionResult = super.mobInteract(pPlayer, pHand);
                if ((!InteractionResult.CONSUME.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                }

                return interactionResult;
            }
        }
        // If the item being held is the tame item and we are not angry and we are not tamed.
        if (item == TAME_ITEM && !this.isAngry() && !this.isTame()) {
            // Only shrink if not in creative
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            // Random 1/3 chance to tame the K9
            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.tameAndStop(pPlayer);
                this.level.broadcastEntityEvent(this, (byte)7);
            } else {
                this.level.broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }
}