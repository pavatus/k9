package mdt.k9mod.client.screen.screen_handler;

import mdt.k9mod.containers.ScreenHandlerContainer;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerInit implements ScreenHandlerContainer {

    public static final ScreenHandlerType<K9ScreenHandler> K9_INVENTORY_HANDLER = new ScreenHandlerType<>(K9ScreenHandler::createDefault, ScreenHandlerType.GENERIC_9X3.getRequiredFeatures());

}
