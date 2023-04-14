package com.mdt.k9mod.common.container;

import com.mdt.k9mod.common.entities.K9Entity;
import com.mdt.k9mod.core.init.K9Containers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class K9InventoryContainer extends AbstractContainerMenu {

//    private final IInventory container;
    public int battery;

//    private final ContainerLevelAccess containerAccess;

    // Client constructor
    public K9InventoryContainer(int id, Inventory playerInv) {
        this(id, playerInv, new ItemStackHandler(27), 50);
    }

    public int getBattery() {
        return this.battery;
    }

    // Server constructor
    public K9InventoryContainer(int id, Inventory playerInv, IItemHandler slots, int batteryLevel) {
        super(K9Containers.K9_CONTAINER.get(), id);

        this.battery = batteryLevel;
        System.out.println(this.battery);

        final int slotSizePlus2 = 18, startX = 8, startY = 86, hotbarY = 144, inventoryY = 18;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new SlotItemHandler(slots, row * 9 + column, startX + column * slotSizePlus2,
                        inventoryY + row * slotSizePlus2));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2,
                        startY + row * slotSizePlus2));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
        }
    }

    public K9InventoryContainer(int i, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        super(K9Containers.K9_CONTAINER.get(), i);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(index);
        if (slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if (index < 27) {
                if (!moveItemStackTo(item, 27, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(item, 0, 27, false))
                return ItemStack.EMPTY;

            if (item.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return retStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public static MenuConstructor getServerContainer(K9Entity entity, int battery) {
        return (id, playerInv, player) -> new K9InventoryContainer(id, playerInv, entity.inventory , battery);
    }

    public static K9InventoryContainer getClientMenu(int id, Inventory playerInv) {
        return new K9InventoryContainer(id, playerInv);
    }
}
