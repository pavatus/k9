package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class K9modItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, K9Mod.MOD_ID);

    public static final RegistryObject<Item> K9_WRENCH = ITEMS.register(
            "k9_wrench", () -> new Item(new Item.Properties().defaultDurability(16).tab(ItemGroup.TAB_REDSTONE)));
}
