package mdt.falloutmod;

import mdt.falloutmod.core.FMCBlocks;
import mdt.falloutmod.core.FMCSounds;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import mdt.falloutmod.core.FMCItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class K9Mod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final OwoItemGroup K9_ITEM_GROUP = OwoItemGroup.builder(new Identifier(K9Mod.MOD_ID, "item_group"), () -> Icon.of(Items.IRON_INGOT)).build();

	public static final String MOD_ID = "k9mod";

    public static final Logger LOGGER = LoggerFactory.getLogger("falloutmod");

	@Override
	public void onInitialize() {
		FieldRegistrationHandler.register(FMCItems.class, MOD_ID, false);
		FieldRegistrationHandler.register(FMCBlocks.class, MOD_ID, false);
		FieldRegistrationHandler.register(FMCSounds.class, MOD_ID, false);
		K9_ITEM_GROUP.initialize();
	}
}