package com.mdt.k9mod.core.init.events;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.entities.K9Entity;
import com.mdt.k9mod.core.init.K9modEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = K9Mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class K9ModEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(K9modEntities.K9.get(), K9Entity.createK9Attributes().build());
    }
}
