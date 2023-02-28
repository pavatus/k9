package com.mdt.k9mod.common.events;


import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.core.commands.K9BatteryCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = K9Mod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        //new K9BatteryCommand(event.getDispatcher());
    }

}
