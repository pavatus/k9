package com.mdt.k9mod.common.items;

import com.mdt.k9mod.common.entities.K9Entity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BoneItem extends Item {
    public BoneItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);

        p_77624_3_.add(Component.translatable("Override K9 Navigation Systems with a Tasty Treat!").withStyle(ChatFormatting.GREEN));

        if (this.getLinkedEntityID(p_77624_1_) != null) {
            // if theres a linked k9 entity, set the tooltip to a golden text of its UUID
            p_77624_3_.add(Component.translatable("K9 ID: ").setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GOLD)));
            p_77624_3_.add(Component.translatable("" + this.getLinkedEntityID(p_77624_1_)).setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GOLD)));
        }

    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        ItemStack itemStack = context.getItemInHand();
        Level world = context.getLevel();
        Player player = context.getPlayer();
        if (!world.isClientSide) {
            if (getLinkedEntity(itemStack, (ServerLevel) world, context.getPlayer()) != null) {
                K9Entity entity = getLinkedEntity(itemStack, (ServerLevel) world, context.getPlayer());
                if(entity.isInSittingPose()) {
                    return InteractionResult.FAIL;
                }
                player.getCooldowns().addCooldown(this, 120); // Cooldown for X / 20 = Y seconds :)
                if (context.getPlayer().isCrouching()) {
                    // Check for whether the K9 can actually go there, and find the best spot for it.
                    if (world.getBlockState(pos.above()).getBlock() != Blocks.AIR) {
                        return InteractionResult.FAIL;
                    }
                    //world.addParticle(ParticleTypes.PORTAL,entity.getX(),entity.getY(),entity.getZ(),2,1,2);
                    entity.moveTo(pos.getX(),pos.getY()+1,pos.getZ());
                    world.playSound(null,pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.MASTER,1f,0.4f);
                    itemStack.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(context.getHand()));
                } else {
                    entity.getNavigation().moveTo(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, 1);
                    world.playSound(null,pos, SoundEvents.NOTE_BLOCK_BIT.get(), SoundSource.MASTER,1f,0.4f);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(context);
    }

    // Return the entity that this is linked to
    public K9Entity getLinkedEntity(ItemStack itemStack, ServerLevel world, Player player) {
        UUID linked_id = getLinkedEntityID(itemStack);
        if (linked_id == null) {return null;}
        Entity entity = world.getEntity(linked_id);
        return (K9Entity) entity;
    }

    // Returns the linked k9s id
    public UUID getLinkedEntityID(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (tag.contains("parent_k9")) {
            return tag.getUUID("parent_k9");
        }
        return null;
    }

    // Sets the linked k9 id
    public void linkBone(K9Entity entity, Player player) {
        CompoundTag tag = player.getMainHandItem().getOrCreateTag();

        tag.putUUID("parent_k9",entity.getUUID());
    }
}
