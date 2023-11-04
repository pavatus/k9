package mdt.falloutmod.core;

import mdt.falloutmod.FalloutMod;
import mdt.falloutmod.core.sounds.SoundRegistryContainer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class FMCSounds implements SoundRegistryContainer {
    public static final SoundEvent STIMPAK_USE = SoundEvent.of(new Identifier(FalloutMod.MOD_ID, "stimpak_use"));
    public static final SoundEvent DEMATERIALIZE = SoundEvent.of(new Identifier(FalloutMod.MOD_ID, "dematerialize"));
    public static final SoundEvent TITLE_MUSIC = SoundEvent.of(new Identifier(FalloutMod.MOD_ID, "title_music"));
    public static final SoundEvent BLAST_DOOR_OPEN = SoundEvent.of(new Identifier(FalloutMod.MOD_ID, "blast_door_open"));
}
