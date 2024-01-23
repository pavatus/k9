package mdt.k9mod;

import io.wispforest.owo.network.OwoNetChannel;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import mdt.k9mod.block.BlockInit;
import mdt.k9mod.client.screen.screen_handler.K9ScreenHandler;
import mdt.k9mod.entity.EntityInit;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.item.ItemGroupInit;
import mdt.k9mod.item.ItemInit;
import mdt.k9mod.client.screen.screen_handler.ScreenHandlerInit;
import mdt.k9mod.sounds.SoundsInit;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import networking.PacketInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class K9mod implements ModInitializer {
	public static final String MOD_ID = "k9mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final OwoNetChannel K9_NET_CHANNEL = OwoNetChannel.create(new Identifier(MOD_ID, "main"));

	@Override
	public void onInitialize() {
		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(BlockInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(EntityInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(SoundsInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(ScreenHandlerInit.class, MOD_ID, false);
		entityAttributeRegister();
		ItemGroupInit.K9_GROUP.initialize();

        //Networking
        //K9_NET_CHANNEL.registerClientboundDeferred(PacketInit.K9Battery.class);
	}

	public void entityAttributeRegister() {
		FabricDefaultAttributeRegistry.register(EntityInit.K9_ENTITY_TYPE, K9Entity.createK9Attributes());
	}
}