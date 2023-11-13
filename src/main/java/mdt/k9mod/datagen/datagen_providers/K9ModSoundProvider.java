package mdt.k9mod.datagen.datagen_providers;

import mdt.k9mod.datagen.datagen_providers.special.K9ModCustomSoundBuilder;
import mdt.k9mod.datagen.datagen_providers.special.K9ModCustomSoundProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.sound.SoundEvent;

import java.util.HashMap;

public class K9ModSoundProvider extends K9ModCustomSoundProvider {
    private final FabricDataOutput dataGenerator;

    private HashMap<String, SoundEvent[]> soundEventList = new HashMap<>();
    public K9ModSoundProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
        dataGenerator = dataOutput;
    }

    @Override
    public void generateSoundsData(K9ModCustomSoundBuilder k9ModCustomSoundBuilder) {
        soundEventList.forEach(k9ModCustomSoundBuilder::add);
    }

    public void addSound(String soundName, SoundEvent sound) {
        soundEventList.put(soundName, new SoundEvent[]{sound});
    }
    public void addSound(String soundName, SoundEvent[] soundEvents) {
        soundEventList.put(soundName, soundEvents);
    }
}
