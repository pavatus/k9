package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.blocks.K9CrateBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class K9modBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, K9Mod.MOD_ID);

    public static final RegistryObject<K9CrateBlock> K9_CRATE = registerBlock("k9_crate", () -> new K9CrateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), ItemGroup.TAB_REDSTONE);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, ItemGroup tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, ItemGroup tab) {
        K9modItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
