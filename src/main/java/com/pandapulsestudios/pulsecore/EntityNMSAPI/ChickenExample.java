package com.pandapulsestudios.pulsecore.EntityNMSAPI;

//import com.pandapulsestudios.pulsecore.PulseCore;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.ai.attributes.AttributeMap;
//import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
//import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
//import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
//import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
//import net.minecraft.world.entity.animal.Chicken;
//import net.minecraft.world.entity.monster.Zombie;
//import net.minecraft.world.entity.player.Player;
//import org.bukkit.Location;
//import org.bukkit.NamespacedKey;
//import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
//import org.bukkit.event.entity.CreatureSpawnEvent;
//import org.bukkit.persistence.PersistentDataType;

public class ChickenExample{
//    public static final NamespacedKey KEY = new NamespacedKey(PulseCore.PulseCore, "DeadlyChicken");
//
//    public ChickenExample(Location location) {
//        super(EntityType.CHICKEN, ((CraftWorld) location.getWorld()).getHandle());
//        this.setPosRaw(location.getX(), location.getY(), location.getZ());
//        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
//        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
//        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
//        this.getBukkitEntity().getPersistentDataContainer().set(KEY, PersistentDataType.BOOLEAN, true);
//        this.persist = true;
//        ((CraftWorld) location.getWorld()).getHandle().addFreshEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);
//    }
//
//    @Override
//    public AttributeMap getAttributes() {
//        return new AttributeMap(Zombie.createAttributes().build());
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.WITHER_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damagesource) {
//        return SoundEvents.WITHER_SKELETON_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return super.getDeathSound();
//    }
}
