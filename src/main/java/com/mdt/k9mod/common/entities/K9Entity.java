package com.mdt.k9mod.common.entities;

import com.mdt.k9mod.common.items.BoneItem;
import com.mdt.k9mod.container.K9InventoryContainer;
import com.mdt.k9mod.core.init.K9modItems;
import com.mdt.k9mod.core.init.K9modSounds;
import com.mdt.k9mod.util.NBTUtil;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class K9Entity extends WolfEntity {
    private final Inventory inventory = new Inventory(27);
    private static final DataParameter<Byte> DATA_ID_FLAGS = EntityDataManager.defineId(K9Entity.class, DataSerializers.BYTE);
    private int numeral = 0;
    public int battery = 100;
    private int hopperCountdown,hopperItem = 0;
    private double hurtCount = 0;

    public K9Entity(EntityType<K9Entity> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    public static List<ItemEntity> getNearbyItems(K9Entity entity,double radius) {
        List<ItemEntity> list = entity.level.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(radius));
        return list;
    }

    private boolean isOnCooldown() {
        return this.hopperCountdown > 0;
    }

    @Override
    public void tick() {
        if (battery <= 0) {
            level.playSound(null, this.getOnPos(), K9modSounds.K9_DEATH.get(), SoundCategory.MASTER, 6, 1F);
            this.setNoAi(true);
            this.setInSittingPose(false);
        }
        if (this.isTame() && !this.isNoAi()) {
            // check if hopper below
            if (this.level.getBlockState(this.getOnPos()).getBlock() instanceof HopperBlock) {
                this.getNavigation().moveTo(this.getOnPos().getX(), this.getOnPos().getY(), this.getOnPos().getZ(), Attributes.MOVEMENT_SPEED.getDefaultValue());
                // goes through every item in the inventory and drops it if the countdowns okay
                if (!isOnCooldown()) {
                    /*if(this.level.getBlockEntity(this.getOnPos()) instanceof HopperTileEntity) {
                        HopperTileEntity hopperTileEntity = (HopperTileEntity) this.level.getBlockEntity(this.getOnPos());
                    }*/
                    if (this.hopperItem >= this.inventory.getContainerSize()) {
                        this.hopperItem = 0;
                    }
                    InventoryHelper.dropItemStack(this.level, this.getOnPos().getX(), this.getOnPos().getY() + 1, this.getOnPos().getZ(), this.inventory.getItem(this.hopperItem));
                    this.hopperItem++;
                    this.hopperCountdown = 1*20; // * 20 times by the length in seconds
                }
                this.hopperCountdown--;
            }

            // check for nearby items and pick them up
            List<ItemEntity> entities = getNearbyItems(this, 1.5);
            for (ItemEntity entity : entities) {
                if (entity.isOnGround()) {
                    // stop if on a hopper
                    if (this.level.getBlockState(this.getOnPos()).getBlock() instanceof HopperBlock) {
                        break;
                    }
                    // check if inventorys full
                    if (this.inventory.getContainerSize() < 27) {
                        this.inventory.addItem(entity.getItem());
                        entity.kill();
                    }
                    this.inventory.addItem(entity.getItem());
                    entity.kill();
                }
            }
            // move towards the first item in the list thats on the ground
            List<ItemEntity> nearbyItems = getNearbyItems(this, 4);
            if (nearbyItems.size() != 0) {
                for(int i = 0; i < nearbyItems.size(); i++) {
                    // stop if on a hopper
                    if (this.level.getBlockState(this.getOnPos()).getBlock() instanceof HopperBlock) {
                        break;
                    }
                    ItemEntity checkedItem = nearbyItems.get(i);
                    if (checkedItem.isOnGround()) {
                        this.getNavigation().moveTo(checkedItem.getX(), checkedItem.getY(), checkedItem.getZ(), Attributes.MOVEMENT_SPEED.getDefaultValue());
                        break;
                    }
                }
            }
            // @TODO Move towards blocks that are in its target list
        }
        if (this.isNoAi()) {
            //this.setHealth(getMaxHealth());
            if(numeral >= 200) {
                numeral = 0;
            } else {
                ++numeral;
            }
            if(numeral >= 100 && numeral <= 125) {
                level.playSound(null, this.getOnPos(), SoundEvents.NOTE_BLOCK_BASS, SoundCategory.MASTER, 1F, 0.25F);
            }
            if(numeral >= 100 && numeral < 150) {
                level.addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0.05,0);
            }
            level.addParticle(ParticleTypes.LARGE_SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0.1, 0);
        } else {
            numeral = 0;
        }
        int dying = 0;
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putInt("deadTime", this.numeral);
        nbt.putDouble("hurtCount", this.hurtCount);
        nbt.putInt("Battery", this.battery);
        NBTUtil.inventoryToNBT(this.inventory, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);

        this.numeral = nbt.getInt("deadTime");
        this.hurtCount = nbt.getDouble("hurtCount");
        this.battery = nbt.getInt("batteryLevel");
        NBTUtil.inventoryFromNBT(this.inventory, nbt);
    }

    @Override
    public AttributeModifierManager getAttributes() {
        return new AttributeModifierManager(createAttributes().build());
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
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
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        //this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        //this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, PREY_SELECTOR));
        //this.targetSelector.addGoal(6, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return super.isFood(new ItemStack(Items.REDSTONE));
    }

    private INamedContainerProvider createContainerProvider() {
        return new INamedContainerProvider() {

            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new K9InventoryContainer(i, playerInventory, inventory);
            }

            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.k9mod.k9_gui");
            }
        };
    }

    @Override
    protected void onChangedBlock(BlockPos p_184594_1_) {
        super.onChangedBlock(p_184594_1_);
        if (this.random.nextInt(9) == 0) { // 0.1% chance of it draining battery
            if (this.battery > 0) {
                this.battery--;
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (!this.isNoAi()) {
            hurtCount += damage;
            level.playSound(null, this.getOnPos(), K9modSounds.K9_HURT.get(), SoundCategory.MASTER, 5, 1);
        }

        if (hurtCount >= 35) {
            level.playSound(null, this.getOnPos(), K9modSounds.K9_DEATH.get(), SoundCategory.MASTER, 6, 1F);
            this.setNoAi(true);
            this.setInSittingPose(false);
        }

        if (source.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity pPlayer = (ServerPlayerEntity) source.getEntity();
            ItemStack itemstack = pPlayer.getMainHandItem();
            Item item = itemstack.getItem();
            if (item == K9modItems.K9_WRENCH.get()) {
                if (this.isNoAi()) {
                    this.setHealth(20.0F);
                    if(!pPlayer.abilities.instabuild) {
                        itemstack.setDamageValue(1);
                    }
                    pPlayer.sendMessage(new TranslationTextComponent("K9 is back to full health!")
                            .setStyle(Style.EMPTY.withColor(TextFormatting.AQUA).withItalic(true)), UUID.randomUUID());
                    hurtCount = 0;
                    this.setNoAi(false);
                    level.playSound(null, this.getOnPos(), K9modSounds.K9_RESTART.get(), SoundCategory.MASTER, 4, 1);
                } else {
                    this.setHealth(20.0F);
                    if(!pPlayer.abilities.instabuild) {
                        itemstack.hurtAndBreak(1, pPlayer, player1 -> player1.broadcastBreakEvent(pPlayer.getUsedItemHand()));
                    }
                    if(itemstack.getDamageValue() > 16) {
                        pPlayer.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                        level.playSound(pPlayer, this.getOnPos(), SoundEvents.ITEM_BREAK, SoundCategory.PLAYERS, 10, 1);
                    } else {
                        if(!pPlayer.abilities.instabuild) {
                            itemstack.hurtAndBreak(1, pPlayer, player1 -> player1.broadcastBreakEvent(pPlayer.getUsedItemHand()));
                        }
                    }
                    pPlayer.sendMessage(new TranslationTextComponent("K9 is back to full health!")
                            .setStyle(Style.EMPTY.withColor(TextFormatting.AQUA).withItalic(true)), UUID.randomUUID());
                    hurtCount = 0;
                    level.playSound(null, this.getOnPos(), SoundEvents.ANVIL_HIT, SoundCategory.MASTER, 4, 1);
                }
            }
        }
        if(!this.isNoAi()) {
            return super.hurt(source,0);
        } else {
            return super.hurt(source, damage);
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || item == Items.IRON_INGOT && !this.isTame() && !this.isAngry();
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;

        } else {
            if (this.isTame()) {
                if (pPlayer.isCrouching() && !this.isNoAi()) {
                    if(this.getOwner() == pPlayer) {
                        if (item == K9modItems.K9_BONE.get()) {
                            ((BoneItem) item).linkBone(this, pPlayer);
                        } else if (item == K9modItems.K9_WRENCH.get()) {
                            pPlayer.sendMessage(new TranslationTextComponent("Remaining Battery: " + this.battery + "%")
                                    .setStyle(Style.EMPTY.withColor(TextFormatting.GREEN).withItalic(true).withBold(true)), UUID.randomUUID());
                        }
                    } else {
                        pPlayer.sendMessage(new TranslationTextComponent("This K9 is not yours!")
                                .setStyle(Style.EMPTY.withColor(TextFormatting.DARK_RED).withItalic(true)), UUID.randomUUID());
                    }
                    pPlayer.openMenu(this.createContainerProvider());
                    this.level.playSound(null, this.getOnPos(), K9modSounds.K9_MASTER.get(), SoundCategory.MASTER, 5, 1);
                    //System.out.println(item + " | " + this.isNoAi() + " | " + hurtCount + " isTame?: " + this.isTame());
                }

                if (this.isFood(itemstack) && (this.getHealth() < this.getMaxHealth() || this.hurtCount > 0) ) {
                    if (!pPlayer.abilities.instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal((float)item.getFoodProperties().getNutrition());
                    return ActionResultType.SUCCESS;
                }

                if (!(item instanceof DyeItem)) {
                    ActionResultType actionresulttype = super.mobInteract(pPlayer, pHand);
                    if ((!actionresulttype.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer)) {
                        this.setOrderedToSit(!this.isOrderedToSit());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return ActionResultType.SUCCESS;
                    }

                    return actionresulttype;
                }

                DyeColor dyecolor = ((DyeItem)item).getDyeColor();
                if (dyecolor != this.getCollarColor()) {
                    this.setCollarColor(dyecolor);
                    if (!pPlayer.abilities.instabuild) {
                        itemstack.shrink(1);
                        //System.out.println(this.getCollarColor());
                    }

                    return ActionResultType.SUCCESS;
                }

            } else if (item == Items.IRON_INGOT && !this.isAngry()) {
                if (!pPlayer.abilities.instabuild) {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    this.tame(pPlayer);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }

                return ActionResultType.SUCCESS;
            }

            return super.mobInteract(pPlayer, pHand);
        }
    }
}