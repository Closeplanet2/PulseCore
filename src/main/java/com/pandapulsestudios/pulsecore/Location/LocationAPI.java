package com.pandapulsestudios.pulsecore.Location;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
        for(var pulseLocationName : PulseCore.customLocations.keySet()){
            var pulseLocation = PulseCore.customLocations.get(pulseLocationName);
            if(pulseLocation.locationName().equals(locationName)) pulseLocation.TeleportPlayers(players);
        }
    }

    public static void TeleportLocation(String locationName, Entity... entities){
        for(var pulseLocationName : PulseCore.customLocations.keySet()){
            var pulseLocation = PulseCore.customLocations.get(pulseLocationName);
            if(pulseLocation.locationName().equals(locationName)) pulseLocation.TeleportPlayers(entities);
        }
    }

    public static List<PulseLocation> ReturnAllPulseLocations(Location location, boolean useDistanceForEvent){
        var data = new ArrayList<PulseLocation>();
        for(var pulseLocationName : PulseCore.customLocations.keySet()){
            var pulseLocation = PulseCore.customLocations.get(pulseLocationName);
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

    public static Location FindMidPointBetween2Locations(Location a, Location b){
        if(a.getWorld() != b.getWorld()) return null;
        var newX = (a.getX() + b.getX()) / 2;
        var newY = (a.getY() + b.getY()) / 2;
        var newZ = (a.getZ() + b.getZ()) / 2;
        return new Location(a.getWorld(), newX, newY, newZ);
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

    public Location[] ReturnAllLocationsBetweenTwoLocations(Location start, Location end, double spacing){
        var startVec = start.toVector();
        var endVec = end.toVector();
        var direction = endVec.clone().subtract(startVec).normalize();

        var distance = start.distance(end);
        var numberOfPoints = (int) Math.ceil(distance / spacing);

        var locations = new Location[numberOfPoints];
        for (var i = 0; i < numberOfPoints; i++) {
            Vector pointVec = startVec.clone().add(direction.clone().multiply(spacing * i));
            locations[i] = pointVec.toLocation(start.getWorld());
        }
        return locations;
    }

    public static List<Location> GetAllLocationsInCubeArea(Location a, Location b) {
        List<Location> locations = new ArrayList<>();

        int minX = Math.min(a.getBlockX(), b.getBlockX());
        int minY = Math.min(a.getBlockY(), b.getBlockY());
        int minZ = Math.min(a.getBlockZ(), b.getBlockZ());

        int maxX = Math.max(a.getBlockX(), b.getBlockX());
        int maxY = Math.max(a.getBlockY(), b.getBlockY());
        int maxZ = Math.max(a.getBlockZ(), b.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    locations.add(new Location(a.getWorld(), x, y, z));
                }
            }
        }

        return locations;
    }
}
