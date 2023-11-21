package mdt.k9mod.client.screen.screen_handler;

import com.google.common.base.Suppliers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;

public class K9ScreenHandler extends ScreenHandler {
    
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public K9ScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        this(type, syncId, playerInventory, new SimpleInventory(35), new ArrayPropertyDelegate(1));
    }

    public static K9ScreenHandler createDefault(int syncId, PlayerInventory playerInventory) {
        return new K9ScreenHandler(ScreenHandlerInit.K9_INVENTORY_HANDLER, syncId, playerInventory);
    }

    public K9ScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        int l;
        int k;
        GenericContainerScreenHandler.checkSize(inventory, 35);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        inventory.onOpen(playerInventory.player);
        int i = 3;
        int j = 9;
        for (k = 0; k < i; ++k) {
            for (l = 0; l < j; ++l) {
                this.addSlot(new ShulkerBoxSlot(inventory, l + k * j, 8 + l * 18, 18 + k * 18));
            }
        }
        for (k = 0; k < i; ++k) {
            for (l = 0; l < j; ++l) {
                this.addSlot(new Slot(playerInventory, l + k * j + j, 8 + l * 18, 84 + k * 18));
            }
        }
        for (k = 0; k < j; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
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
