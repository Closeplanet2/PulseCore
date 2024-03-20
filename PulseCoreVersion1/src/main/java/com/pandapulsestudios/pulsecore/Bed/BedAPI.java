package com.pandapulsestudios.pulsecore.Bed;

import com.pandapulsestudios.pulsecore.Block.API.BlockAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BedAPI {
    public static List<Block> FindBedsNearby(Player player, int radius){ return FindBedsNearby(player.getLocation(), radius); }
    public static List<Block> FindBedsNearby(World world, int x, int y, int z, int radius){ return FindBedsNearby(new Location(world, x, y, z), radius); }

    public static List<Block> FindBedsNearby(Location location, int radius){
        var foundInformation = new ArrayList<Block>();
        for(var block : BlockAPI.ReturnAllBlocksInRadius(location, radius)){
            if(!BlockIsBed(block)) continue;
            foundInformation.add(block);
        }
        return foundInformation;
    }

    public static boolean BlockIsBed(Block block){
        return block.getType() == Material.RED_BED || block.getType() == Material.BLACK_BED ||
                block.getType() == Material.BLUE_BED || block.getType() == Material.BROWN_BED ||
                block.getType() == Material.CYAN_BED || block.getType() == Material.GRAY_BED ||
                block.getType() == Material.GREEN_BED || block.getType() == Material.LIGHT_BLUE_BED ||
                block.getType() == Material.LIGHT_GRAY_BED || block.getType() == Material.LIME_BED ||
                block.getType() == Material.MAGENTA_BED || block.getType() == Material.ORANGE_BED ||
                block.getType() == Material.PINK_BED || block.getType() == Material.PURPLE_BED ||
                block.getType() == Material.WHITE_BED || block.getType() == Material.YELLOW_BED;
    }
}
