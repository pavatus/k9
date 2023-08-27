package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class K9modTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, K9Mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> K9_TAB = TABS.register("k9_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tabs.k9mod.k9_tab"))
            .icon(K9modItems.K9_BONE.get()::getDefaultInstance)
            .displayItems((params,output) -> {
                    // Add all the items in registry
                    for (RegistryObject<Item> item : K9modItems.ITEMS.getEntries()) {
                        output.accept(item.get());
                    }
                    // Add all the blocks in registry
                    for (RegistryObject<Block> block : K9modBlocks.BLOCKS.getEntries()) {
                        output.accept(block.get().asItem());
                    }
            })
            .build()
    );
}
