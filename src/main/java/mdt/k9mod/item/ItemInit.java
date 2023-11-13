package mdt.k9mod.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.item.Item;

public class ItemInit implements ItemRegistryContainer {
    public static final Item WRENCH = new Item(new OwoItemSettings().group(ItemGroupInit.K9_GROUP).maxDamage(16));
}
