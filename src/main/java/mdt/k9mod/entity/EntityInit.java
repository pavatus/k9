package mdt.k9mod.entity;

import io.wispforest.owo.registration.annotations.AssignedName;
import io.wispforest.owo.registration.reflect.EntityRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;

public class EntityInit implements EntityRegistryContainer {

    @AssignedName("k9_entity")
    public static final EntityType<K9Entity> K9_ENTITY_TYPE = FabricEntityTypeBuilder.create(
            SpawnGroup.MISC, K9Entity::new).dimensions(EntityDimensions.changing(0.8f, 0.8f)).build();
}
