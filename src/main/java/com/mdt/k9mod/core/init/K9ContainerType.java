package com.mdt.k9mod.core.init;

import com.mdt.k9mod.container.K9InventoryContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class K9ContainerType<T extends Container> extends net.minecraftforge.registries.ForgeRegistryEntry<K9ContainerType<?>> implements net.minecraftforge.common.extensions.IForgeContainerType<T> {
    public static final ContainerType<K9InventoryContainer> K9_GUI = register("k9_gui", K9InventoryContainer::new);
    private final K9ContainerType.IFactory<T> constructor;

    public K9ContainerType(K9ContainerType.IFactory<T> constructor) {
        this.constructor = constructor;
    }

    private static <T extends Container> ContainerType<T> register(String p_221505_0_, ContainerType.IFactory<T> p_221505_1_) {
        return Registry.register(Registry.MENU, p_221505_0_, new ContainerType<>(p_221505_1_));
    }

    @OnlyIn(Dist.CLIENT)
    public T create(int p_221506_1_, PlayerInventory p_221506_2_) {
        return this.constructor.create(p_221506_1_, p_221506_2_);
    }

    @Override
    public T create(int windowId, PlayerInventory playerInv, net.minecraft.network.PacketBuffer extraData) {
        if (this.constructor instanceof net.minecraftforge.fml.network.IContainerFactory) {
            return ((net.minecraftforge.fml.network.IContainerFactory<T>) this.constructor).create(windowId, playerInv, extraData);
        }
        return create(windowId, playerInv);
    }

    public interface IFactory<T extends Container> {
        @OnlyIn(Dist.CLIENT)
        T create(int p_create_1_, PlayerInventory p_create_2_);
    }
}
