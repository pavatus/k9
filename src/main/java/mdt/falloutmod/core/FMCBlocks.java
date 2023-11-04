package mdt.falloutmod.core;

import mdt.falloutmod.K9Mod;
import mdt.falloutmod.core.blocks.RadioBlock;
import mdt.falloutmod.core.blocks.StandBlock;
import mdt.falloutmod.core.blocks.TypewriterBlock;
import mdt.falloutmod.core.blocks.UraniumOreBlock;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class FMCBlocks implements BlockRegistryContainer {

    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new OwoItemSettings().group(K9Mod.K9_ITEM_GROUP));
    }
}
