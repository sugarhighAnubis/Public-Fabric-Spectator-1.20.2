package net.anubis.spectatormod.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class BlueGarnetParticle extends SpriteBillboardParticle {

    public BlueGarnetParticle(ClientWorld world, double xCoord, double yCoord, double zCoord,
                                         SpriteProvider spriteset, double xd, double yd, double zd) {
        super(world, xCoord, yCoord, zCoord, xd, yd, zd);

        this.velocityMultiplier = 1.2f;
        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;

        this.scale += 0.1;
        this.maxAge = 10;
        this.setSpriteForAge(spriteset);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteProvider) {
            this.sprites = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld,
                                       double x, double y, double z, double xd, double yd, double zd) {
            return new BlueGarnetParticle(clientWorld, x, y, z, this.sprites, xd, yd, zd);
        }

    }
}
