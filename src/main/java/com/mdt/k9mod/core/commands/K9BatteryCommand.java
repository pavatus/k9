package com.mdt.k9mod.core.commands;

import com.mdt.k9mod.core.init.K9modItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class K9BatteryCommand {

    /*public K9BatteryCommand(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("k9").then(Commands.literal("battery")).executes((command) -> {
            return getK9Battery(command.getSource());
        }));
    }

    private int getK9Battery(CommandSource source) throws CommandSyntaxException {
        return 0;
    }*/

}
