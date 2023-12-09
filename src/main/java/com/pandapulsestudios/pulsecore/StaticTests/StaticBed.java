package com.pandapulsestudios.pulsecore.StaticTests;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class StaticBed {
    public static boolean IsBedType(Block block){
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
