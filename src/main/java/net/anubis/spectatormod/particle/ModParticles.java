package net.anubis.spectatormod.particle;

import net.anubis.spectatormod.SpectatorMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final DefaultParticleType BLUE_GARNET_PARTICLE =
            registerParticle("blue_garnet_particle", FabricParticleTypes.simple());

    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(SpectatorMod.MOD_ID, name), particleType);
    }

    public static void registerParticles(){
        SpectatorMod.LOGGER.info("Registering Particles for" + SpectatorMod.MOD_ID);
    }
}
