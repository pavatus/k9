package mdt.k9mod.item;

import com.google.common.collect.Lists;
import com.sun.jna.platform.KeyboardUtils;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class K9LithiumCellItem extends Item {
    public static final String BATTERY_KEY = "Battery";

    public K9LithiumCellItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.getOrCreateNbt().putInt(BATTERY_KEY, 10);
        super.onCraft(stack, world, player);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        itemStack.getOrCreateNbt().putInt(BATTERY_KEY, 10);
        return itemStack;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack itemStack = context.getStack();

        NbtCompound nbt = itemStack.getNbt();

        if(nbt == null)
            return TypedActionResult.pass(context).getResult();

        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock() instanceof RedstoneBlock) {
            if (nbt.contains(BATTERY_KEY)) {
                nbt.putInt(BATTERY_KEY, Math.min(nbt.getInt(BATTERY_KEY) + 1, 10));
            } else {
                nbt.putInt(BATTERY_KEY, 10);
            }
            itemStack.setNbt(nbt);
        }

        //System.out.println(nbt.getInt(BATTERY_KEY));

        return TypedActionResult.success(context).getResult();
    }

    /*@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        NbtCompound nbt = itemStack.getNbt();

        if(nbt == null)
            return TypedActionResult.pass(user.getStackInHand(hand));

        if(nbt.contains(BATTERY_KEY)) {
            nbt.putInt(BATTERY_KEY, Math.max(nbt.getInt(BATTERY_KEY) - 1, 0));
        } else {
            nbt.putInt(BATTERY_KEY, 10);
        }

        itemStack.setNbt(nbt);

        System.out.println(nbt.getInt(BATTERY_KEY));

        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }*/

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        /*NbtCompound nbtCompound = stack.getNbt();
        //█▓▒░
        String __blockAmount = "██████████";
        if (nbtCompound != null && nbtCompound.contains(BATTERY_KEY)) {
            String newBlockAmount = __blockAmount.substring(__blockAmount.length() - nbtCompound.getInt(BATTERY_KEY));
            for(int i = 0; i < newBlockAmount.length(); i++) {
                if(newBlockAmount.substring(i).matches("█"))
                    newBlockAmount = newBlockAmount.replace("█", "▓");
            }
            MutableText textifiedBlockAmount = Text.literal(newBlockAmount);
            //if(newBlockAmount.length() <= 7) {
            //    textifiedBlockAmount = Text.literal(newBlockAmount).append("▓▒░");
            //}
            tooltip.add(1, textifiedBlockAmount.formatted(Formatting.AQUA));
        }*/
        NbtCompound nbtCompound = stack.getNbt();

        String[] wholeBlockPattern = {"█", "█", "█", "█", "█", "█", "█", "█", "█", "█"};
        String[] fadingPattern = {"▓", "▓", "▓", "▒", "▒", "▒", "░", "░", "░"};

        if (nbtCompound != null && nbtCompound.contains(BATTERY_KEY)) {
            int totalBlocks = 10; // Adjust the total number of blocks based on your scenario
            int remainingBlocks = nbtCompound.getInt(BATTERY_KEY);

            int wholeBlocks = Math.min(totalBlocks, remainingBlocks);
            int fadingBlocks = Math.max(totalBlocks - remainingBlocks, 0);

            StringBuilder tooltipString = new StringBuilder();

            // Append the whole block pattern
            for (int i = 0; i < wholeBlocks; i++) {
                tooltipString.append(wholeBlockPattern[i % wholeBlockPattern.length]);
            }

            // Append the fading pattern for the remaining part
            for (int i = 0; i < fadingBlocks; i++) {
                tooltipString.append(fadingPattern[i % fadingPattern.length]);
            }

            MutableText textifiedBlockAmount = remainingBlocks > 0 ? Text.literal(tooltipString.toString()) : Text.literal("░░░░░░░░░░░");
            tooltip.add(1, textifiedBlockAmount.formatted(remainingBlocks > 0 ? Formatting.AQUA : Formatting.RED));
            /*assert MinecraftClient.getInstance().currentScreen != null;
            if (MinecraftClient.getInstance().currentScreen.keyPressed(GLFW.GLFW_KEY_LEFT_SHIFT, GLFW.glfwGetKeyScancode(GLFW.GLFW_KEY_LEFT_SHIFT), 0))*/
            if (MinecraftClient.getInstance().options.advancedItemTooltips)
                tooltip.add(2, remainingBlocks > 0 ? Text.translatable(BATTERY_KEY + " Level: " + remainingBlocks + "0%").formatted(Formatting.GREEN) : Text.translatable("Battery Depleted!").formatted(Formatting.GOLD));
        }
    }

}
