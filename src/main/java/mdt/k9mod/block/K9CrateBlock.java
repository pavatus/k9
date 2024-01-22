package mdt.k9mod.block;

import mdt.k9mod.entity.EntityInit;
import mdt.k9mod.entity.K9Entity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class K9CrateBlock extends Block {
    public K9CrateBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pos.getX() + Random.create().nextBetween(1, 2), pos.getY() + Random.create().nextBetween(1, 5), pos.getZ() + Random.create().nextBetween(1, 2), 0, 1, 0);
        world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pos.getX() + Random.create().nextBetween(1, 2), pos.getY() + Random.create().nextBetween(1, 5), pos.getZ() + Random.create().nextBetween(1, 2), 0, 1, 0);
        world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pos.getX() + Random.create().nextBetween(1, 2), pos.getY() + Random.create().nextBetween(1, 5), pos.getZ() + Random.create().nextBetween(1, 2), 0, 1, 0);
        world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pos.getX() + Random.create().nextBetween(1, 2), pos.getY() + Random.create().nextBetween(1, 5), pos.getZ() + Random.create().nextBetween(1, 2), 0, 1, 0);
        world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pos.getX() + Random.create().nextBetween(1, 2), pos.getY() + Random.create().nextBetween(1, 5), pos.getZ() + Random.create().nextBetween(1, 2), 0, 1, 0);
        K9Entity k9Entity = EntityInit.K9_ENTITY_TYPE.create(world);
        if(k9Entity != null) {
            world.spawnEntity(k9Entity);
            k9Entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, 0, 0);
            world.playSound(null, new BlockPos(pos.getX(), pos.getY(), pos.getZ()), SoundEvents.ITEM_TOTEM_USE, SoundCategory.BLOCKS, 7, 1);
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
