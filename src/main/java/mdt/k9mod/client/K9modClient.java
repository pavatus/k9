package mdt.k9mod.client;

import mdt.k9mod.entity.EntityInit;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.client.renderers.K9EntityRenderer;
import mdt.k9mod.client.screen.K9InventoryScreen;
import mdt.k9mod.client.screen.screen_handler.ScreenHandlerInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import networking.PacketInit;

import static mdt.k9mod.K9mod.K9_NET_CHANNEL;

public class K9modClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		entityAttributeRegister();
		entityRenderRegister();
		handledScreenRegister();
		//K9_NET_CHANNEL.registerClientbound(PacketInit.K9Battery.class, (message, access) -> {
		//	K9_NET_CHANNEL.serverHandle(message.k9Entity().getCommandSource().getWorld(), message.k9Entity().getBlockPos());
		//});
	}

	public void entityRenderRegister() {
		EntityRendererRegistry.register(EntityInit.K9_ENTITY_TYPE, K9EntityRenderer::new);
	}

	public void entityAttributeRegister() {
		FabricDefaultAttributeRegistry.register(EntityInit.K9_ENTITY_TYPE, K9Entity.createK9Attributes());
	}

	public void handledScreenRegister() {
		HandledScreens.register(ScreenHandlerInit.K9_INVENTORY_HANDLER, K9InventoryScreen::new);
	}
}