package mdt.falloutmod.client;

import mdt.falloutmod.FalloutMod;
import mdt.falloutmod.core.FMCBlocks;
import mdt.falloutmod.core.screens.PipboyMainScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FalloutModClient implements ClientModInitializer {

	private static KeyBinding keyBinding;

	@Override
	public void onInitializeClient() {
		setupBlockRendering();
		setKeyBinding();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
	public void setKeyBinding() {
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key." + FalloutMod.MOD_ID + ".open",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"category." + FalloutMod.MOD_ID + ".pipboy"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (keyBinding.wasPressed()) {
				client.setScreen(new PipboyMainScreen(MinecraftClient.getInstance().player));
			}
		});
	}

	public void setupBlockRendering() {
		BlockRenderLayerMap map = BlockRenderLayerMap.INSTANCE;
		map.putBlock(FMCBlocks.STAND, RenderLayer.getCutout());
		map.putBlock(FMCBlocks.RADIO, RenderLayer.getCutout());
		map.putBlock(FMCBlocks.TYPEWRITER, RenderLayer.getCutout());
	}
}