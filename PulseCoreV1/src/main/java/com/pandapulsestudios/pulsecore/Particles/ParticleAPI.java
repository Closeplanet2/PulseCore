package com.pandapulsestudios.pulsecore.Particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticleAPI {
    public static void SpawnParticle(World world, Particle particle, Location point){
        SpawnParticle(world, particle, point.toVector());
    }

    public static void SpawnParticle(World world, Particle particle, Vector point){
        world.spawnParticle(particle, point.getX(), point.getY(), point.getZ(), 1);
    }

    public static void SpawnParticle(World world, Particle particle, Location location, int i, int vm, int v1, int v2, int v3){
        world.spawnParticle(particle, location, i, vm, v1, v2, v3);
    }
}
