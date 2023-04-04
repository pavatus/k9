package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class K9modSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, K9Mod.MOD_ID);
    public static final RegistryObject<SoundEvent> K9_GROWL = SOUNDS.register("k9_growl", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_growl")));
    public static final RegistryObject<SoundEvent> K9_WHINE = SOUNDS.register("k9_whine", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_whine")));
    public static final RegistryObject<SoundEvent> K9_PANT = SOUNDS.register("k9_pant", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_pant")));
    public static final RegistryObject<SoundEvent> K9_HURT = SOUNDS.register("k9_hurt", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_hurt")));
    public static final RegistryObject<SoundEvent> K9_DEATH = SOUNDS.register("k9_die", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_death")));
    public static final RegistryObject<SoundEvent> K9_AMBIENT = SOUNDS.register("k9_ambient", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_ambient")));
    public static final RegistryObject<SoundEvent> K9_MASTER = SOUNDS.register("k9_master", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_master")));
    public static final RegistryObject<SoundEvent> K9_DEFENSE = SOUNDS.register("k9_defense", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_defense")));
    public static final RegistryObject<SoundEvent> K9_RESTART = SOUNDS.register("k9_restart", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_restart")));
    public static final RegistryObject<SoundEvent> K9_RUN = SOUNDS.register("k9_run", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_run")));
}
