package mdt.k9mod.sounds;

import mdt.k9mod.K9mod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundsInit implements SoundRegistryContainer {

    public static final SoundEvent K9_DEFENSE = SoundEvent.of(new Identifier(K9mod.MOD_ID, "k9_defense"));
    public static final SoundEvent K9_DIE = SoundEvent.of(new Identifier(K9mod.MOD_ID, "k9_die"));
    public static final SoundEvent K9_HURT = SoundEvent.of(new Identifier(K9mod.MOD_ID, "k9_hurt"));
    public static final SoundEvent K9_MASTER = SoundEvent.of(new Identifier(K9mod.MOD_ID, "k9_master"));
    public static final SoundEvent K9_RESTART = SoundEvent.of(new Identifier(K9mod.MOD_ID, "k9_restart"));
    public static final SoundEvent K9_RUN = SoundEvent.of(new Identifier(K9mod.MOD_ID, "k9_run"));

}
