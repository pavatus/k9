package com.mdt.k9mod.common.blocks;

import com.mdt.k9mod.common.entities.K9Entity;
import com.mdt.k9mod.core.init.K9modEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class K9CrateBlock extends Block {

    public K9CrateBlock(BlockBehaviour.Properties p_i48440_1_) {
        super(p_i48440_1_);
    }


    @Override
    public void onRemove(BlockState p_196243_1_, Level p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
        if(!p_196243_2_.isClientSide) {
            K9Entity k9 = new K9Entity(K9modEntities.K9.get(), p_196243_2_);
            p_196243_2_.addFreshEntity(k9);
            k9.setPos(p_196243_3_.getX() + 0.5, p_196243_3_.getY(), p_196243_3_.getZ() + 0.5);
            p_196243_2_.playSound(null, p_196243_3_, SoundEvents.TOTEM_USE, SoundSource.MASTER, 5, 1);
        }
        super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
    }
}
