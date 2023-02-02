package com.mdt.k9mod.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class K9modContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, com.mdt.K9mod.K9mod.MOD_ID);

    public static final RegistryObject<ContainerType<K9InventoryContainer>> K9_INVENTORY_CONTAINER = CONTAINERS.register("k9_inventory_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
        return new K9InventoryContainer(ContainerType.GENERIC_9x3, 1, inv,3);
    })));

//    public static final RegistryObject<ContainerType<PlayerMonitorContainer>> PLAYER_MONITOR_CONTAINER = CONTAINERS.register("player_monitor_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
//        BlockPos pos = data.readBlockPos();
//        World world = inv.player.getEntityWorld();
//        return new PlayerMonitorContainer(windowId, world, pos, inv, inv.player);
//    })));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
