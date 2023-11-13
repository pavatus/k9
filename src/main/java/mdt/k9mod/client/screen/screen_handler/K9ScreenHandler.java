package mdt.k9mod.client.screen.screen_handler;

import com.google.common.base.Suppliers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;

public class K9ScreenHandler extends ScreenHandler {
    
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public K9ScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        this(type, syncId, playerInventory, new SimpleInventory(27), new ArrayPropertyDelegate(1));
    }

    public static K9ScreenHandler createDefault(int syncId, PlayerInventory playerInventory) {
        return new K9ScreenHandler(ScreenHandlerInit.K9_INVENTORY_HANDLER, syncId, playerInventory);
    }

    public K9ScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        int k;
        int j;
        GenericContainerScreenHandler.checkSize(inventory, 27);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        inventory.onOpen(playerInventory.player);
        int i = (3 - 4) * 18;
        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }
        for (j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot < 27 ? !this.insertItem(itemStack2, 27, this.slots.size(), true) : !this.insertItem(itemStack2, 0, 27, false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getBattery() {
        return propertyDelegate.get(0);
    }
}
