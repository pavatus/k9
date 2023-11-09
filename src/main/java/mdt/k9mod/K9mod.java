package mdt.k9mod;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import mdt.k9mod.item.ItemInit;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class K9mod implements ModInitializer {
	public static final String MOD_ID = "k9mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);
	}
}