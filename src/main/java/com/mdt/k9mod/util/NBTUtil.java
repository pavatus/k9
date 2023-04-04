package com.mdt.k9mod.util;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;

public class NBTUtil {

    public static void inventoryFromNBT(Inventory inventory, CompoundNBT nbt) {
        ListNBT items = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < items.size(); ++i) {
            CompoundNBT compound = items.getCompound(i);
            ItemStack itemstack = ItemStack.of(compound);

            if (!itemstack.isEmpty()) {
                inventory.setItem(compound.getInt("Slot"), itemstack);
            }
        }
    }

    public static void inventoryToNBT(Inventory inventory, CompoundNBT nbt) {
        ListNBT items = new ListNBT();
        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack itemstack = inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                CompoundNBT compound = new CompoundNBT();
                compound.putInt("Slot", i);

                items.add(itemstack.save(compound));
            }
        }

        nbt.put("Items", items);
    }
}
