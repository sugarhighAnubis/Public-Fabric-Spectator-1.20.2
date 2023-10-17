package net.anubis.spectatormod.entity.custom;

import net.anubis.spectatormod.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.EnumSet;

public class SpectatorEntity extends TameableEntity implements RangedAttackMob{

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState sitAnimationState = new AnimationState();
    public SpectatorEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }


    private void setupAnimationStates () {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if(isInSittingPose()){
            sitAnimationState.startIfNotRunning(this.age);
        } else {
            sitAnimationState.stop();
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
protected void initGoals(){
      this.goalSelector.add(1, new AnimalMateGoal(this, 0.5));
      this.goalSelector.add(3, new TemptGoal(this, 0.5, Ingredient.ofItems(Items.DIAMOND), false));
      this.targetSelector.add(4, new ActiveTargetGoal<MobEntity>(this, MobEntity.class, 10, true, false, entity -> entity instanceof Monster));
      this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
      this.goalSelector.add(6, new LookAroundGoal(this));
      this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 10.0f, 3.0f, false));
      this.goalSelector.add(0, new SitGoal(this));
        this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.25, 20, 40.0f));
      this.goalSelector.add(0, new SwimGoal(this));
    }


    public static DefaultAttributeContainer.Builder createSpectatorAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15);

    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(200.0);
            this.setHealth(200.0f);
        } else {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(50.0);
        }
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(15.0);
    }




    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.6f;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02f, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.8f));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02f, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5));
            } else {
                this.updateVelocity(this.getMovementSpeed(), movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.91f));
            }
        }
        this.updateLimbs(false);
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.DIAMOND);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.SPECTATOR.create(world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        Item itemForTaming = Items.ECHO_SHARD;

        if(item == itemForTaming && !isTamed()){
            if(this.getWorld().isClient()) {
                return ActionResult.CONSUME;
            } else {
                if(!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                if(!this.getWorld().isClient()) {
                    super.setOwner(player);
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.getWorld().sendEntityStatus(this,(byte)7);
                    setSitting(true);
                    setInSittingPose(true);
                }

                return ActionResult.SUCCESS;
            }
        }

        if(isTamed() && hand ==Hand.MAIN_HAND) {
            boolean sitting = !isSitting();
            setSitting(sitting);
            setInSittingPose(sitting);

            return ActionResult.SUCCESS;
        }


        return super.interactMob(player, hand);
    }


    @Override
    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {

        if (target instanceof SpectatorEntity) {
            SpectatorEntity spectatorEntity = (SpectatorEntity) target;
            return !spectatorEntity.isTamed() || spectatorEntity.getOwner() != owner;
        }
        if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
            return false;
        }
        if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
            return false;
        }
        return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
    }


    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        ShockwaveProjectileEntity shockwaveProjectileEntity = new ShockwaveProjectileEntity(this.getWorld(), this);
        double d = target.getEyeY() - (double) 1.1f;
        double e = target.getX() - this.getX();
        double f = d - shockwaveProjectileEntity.getY();
        double g = target.getZ() - this.getZ();
        double h = Math.sqrt(e * e + g * g) * (double) 0.2f;
        shockwaveProjectileEntity.setVelocity(e, f + h, g, 1.6f, 0.0f);
        this.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0f, 0.4f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        this.getWorld().spawnEntity(shockwaveProjectileEntity);
        if (target instanceof LivingEntity) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200), this);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.AMBIENT_UNDERWATER_LOOP;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_GUARDIAN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GUARDIAN_DEATH;
    }

    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }



    }
