package com.pandapulsestudios.pulsecore.ParticleAPI.API;

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

    public static void SpawnSphere(Location location, Particle particle, int density, int duration, double sphereRadius){
        if(location.getWorld() == null) return;

        for (double i = 0; i <= Math.PI; i += Math.PI / density) {
            double radius = sphereRadius *  Math.sin(i);
            double y = sphereRadius * Math.cos(i);

            for (double a = 0; a < Math.PI * 2; a+= Math.PI / density) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;

                location.add(x, y, z);
                location.getWorld().spawnParticle(particle, location, duration, 0F, 0F, 0F, 0.001);
                location.subtract(x, y, z);
            }
        }
    }
}
