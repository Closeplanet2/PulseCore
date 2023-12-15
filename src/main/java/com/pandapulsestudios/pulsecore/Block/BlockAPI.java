package com.pandapulsestudios.pulsecore.Block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockAPI {
    public static int CountBlocks(Player player, int radius, Material... materials){ return CountBlocks(player.getLocation(), radius, materials); }
    public static int CountBlocks(World world, int x, int y, int z, int radius, Material... materials){ return CountBlocks(new Location(world, x, y, z), radius, materials); }

    public static int CountBlocks(Location location, int radius, Material... materials){
        var count = 0;
        for(var block : ReturnAllBlocksInRadius(location, radius == 0 ? 1 : radius)){
            count += Arrays.stream(materials).toList().contains(block.getType()) ? 1 : 0;
        }
        return count;
    }

    public static List<Block> ReturnAllBlocksInRadius(Location location, int radius){
        var blocksInRadius = new ArrayList<Block>();
        if(location.getWorld() == null) return blocksInRadius;
        for(var x = location.getBlockX() - radius; x < location.getBlockX() + radius; x++){
            for(var y = location.getBlockY() - radius; y < location.getBlockY() + radius; y++){
                for(var z = location.getBlockZ() - radius; z < location.getBlockZ() + radius; z++){
                    blocksInRadius.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocksInRadius;
    }
}
