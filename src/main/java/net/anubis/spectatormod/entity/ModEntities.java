package net.anubis.spectatormod.entity;

import net.anubis.spectatormod.SpectatorMod;
import net.anubis.spectatormod.entity.custom.ShockwaveEntity;
import net.anubis.spectatormod.entity.custom.SpectatorEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SpectatorEntity> SPECTATOR = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SpectatorMod.MOD_ID, "spectator"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpectatorEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static final EntityType<ShockwaveEntity> SHOCKWAVE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SpectatorMod.MOD_ID, "shockwave"),
            FabricEntityTypeBuilder.<ShockwaveEntity>create(SpawnGroup.CREATURE, ShockwaveEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());



}


