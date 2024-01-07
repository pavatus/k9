package mdt.k9mod.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;

public class ItemInit implements ItemRegistryContainer {
    public static final Item K9_WRENCH = new Item(new OwoItemSettings().group(ItemGroupInit.K9_GROUP).maxDamage(4));
    public static final Item K9_BONE = new Item(new OwoItemSettings().group(ItemGroupInit.K9_GROUP).maxDamage(16));
    public static final K9LithiumCellItem K9_LITHIUM_CELL = new K9LithiumCellItem(new OwoItemSettings().group(ItemGroupInit.K9_GROUP).maxCount(1));
    public static final K9Wristband K9_WRISTBAND = new K9Wristband(new OwoItemSettings().group(ItemGroupInit.K9_GROUP).maxCount(1));
}
