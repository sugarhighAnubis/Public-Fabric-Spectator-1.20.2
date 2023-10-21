package net.anubis.spectatormod.entity.custom;


import net.anubis.spectatormod.entity.ModEntities;
import net.anubis.spectatormod.item.ModItems;
import net.anubis.spectatormod.particle.ModParticles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShockwaveEntity  extends ThrownItemEntity {
    public ShockwaveEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShockwaveEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.SHOCKWAVE, livingEntity, world);
    }

    public ShockwaveEntity(World world, double x, double y, double z) {
        super(ModEntities.SHOCKWAVE, x, y, z, world);
    }


    public ShockwaveEntity(World world, LivingEntity owner) {
        super((EntityType<? extends ThrownItemEntity>)ModEntities.SHOCKWAVE, owner, world);
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SHOCKWAVE;
    }

    private static final TrackedData<Boolean> HIT =
            DataTracker.registerData(ShockwaveEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int counter = 0;


    @Override
    public void tick() {
        super.tick();

        if(this.dataTracker.get(HIT)) {
            if(this.age >= counter) {
                this.discard();
            }
        }

        if (this.age >= 200) {
            this.remove(RemovalReason.DISCARDED);
        }

        Vec3d vec3 = this.getVelocity();
        HitResult hitresult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitresult.getType() != HitResult.Type.MISS)
            this.onCollision(hitresult);

        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;

        for(int i = 1; i < 5; ++i) {
            this.getWorld().addParticle(ModParticles.BLUE_GARNET_PARTICLE, d0-(d5*2), d1-(d6*2), d2-(d7*2),
                    -d5, -d6 - 0.1D, -d7);
        }

        if (this.getWorld().getStatesInBox(this.getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            this.discard();
        } else if (this.isInsideWaterOrBubbleColumn()) {
            this.discard();
        } else {
            this.setVelocity(vec3.multiply(0.99F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity hitEntity = entityHitResult.getEntity();
        Entity owner = this.getOwner();

        if(hitEntity == owner && this.getWorld().isClient()) {
            return;
        }

        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity)owner : null;
        float damage = 8f;
        boolean hurt = hitEntity.damage(this.getDamageSources().mobProjectile(this, livingentity), damage);
        if (hurt) {
            if(hitEntity instanceof LivingEntity livingHitEntity) {
                livingHitEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200, 1), owner);
                livingHitEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 1), owner);
                livingHitEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1), owner);

            }
        }

        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.getWorld().addParticle(ParticleTypes.SCULK_SOUL, this.getX(), this.getY(), this.getZ(),
                        Math.cos(x*20) * 0.1d, Math.cos(y*20) * 0.05d, Math.sin(x*20) * 0.1d);
            }
        }
    }

    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        BlockState blockState = this.getWorld().getBlockState(blockHitResult.getBlockPos());
        blockState.onProjectileHit(this.getWorld(), blockState, blockHitResult, this);

        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.getWorld().addParticle(ParticleTypes.SCULK_SOUL, this.getX(), this.getY(), this.getZ(),
                        Math.cos(x*20) * 0.1d, Math.cos(y*20) * 0.05d, Math.sin(x*20) * 0.1d);
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(this.getWorld().isClient()) {
            return;
        }

        if(hitResult.getType() == HitResult.Type.ENTITY && hitResult instanceof EntityHitResult entityHitResult) {
            Entity hit = entityHitResult.getEntity();
            Entity owner = this.getOwner();

            if(owner != hit) {
                this.dataTracker.set(HIT, true);
                counter = this.age + 5;
            }
        } else if(hitResult.getType() == HitResult.Type.BLOCK) {
            this.dataTracker.set(HIT, true);
            counter = this.age + 5;
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HIT, false);
    }
}