package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class K9modSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, K9Mod.MOD_ID);
    public static final RegistryObject<SoundEvent> KNINE_GROWL = SOUNDS.register("knine_growl", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "knine_growl")));
    public static final RegistryObject<SoundEvent> KNINE_WHINE = SOUNDS.register("knine_whine", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "knine_whine")));
    public static final RegistryObject<SoundEvent> KNINE_PANT = SOUNDS.register("knine_pant", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "knine_pant")));
    public static final RegistryObject<SoundEvent> KNINE_HURT = SOUNDS.register("knine_hurt", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "knine_hurt")));
    public static final RegistryObject<SoundEvent> KNINE_DEATH = SOUNDS.register("knine_death", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "knine_death")));
    public static final RegistryObject<SoundEvent> KNINE_AMBIENT = SOUNDS.register("knine_ambient", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "knine_ambient")));

    public static final RegistryObject<SoundEvent> K9_MASTER = SOUNDS.register("k9_master", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_master")));
    public static final RegistryObject<SoundEvent> K9_DEFENSE = SOUNDS.register("k9_defense", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_defense")));
    public static final RegistryObject<SoundEvent> K9_DIE = SOUNDS.register("k9_die", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_die")));
    public static final RegistryObject<SoundEvent> K9_RESTART = SOUNDS.register("k9_restart", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_restart")));
    public static final RegistryObject<SoundEvent> K9_RUN = SOUNDS.register("k9_run", () -> new SoundEvent(
            new ResourceLocation(K9Mod.MOD_ID, "k9_run")));
}
