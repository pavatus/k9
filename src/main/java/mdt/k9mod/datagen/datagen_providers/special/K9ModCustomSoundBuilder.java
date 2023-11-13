package mdt.k9mod.datagen.datagen_providers.special;

import net.minecraft.sound.SoundEvent;

@FunctionalInterface
public interface K9ModCustomSoundBuilder {
    void add (String soundName, SoundEvent[] soundEvents);
    default void add(String soundName, SoundEvent soundEvent) {
        add(soundName, new SoundEvent[]{soundEvent});
    }
}
