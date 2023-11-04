package mdt.falloutmod.core.items;

import mdt.falloutmod.core.FMCSounds;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class StimpakItem extends Item {

    public StimpakItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if ( !world.isClient() ) {
            world.playSound(null, user.getBlockPos(), FMCSounds.STIMPAK_USE, net.minecraft.sound.SoundCategory.PLAYERS, 1.0F, 1.0F);
            user.heal(3f);
            user.addStatusEffect(new StatusEffectInstance(Objects.requireNonNull(Registries.STATUS_EFFECT.get(new Identifier("minecraft:regeneration"))), 100, 1));
            user.getItemCooldownManager().set(this, 50);
            user.getStackInHand(hand).decrement(1);
        }
        return super.use(world, user, hand);
    }
}
