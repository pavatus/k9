package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.container.K9InventoryContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class K9Containers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, K9Mod.MOD_ID);
    public static final RegistryObject<MenuType<K9InventoryContainer>> K9_CONTAINER = CONTAINERS.register("k9_container", () -> new MenuType<>(K9InventoryContainer::getClientMenu));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
