package mdt.k9mod;

import mdt.k9mod.entity.EntityInit;
import mdt.k9mod.entity.K9Entity;
import mdt.k9mod.renderers.K9EntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class K9modClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		entityAttributeRegister();
		entityRenderRegister();
	}

	public void entityRenderRegister() {
		EntityRendererRegistry.register(EntityInit.K9_ENTITY_TYPE, K9EntityRenderer::new);
	}

	public void entityAttributeRegister() {
		FabricDefaultAttributeRegistry.register(EntityInit.K9_ENTITY_TYPE, K9Entity.createK9Attributes());
	}
}