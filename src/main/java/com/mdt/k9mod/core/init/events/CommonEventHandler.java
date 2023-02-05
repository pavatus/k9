package com.mdt.k9mod.core.init.events;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.entities.K9Entity;
import com.mdt.k9mod.core.init.K9modEntities;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = K9Mod.MOD_ID)
public class CommonEventHandler {

    /*@SubscribeEvent
    public void onWorldFirstLoad(WorldEvent.CreateSpawnPosition event) {
        World world = (World) event.getWorld();
        K9Entity k9 = new K9Entity(K9modEntities.K9.get(), world);
        world.addFreshEntity(k9);
        k9.setPos(world.);
    }*/
}
