package com.pandapulsestudios.pulsecore.Location;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocationAPI {
    public static Location LOCATION(Player player, double x, double y, double z){ return LOCATION(player.getWorld(), x, y, z); }
    public static Location LOCATION(UUID worldUUID, double x, double y, double z){return LOCATION(Bukkit.getWorld(worldUUID), x, y, z);}
    public static Location LOCATION(String worldName, double x, double y, double z){return LOCATION(Bukkit.getWorld(worldName), x, y, z);}
    public static Location LOCATION(World world, double x, double y, double z){
        if(world == null) return null;
        return new Location(world, x, y , z);
    }

    public static Location LOCATION(Player player, double x, double y, double z, float yaw, float pitch){ return LOCATION(player.getWorld(), x, y, z, yaw, pitch); }
    public static Location LOCATION(UUID worldUUID, double x, double y, double z, float yaw, float pitch){return LOCATION(Bukkit.getWorld(worldUUID), x, y, z, yaw, pitch);}
    public static Location LOCATION(String worldName, double x, double y, double z, float yaw, float pitch){return LOCATION(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);}
    public static Location LOCATION(World world, double x, double y, double z, float yaw, float pitch){
        if(world == null) return null;
        return new Location(world, x, y , z, yaw, pitch);
    }

    public static void TeleportLocation(String locationName, Player... players){
        for(var pulseLocationName : PulseCore.CustomLocations.keySet()){
            var pulseLocation = PulseCore.CustomLocations.get(pulseLocationName);
            if(pulseLocation.locationName().equals(locationName)) pulseLocation.TeleportPlayers(players);
        }
    }

    public static void TeleportLocation(String locationName, Entity... entities){
        for(var pulseLocationName : PulseCore.CustomLocations.keySet()){
            var pulseLocation = PulseCore.CustomLocations.get(pulseLocationName);
            if(pulseLocation.locationName().equals(locationName)) pulseLocation.TeleportPlayers(entities);
        }
    }

    public static List<PulseLocation> ReturnAllPulseLocations(Location location, boolean useDistanceForEvent){
        var data = new ArrayList<PulseLocation>();
        for(var pulseLocationName : PulseCore.CustomLocations.keySet()){
            var pulseLocation = PulseCore.CustomLocations.get(pulseLocationName);
            var isLocationForEvent = pulseLocation.storedLocation().distance(location) <= (useDistanceForEvent ? pulseLocation.distanceForEvents() : 1);
            if (isLocationForEvent) data.add(pulseLocation);
        }
        return data;
    }

    public static Location ReturnClosestLocation(List<Location> locations, Location originLocation){
        var distance = Double.POSITIVE_INFINITY;
        Location location = null;
        for(var s : locations){
            if(originLocation.distance(s) < distance){
                distance = originLocation.distance(s);
                location = s;
                if(distance == 0) break;
            }
        }
        return location;
    }

    public static Double ReturnDistanceClosestLocation(List<Location> locations, Location originLocation){
        var distance = Double.POSITIVE_INFINITY;
        Location location = null;
        for(var s : locations){
            if(originLocation.distance(s) < distance){
                distance = originLocation.distance(s);
                location = s;
                if(distance == 0) break;
            }
        }
        return distance;
    }

    public static boolean IsBlockBetweenLocations(Location location1, Location location2, Block block) {
        // Get the World object for the locations
        var world = location1.getWorld();

        // Get the minimum and maximum coordinates of the bounding box
        var minX = Math.min(location1.getBlockX(), location2.getBlockX());
        var maxX = Math.max(location1.getBlockX(), location2.getBlockX());
        var minY = Math.min(location1.getBlockY(), location2.getBlockY());
        var maxY = Math.max(location1.getBlockY(), location2.getBlockY());
        var minZ = Math.min(location1.getBlockZ(), location2.getBlockZ());
        var maxZ = Math.max(location1.getBlockZ(), location2.getBlockZ());

        // Check if the block is within the bounding box
        return block.getWorld().equals(world) &&
                block.getX() >= minX && block.getX() <= maxX &&
                block.getY() >= minY && block.getY() <= maxY &&
                block.getZ() >= minZ && block.getZ() <= maxZ;
    }

    public static boolean IsBlockBetweenLocations(Location location1, Location location2) {
        World world = location1.getWorld();

        var x1 = location1.getBlockX();
        var y1 = location1.getBlockY();
        var z1 = location1.getBlockZ();

        var x2 = location2.getBlockX();
        var y2 = location2.getBlockY();
        var z2 = location2.getBlockZ();

        var dx = Math.abs(x2 - x1);
        var dy = Math.abs(y2 - y1);
        var dz = Math.abs(z2 - z1);

        var sx = x1 < x2 ? 1 : -1;
        var sy = y1 < y2 ? 1 : -1;
        var sz = z1 < z2 ? 1 : -1;

        var err1 = dx - dy;
        var err2 = dx - dz;

        var x = x1;
        var y = y1;
        var z = z1;

        while (x != x2 || y != y2 || z != z2) {
            if (x != x1 || y != y1 || z != z1) {
                var block = world.getBlockAt(x, y, z);
                if(block.getType() != Material.AIR) return true;
            }

            var err1x2 = err1 * 2;
            var err2x2 = err2 * 2;

            if (err1x2 > -dy) {
                err1 -= dy;
                x += sx;
            }

            if (err1x2 < dx) {
                err1 += dx;
                y += sy;
            }

            if (err2x2 > -dz) {
                err2 -= dz;
                x += sx;
            }

            if (err2x2 < dx) {
                err2 += dx;
                z += sz;
            }
        }

        // Perform action on the last block (x2, y2, z2)
        return world.getBlockAt(x2, y2, z2).getType() != Material.AIR;
    }
}
