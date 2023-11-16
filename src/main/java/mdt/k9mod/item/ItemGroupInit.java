package mdt.k9mod.item;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import mdt.k9mod.K9mod;
import mdt.k9mod.block.BlockInit;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ItemGroupInit {
    public static final OwoItemGroup K9_GROUP = OwoItemGroup.builder(new Identifier(K9mod.MOD_ID,"k9_group"), () -> Icon.of(BlockInit.K9_CRATE)).build();
}
