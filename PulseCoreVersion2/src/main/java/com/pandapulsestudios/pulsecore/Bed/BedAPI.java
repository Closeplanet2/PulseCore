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
    private static final Material[] bedMaterials = { Material.RED_BED, Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED, Material.CYAN_BED, Material.GRAY_BED,
            Material.GREEN_BED, Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED, Material.LIME_BED, Material.MAGENTA_BED, Material.ORANGE_BED, Material.PINK_BED,
            Material.PURPLE_BED, Material.WHITE_BED, Material.YELLOW_BED};
    public static List<Block> FindBedsNearby(Player player, int radius){ return FindBedsNearby(player.getLocation(), radius); }
    public static List<Block> FindBedsNearby(World world, int x, int y, int z, int radius){ return FindBedsNearby(new Location(world, x, y, z), radius); }
    public static List<Block> FindBedsNearby(Location location, int radius){ return new ArrayList<Block>(BlockAPI.ReturnAllBlocksInRadius(location, radius, 1, bedMaterials)); }
}
