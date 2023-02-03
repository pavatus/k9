package com.mdt.k9mod.common.entities;

import com.mdt.k9mod.container.K9InventoryContainer;
import com.mdt.k9mod.core.init.K9modItems;
import com.mdt.k9mod.core.init.K9modSounds;
import com.mdt.k9mod.screen.K9InventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.ShulkerBoxContainer;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

public class K9Entity extends WolfEntity implements IInventoryChangedListener {
    public Inventory inventory;
    private static final DataParameter<Byte> DATA_ID_FLAGS = EntityDataManager.defineId(K9Entity.class, DataSerializers.BYTE);
    private int numeral = 0;
    private double hurtCount = 0;

    public K9Entity(EntityType<K9Entity> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    @Override
    public void tick() {
        if (this.isNoAi()) {
            this.setHealth(getMaxHealth());
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
        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        ListNBT listnbt = new ListNBT();
        for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                CompoundNBT compoundnbt = new CompoundNBT();
                compoundnbt.putByte("Slot", (byte)i);
                itemstack.save(compoundnbt);
                listnbt.add(compoundnbt);
            }
        }

        p_213281_1_.putInt("deadTime", this.numeral);
        p_213281_1_.putDouble("hurtCount", this.hurtCount);
        p_213281_1_.put("Items", listnbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        ListNBT listnbt = p_70037_1_.getList("Items", 27);
        this.createInventory();

        for(int i = 0; i < listnbt.size(); ++i) {
            CompoundNBT compoundnbt = listnbt.getCompound(i);
            int j = compoundnbt.getByte("Slot") & 255;
            if (j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.of(compoundnbt));
            }
        }
        this.numeral = p_70037_1_.getInt("deadTime");
        this.hurtCount = p_70037_1_.getDouble("hurtCount");
        this.updateContainerEquipment();
    }

    protected void setFlag(int p_110208_1_, boolean p_110208_2_) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (p_110208_2_) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 | p_110208_1_));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 & ~p_110208_1_));
        }

    }
    protected void updateContainerEquipment() {
        if (!this.level.isClientSide) {
            this.setFlag(4, (!this.inventory.getItem(0).isEmpty()));
        }
    }

    protected void createInventory() {
        Inventory inventory = this.inventory;
        this.inventory = new Inventory(27);
        if (inventory != null) {
            inventory.removeListener(this);
            int i = Math.min(inventory.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = inventory.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.updateContainerEquipment();
    }
    public boolean setSlot(int p_174820_1_, ItemStack p_174820_2_) {
        if (p_174820_1_ == 499) {
            this.createInventory();
            return true;
        }
        return super.setSlot(p_174820_1_, p_174820_2_);
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
            return K9modSounds.KNINE_GROWL.get();
        } else if (this.random.nextInt(3) == 0) {
            return this.isTame() && this.getHealth() < 10.0F ? K9modSounds.KNINE_WHINE.get() : K9modSounds.KNINE_PANT.get();
        } else {
            return K9modSounds.KNINE_AMBIENT.get();
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.IRON_GOLEM_HURT;//K9modSounds.KNINE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;//K9modSounds.KNINE_DEATH.get();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
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

    private INamedContainerProvider createContainerProvider(Inventory inventory, K9Entity entity) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.k9mod.k9_gui");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new K9InventoryContainer(ContainerType.GENERIC_9x3,9, player.inventory, inventory,3,entity);
            }
        };
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if(!this.isNoAi()) {
            if(p_70097_1_.isCreativePlayer()) {
                hurtCount += 0.5D;
            } else {
                hurtCount += 0.5D;
            }
        }
        if(hurtCount == 17.5) {
            level.playSound(null, this.getOnPos(), SoundEvents.BEACON_DEACTIVATE, SoundCategory.MASTER, 6, 0.25F);
            this.setNoAi(true);
            this.setInSittingPose(false);
        }
        if(p_70097_1_.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity pPlayer = (ServerPlayerEntity) p_70097_1_.getEntity();
            ItemStack itemstack = pPlayer.getMainHandItem();
            Item item = itemstack.getItem();
            if (item == K9modItems.K9_WRENCH.get()) {
                if(this.isNoAi()) {
                    this.setHealth(20.0F);
                    itemstack.setDamageValue(2);
                    pPlayer.sendMessage(new TranslationTextComponent("K9 is back to full health!")
                            .setStyle(Style.EMPTY.withColor(TextFormatting.AQUA).withItalic(true)), UUID.randomUUID());
                    hurtCount = 0;
                    this.setNoAi(false);
                    level.playSound(null, this.getOnPos(), SoundEvents.ANVIL_USE, SoundCategory.MASTER, 4, 1);
                } else {
                    this.setHealth(20.0F);
                    if(itemstack.getDamageValue() > 16) {
                        pPlayer.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                        level.playSound(pPlayer, this.getOnPos(), SoundEvents.ITEM_BREAK, SoundCategory.PLAYERS, 10, 1);
                    } else {
                        itemstack.setDamageValue(itemstack.getDamageValue() + 1);
                    }
                    pPlayer.sendMessage(new TranslationTextComponent("K9 is back to full health!")
                            .setStyle(Style.EMPTY.withColor(TextFormatting.AQUA).withItalic(true)), UUID.randomUUID());
                    hurtCount = 0;
                    level.playSound(null, this.getOnPos(), SoundEvents.ANVIL_HIT, SoundCategory.MASTER, 4, 1);
                }
            }
        }
        p_70097_2_ = 0;
        return super.hurt(p_70097_1_, p_70097_2_);
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
                    INamedContainerProvider containerProvider = createContainerProvider(this.inventory, this);
                    NetworkHooks.openGui((ServerPlayerEntity) pPlayer, containerProvider);
                    //System.out.println(item + " | " + this.isNoAi() + " | " + hurtCount + " isTame?: " + this.isTame());
                }

                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
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
                        this.setTarget((LivingEntity) null);
                        return ActionResultType.SUCCESS;
                    }

                    return actionresulttype;
                }

                DyeColor dyecolor = ((DyeItem)item).getDyeColor();
                if (dyecolor != this.getCollarColor()) {
                    this.setCollarColor(dyecolor);
                    if (!pPlayer.abilities.instabuild) {
                        itemstack.shrink(1);
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
                    this.setTarget((LivingEntity)null);
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

    @Override
    public void containerChanged(IInventory p_76316_1_) {

    }
}