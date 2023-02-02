package com.mdt.k9mod.common.entities;

import com.mdt.k9mod.container.K9InventoryContainer;
import com.mdt.k9mod.core.init.K9modSounds;
import net.minecraft.command.impl.ForceLoadCommand;
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
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class K9Entity extends WolfEntity {

    public K9Entity(EntityType<K9Entity> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    private INamedContainerProvider createContainerProvider() {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.k9mod.inventory");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new K9InventoryContainer(ContainerType.GENERIC_9x3, i, playerInventory,3);
            }
        };
    }

    @Override
    public AttributeModifierManager getAttributes() {
        return new AttributeModifierManager(createAttributes().build());
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
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
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return super.isFood(new ItemStack(Items.REDSTONE));
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

                if (pPlayer.isCrouching()) {
                    INamedContainerProvider containerProvider = createContainerProvider();
                    NetworkHooks.openGui((ServerPlayerEntity) pPlayer,containerProvider);
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
}
