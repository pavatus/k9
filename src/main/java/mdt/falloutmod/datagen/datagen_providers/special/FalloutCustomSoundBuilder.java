package mdt.falloutmod.datagen.datagen_providers.special;

import net.minecraft.sound.SoundEvent;

@FunctionalInterface
public interface FalloutCustomSoundBuilder {
    void add (String soundName, SoundEvent[] soundEvents);
    default void add(String soundName, SoundEvent soundEvent) {
        add(soundName, new SoundEvent[]{soundEvent});
    }
}
