package com.mdt.k9mod.common.items;

import com.mdt.k9mod.common.entities.K9Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BoneItem extends Item {
    public BoneItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);

        if (this.getLinkedEntityID(p_77624_1_) != null) {
            // if theres a linked k9 entity, set the tooltip to a golden text of its UUID
            p_77624_3_.add(new TranslationTextComponent("K9 ID " + this.getLinkedEntityID(p_77624_1_)).setStyle(Style.EMPTY.withItalic(true).withColor(TextFormatting.GOLD)));
        }

    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        BlockPos pos = context.getClickedPos();
        ItemStack itemStack = context.getItemInHand();
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        if (!world.isClientSide) {
            if (getLinkedEntity(itemStack, (ServerWorld) world, context.getPlayer()) != null) {
                K9Entity entity = getLinkedEntity(itemStack, (ServerWorld) world, context.getPlayer());
                if (context.getPlayer().isCrouching()) {
                    entity.moveTo(pos.getX(),pos.getY()+1,pos.getZ());
                    itemStack.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(context.getHand()));
                } else {
                    entity.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 1);
                }
                world.playSound(null,pos, SoundEvents.NOTE_BLOCK_BIT, SoundCategory.MASTER,1f,0.4f);
                return ActionResultType.SUCCESS;
            }
        }
        return super.useOn(context);
    }

    // Return the entity that this is linked to
    public K9Entity getLinkedEntity(ItemStack itemStack, ServerWorld world, PlayerEntity player) {
        UUID linked_id = getLinkedEntityID(itemStack);
        if (linked_id == null) {return null;}
        Entity entity = world.getEntity(linked_id);
        return (K9Entity) entity;
    }

    // Returns the linked k9s id
    public UUID getLinkedEntityID(ItemStack itemStack) {
        CompoundNBT tag = itemStack.getOrCreateTag();
        if (tag.contains("parent_k9")) {
            return tag.getUUID("parent_k9");
        }
        return null;
    }

    // Sets the linked k9 id
    public void linkBone(K9Entity entity, PlayerEntity player) {
        CompoundNBT tag = player.getMainHandItem().getOrCreateTag();

        tag.putUUID("parent_k9",entity.getUUID());
    }
}
