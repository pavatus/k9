package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.items.BoneItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class K9modItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, K9Mod.MOD_ID);

    public static final RegistryObject<Item> K9_WRENCH = ITEMS.register(
            "k9_wrench", () -> new Item(new Item.Properties().defaultDurability(16).tab(K9Mod.K9_TAB)));
    public static final RegistryObject<Item> K9_BONE = ITEMS.register(
            "k9_bone", () -> new BoneItem(new Item.Properties().defaultDurability(8).tab(K9Mod.K9_TAB)));
}
