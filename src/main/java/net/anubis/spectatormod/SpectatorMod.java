package net.anubis.spectatormod;

import net.anubis.spectatormod.entity.ModEntities;
import net.anubis.spectatormod.entity.custom.SpectatorEntity;
import net.anubis.spectatormod.item.ModItems;
import net.anubis.spectatormod.particle.ModParticles;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectatorMod implements ModInitializer {
	public static final String MOD_ID = "spectatormod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		FabricDefaultAttributeRegistry.register(ModEntities.SPECTATOR, SpectatorEntity.createSpectatorAttributes());

		ModParticles.registerParticles();
	}
}