package com.mdt.k9mod.core.init;

import com.mdt.k9mod.K9Mod;
import com.mdt.k9mod.common.entities.K9Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

public class K9modEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, K9Mod.MOD_ID);

    public static final RegistryObject<EntityType<K9Entity>> K9 = ENTITIES.register(
            "k9_entity", () -> EntityType.Builder.of(K9Entity::new, MobCategory.CREATURE).sized(1f, 1f).build(
                    new ResourceLocation(K9Mod.MOD_ID, "k9").toString()));
}