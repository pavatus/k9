package com.mdt.k9mod;

import com.mdt.k9mod.common.screens.K9InventoryScreen;
import com.mdt.k9mod.core.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(K9Mod.MOD_ID)
public class K9Mod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "k9mod";

    public static CreativeModeTab K9_TAB;

    public K9Mod() {


        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        K9modSounds.SOUNDS.register(bus);
        K9modEntities.ENTITIES.register(bus);
        K9modItems.ITEMS.register(bus);
        K9modBlocks.BLOCKS.register(bus);
        K9Containers.CONTAINERS.register(bus);

        bus.addListener(this::registerTabs);
    }

    private void registerTabs(CreativeModeTabEvent.Register event)
    {
        K9_TAB = event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "k9_tab"), builder -> builder
                .icon(() -> new ItemStack(K9modItems.K9_BONE.get()))
                .title(Component.translatable("tabs.k9mod.k9_tab"))
                .displayItems((featureFlags, output) -> {
                    // Add all the items in registry
                    for (RegistryObject<Item> item : K9modItems.ITEMS.getEntries()) {
                        output.accept(item.get());
                    }
                    // Add all the blocks in registry
                    for (RegistryObject<Block> block : K9modBlocks.BLOCKS.getEntries()) {
                        output.accept(block.get().asItem());
                    }
                })
        );
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(K9Containers.K9_CONTAINER.get(), K9InventoryScreen::new));
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
