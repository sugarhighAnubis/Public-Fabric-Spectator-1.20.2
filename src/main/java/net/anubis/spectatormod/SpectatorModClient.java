package net.anubis.spectatormod;

import net.anubis.spectatormod.entity.ModEntities;
import net.anubis.spectatormod.entity.client.ModModelLayers;
import net.anubis.spectatormod.entity.client.SpectatorModel;
import net.anubis.spectatormod.entity.client.SpectatorRenderer;
import net.anubis.spectatormod.particle.BlueGarnetParticle;
import net.anubis.spectatormod.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.mob.FlyingEntity;

public class SpectatorModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.SPECTATOR,SpectatorRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SPECTATOR,SpectatorModel::getTexturedModelData);

        ParticleFactoryRegistry.getInstance().register(ModParticles.BLUE_GARNET_PARTICLE, BlueGarnetParticle.Factory::new);

        EntityRendererRegistry.register(ModEntities.SHOCKWAVE, FlyingItemEntityRenderer::new);
    }
}
