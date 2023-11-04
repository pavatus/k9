/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package mdt.falloutmod.core.screens;

import mdt.falloutmod.FalloutMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

//@TODO config screen
@Environment(value=EnvType.CLIENT)
public class ConfigScreen
        extends SimpleOptionsScreen {
    private static SimpleOption<?>[] getOptions(GameOptions gameOptions) {
        return new SimpleOption[]{gameOptions.getNarrator(), gameOptions.getShowSubtitles(), gameOptions.getHighContrast(), gameOptions.getAutoJump(), gameOptions.getTextBackgroundOpacity(), gameOptions.getBackgroundForChatOnly(), gameOptions.getChatOpacity(), gameOptions.getChatLineSpacing(), gameOptions.getChatDelay(), gameOptions.getNotificationDisplayTime(), gameOptions.getSneakToggled(), gameOptions.getSprintToggled(), gameOptions.getDistortionEffectScale(), gameOptions.getFovEffectScale(), gameOptions.getDarknessEffectScale(), gameOptions.getDamageTiltStrength(), gameOptions.getGlintSpeed(), gameOptions.getGlintStrength(), gameOptions.getHideLightningFlashes(), gameOptions.getMonochromeLogo(), gameOptions.getPanoramaSpeed()};
    }

    public ConfigScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, Text.translatable(FalloutMod.MOD_ID,"options.config.title"), ConfigScreen.getOptions(gameOptions));
    }

    @Override
    protected void init() {
        super.init();
        ClickableWidget clickableWidget = this.buttonList.getWidgetFor(this.gameOptions.getHighContrast());
        if (clickableWidget != null && !this.client.getResourcePackManager().getNames().contains("high_contrast")) {
            clickableWidget.active = false;
            clickableWidget.setTooltip(Tooltip.of(Text.translatable("options.accessibility.high_contrast.error.tooltip")));
        }
    }

    @Override
    protected void initFooter() {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("options.accessibility.link"), button -> this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open("https://aka.ms/MinecraftJavaAccessibility");
            }
            this.client.setScreen(this);
        }, "https://aka.ms/MinecraftJavaAccessibility", true))).dimensions(this.width / 2 - 155, this.height - 27, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.client.setScreen(this.parent)).dimensions(this.width / 2 + 5, this.height - 27, 150, 20).build());
    }
}
