package mdt.falloutmod.datagen.datagen_providers;

import mdt.falloutmod.datagen.datagen_providers.special.FalloutCustomSoundBuilder;
import mdt.falloutmod.datagen.datagen_providers.special.FalloutCustomSoundProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.sound.SoundEvent;

import java.util.HashMap;

public class FalloutSoundProvider extends FalloutCustomSoundProvider {
    private final FabricDataOutput dataGenerator;

    private HashMap<String, SoundEvent[]> soundEventList = new HashMap<>();
    public FalloutSoundProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
        dataGenerator = dataOutput;
    }

    @Override
    public void generateSoundsData(FalloutCustomSoundBuilder falloutCustomSoundBuilder) {
        soundEventList.forEach(falloutCustomSoundBuilder::add);
    }

    public void addSound(String soundName, SoundEvent sound) {
        soundEventList.put(soundName, new SoundEvent[]{sound});
    }
    public void addSound(String soundName, SoundEvent[] soundEvents) {
        soundEventList.put(soundName, soundEvents);
    }
}
