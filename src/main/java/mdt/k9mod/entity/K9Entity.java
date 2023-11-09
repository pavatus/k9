package mdt.k9mod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.world.World;

public class K9Entity extends WolfEntity {
    public K9Entity(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
    }
}
