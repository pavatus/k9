package com.mdt.k9mod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class NBTUtil {

    public static void inventoryFromNBT(IItemHandler inventory, CompoundTag nbt) {
        ListTag items = nbt.getList("Items", 27);
        for(int i = 0; i < items.size(); ++i) {
            CompoundTag compound = items.getCompound(i);
            ItemStack itemstack = ItemStack.of(compound);
//            System.out.println("Compound: " + compound +" | Stack: " + itemstack + " | Iteration: " + i);

            if (!itemstack.isEmpty()) {
//                System.out.println("Adding item " + itemstack + "into slot " + compound );
                inventory.insertItem(compound.getInt("Slot"), itemstack, false);
            }
        }
    }

    public static void inventoryToNBT(IItemHandler inventory, CompoundTag nbt) {
        ListTag items = new ListTag();
        for(int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack itemstack = inventory.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                CompoundTag compound = new CompoundTag();
                compound.putInt("Slot", i);
//                System.out.println("Compound: " + compound +" | Stack: " + itemstack + " | Iteration: " + i);

                items.add(itemstack.save(compound));
            }
        }

        nbt.put("Items", items);
    }
}
