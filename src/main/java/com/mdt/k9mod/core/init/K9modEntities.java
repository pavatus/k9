package com.mdt.k9mod.core.init;

import com.mdt.k9mod.common.entities.K9Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.mdt.K9mod.K9MOD.MOD_ID;

public class K9modEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

    public static final RegistryObject<EntityType<K9Entity>> K9 = ENTITIES.register(
            "k9_entity", () -> EntityType.Builder.of(K9Entity::new, EntityClassification.CREATURE).sized(1f, 1f).build(
                    new ResourceLocation(MOD_ID, "k9").toString()));
}