package com.pandapulsestudios.pulsecore.Block;

import com.pandapulsestudios.pulsecore.StaticTests.StaticBed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BlockAPI {
    public static int CountBlocks(Player player, Material material, int radius){
        if(player == null) return 0;
        return CountBlocks(player.getLocation(), material, radius);
    }

    public static int CountBlocks(Location location, Material material, int radius){
        if(material == null || location == null) return 0;
        if(location.getWorld() == null) return 0;
        if(radius <= 0) radius = 1;

        var count = 0;
        for(var x = location.getBlockX() - radius; x < location.getBlockX() + radius; x++){
            for(var y = location.getBlockY() - radius; y < location.getBlockY() + radius; y++){
                for(var z = location.getBlockZ() - radius; z < location.getBlockZ() + radius; z++){
                    var block = location.getWorld().getBlockAt(x, y, z);
                    if(block.getType() == material) count+=1;
                }
            }
        }

        return count;
    }

    public static List<Block> FindBedsNearby(Player player, int radius){
        if(player == null) return new ArrayList<>();
        return FindBedsNearby(player.getLocation(), radius);
    }

    public static List<Block> FindBedsNearby(Location center, int radius){
        if(center == null) return null;
        if(radius <= 0) radius = 1;
        var beds = new ArrayList<Block>();

        for(var x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++){
            for(var y = center.getBlockY() - radius; y <= center.getBlockY() + radius; y++){
                for(var z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++){
                    if(center.getWorld() == null) continue;
                    var block = center.getWorld().getBlockAt(x, y, z);
                    if(!StaticBed.IsBedType(block)) continue;
                    beds.add(block);
                }
            }
        }

        return beds;
    }
}
