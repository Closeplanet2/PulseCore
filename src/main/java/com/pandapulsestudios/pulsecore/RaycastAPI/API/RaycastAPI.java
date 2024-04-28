package com.pandapulsestudios.pulsecore.RaycastAPI.API;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.Set;

public class RaycastAPI {
    /**
     * Raycast from the player ignoring the given materials
     * @param player
     * @param range
     * @param particle
     * @param materials
     * @return
     */
    public static Block rayCastFromPlayerIgnore(Player player, int range, Particle particle, Set<Material> materials) {
        var playerDirection = player.getLocation().getDirection();
        var blockIterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), playerDirection, 0, range);
        Block lastBlock = null;
        while (blockIterator.hasNext()) {
            lastBlock = blockIterator.next();
            if (!materials.contains(lastBlock.getType())) break;
            if (particle != null) player.getWorld().spawnParticle(particle, lastBlock.getLocation().add(0.5, 0.5, 0.5), 1);
        }
        return lastBlock;
    }

    /**
     * Hit the first block of the given material
     * @param player
     * @param range
     * @param particle
     * @param materials
     * @return
     */
    public static Block rayCastFromPlayerMust(Player player, int range, Particle particle, Set<Material> materials) {
        var playerDirection = player.getLocation().getDirection();
        var blockIterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), playerDirection, 0, range);
        Block foundBlock = null;
        while (blockIterator.hasNext()) {
            Block nextBlock = blockIterator.next();
            if (materials.contains(nextBlock.getType())) {
                foundBlock = nextBlock;
                if (particle != null) {
                    player.getWorld().spawnParticle(particle, nextBlock.getLocation().add(0.5, 0.5, 0.5), 1);
                }
                break;
            }
        }
        return foundBlock;
    }
}
