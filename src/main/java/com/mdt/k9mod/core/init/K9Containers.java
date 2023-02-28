package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.container.K9InventoryContainer;
import net.minecraft.inventory.container.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class K9Containers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, K9Mod.MOD_ID);
    public static final RegistryObject<ContainerType<K9InventoryContainer>> K9_CONTAINER = CONTAINERS.register("k9_container", () -> IForgeContainerType.create((((windowId, inv, data) -> new K9InventoryContainer(windowId,inv)))));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
