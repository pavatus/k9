package mdt.k9mod;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import mdt.k9mod.block.BlockInit;
import mdt.k9mod.client.screen.screen_handler.K9ScreenHandler;
import mdt.k9mod.entity.EntityInit;
import mdt.k9mod.item.ItemGroupInit;
import mdt.k9mod.item.ItemInit;
import mdt.k9mod.client.screen.screen_handler.ScreenHandlerInit;
import mdt.k9mod.sounds.SoundsInit;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class K9mod implements ModInitializer {
	public static final String MOD_ID = "k9mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	static {
		ScreenHandlerType<ScreenHandler> K9_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID + ".k9screenhandler"),K9ScreenHandler::create9x3);
	}

	@Override
	public void onInitialize() {
		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(BlockInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(EntityInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(SoundsInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(ScreenHandlerInit.class, MOD_ID, false);
		ItemGroupInit.K9_GROUP.initialize();
	}
}