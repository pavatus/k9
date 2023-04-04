package com.mdt.k9mod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class NBTUtil {

    public static void inventoryFromNBT(SimpleContainer inventory, CompoundTag nbt) {
        ListTag items = nbt.getList("Items", 27);
        for(int i = 0; i < items.size(); ++i) {
            CompoundTag compound = items.getCompound(i);
            ItemStack itemstack = ItemStack.of(compound);

            if (!itemstack.isEmpty()) {
                inventory.setItem(compound.getInt("Slot"), itemstack);
            }
        }
    }

    public static void inventoryToNBT(SimpleContainer inventory, CompoundTag nbt) {
        ListTag items = new ListTag();
        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack itemstack = inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                CompoundTag compound = new CompoundTag();
                compound.putInt("Slot", i);

                items.add(itemstack.save(compound));
            }
        }

        nbt.put("Items", items);
    }
}
