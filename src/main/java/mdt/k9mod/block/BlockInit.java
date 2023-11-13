package mdt.k9mod.block;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import mdt.k9mod.item.ItemGroupInit;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockInit implements BlockRegistryContainer {

    public static final Block K9_CRATE = new K9CrateBlock(FabricBlockSettings.create().nonOpaque());

    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new OwoItemSettings().group(ItemGroupInit.K9_GROUP));
    }

}
