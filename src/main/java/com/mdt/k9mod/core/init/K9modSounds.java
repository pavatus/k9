package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class K9modSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, K9Mod.MOD_ID);
    public static final RegistryObject<SoundEvent> K9_GROWL = SOUNDS.register("k9_growl", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_growl"),1f));
    public static final RegistryObject<SoundEvent> K9_WHINE = SOUNDS.register("k9_whine", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_whine"),1f));
    public static final RegistryObject<SoundEvent> K9_PANT = SOUNDS.register("k9_pant", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_pant"),1f));
    public static final RegistryObject<SoundEvent> K9_HURT = SOUNDS.register("k9_hurt", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_hurt"),1f));
    public static final RegistryObject<SoundEvent> K9_DEATH = SOUNDS.register("k9_die", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_death"),1f));
    public static final RegistryObject<SoundEvent> K9_AMBIENT = SOUNDS.register("k9_ambient", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_ambient"),1f));
    public static final RegistryObject<SoundEvent> K9_MASTER = SOUNDS.register("k9_master", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_master"),1f));
    public static final RegistryObject<SoundEvent> K9_DEFENSE = SOUNDS.register("k9_defense", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_defense"),1f));
    public static final RegistryObject<SoundEvent> K9_RESTART = SOUNDS.register("k9_restart", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_restart"),1f));
    public static final RegistryObject<SoundEvent> K9_RUN = SOUNDS.register("k9_run", () -> SoundEvent.createFixedRangeEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_run"),1f));
}
