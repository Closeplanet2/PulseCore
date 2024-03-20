package com.pandapulsestudios.pulsecore.Block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockAPI {
    /**
     * Return all blocks of given radius of the location provided
     * @param location Location to center the search on
     * @param radius Radius from the given location to check
     * @param gap Gap for the blocks to check (1 will check every block, 2 will check every other block etc)
     * @param materials Materials of the blocks to check. Provide 0 to search every block
     * @return ArrayList<Block>
     */
    public static ArrayList<Block> ReturnAllBlocksInRadius(Location location, int radius, int gap, Material... materials){
        var blocksInRadius = new ArrayList<Block>();
        if(location.getWorld() == null) return blocksInRadius;
        for(var x = location.getBlockX() - radius; x < location.getBlockX() + radius; x += gap){
            for(var y = location.getBlockY() - radius; y < location.getBlockY() + radius; y += gap){
                for(var z = location.getBlockZ() - radius; z < location.getBlockZ() + radius; z += gap){
                    var block = location.getWorld().getBlockAt(x, y, z);
                    if(materials.length == 0) blocksInRadius.add(block);
                    else if(Arrays.asList(materials).contains(block.getType())) blocksInRadius.add(block);
                }
            }
        }
        return blocksInRadius;
    }
}
