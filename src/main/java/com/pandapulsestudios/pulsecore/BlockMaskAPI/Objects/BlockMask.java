package com.pandapulsestudios.pulsecore.BlockMaskAPI.Objects;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

//TODO keep trail revealed to the player
//TODO Jumping - does the hole mask turn off when the player is jumping? Is certain blocks revealed when the player us jumping?
//TODO Crouching - does the hole mask turn off when the player is crouching? Is certain blocks revealed when the player us crouching?
//TODO Events - toggle mask on certain blocks when events happen
//TODO Pause mask for a certain amount of time
//TODO make rest of world blank and reveal based on the players location
public class BlockMask {

    private final Player player;
    private final boolean deleteMaskOnNull;
    private final boolean ignoreAir;
    private final boolean resetLastFrames;
    private final boolean keepTrailTheSame;
    private final double minDistance;
    private final double maxX;
    private final double maxY;
    private final double maxZ;
    private final HashMap<Material, Material> blockExceptions;

    private HashMap<Vector, BlockState> lastFrameBlockStates = new HashMap<>();
    private HashMap<Vector, BlockState> visitedTrailLocations = new HashMap<>();

    public BlockMask(Player player, boolean deleteMaskOnNull, boolean ignoreAir, boolean resetLastFrames, boolean keepTrailTheSame, double minDistance, double maxX, double maxY, double maxZ, HashMap<Material, Material> blockExceptions){
        this.player = player;
        this.deleteMaskOnNull = deleteMaskOnNull;
        this.resetLastFrames = resetLastFrames;
        this.keepTrailTheSame = keepTrailTheSame;
        this.minDistance = minDistance;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.ignoreAir = ignoreAir;
        this.blockExceptions = blockExceptions;
    }

    public void AddToExceptions(HashMap<Material, Material> blockExceptions){
        for(var key : blockExceptions.keySet()) this.blockExceptions.put(key, blockExceptions.get(key));
    }

    public boolean UpdateBlockMask(){
        if(player == null || !player.isOnline()) return deleteMaskOnNull;

        var previousFrameStates = new HashMap<Vector, BlockState>();
        var newFrameBlockStates = new HashMap<Vector, BlockState>();

        for(var x = player.getLocation().getX() - maxX; x < player.getLocation().getX() + maxX; x++){
            for(var y = player.getLocation().getY() - maxY; y < player.getLocation().getY() + maxY; y++){
                for(var z = player.getLocation().getZ() - maxZ; z < player.getLocation().getZ() + maxZ; z++){

                    var affectedBlock = new Location(player.getWorld(), x, y, z).getBlock();
                    var affectedBlockVector = affectedBlock.getLocation().toVector();
                    if(affectedBlock.getLocation().distance(player.getLocation()) < minDistance) continue;

                    if(affectedBlock.getType() == Material.AIR && ignoreAir) continue;
                    var newBlockType = blockExceptions.getOrDefault(affectedBlock.getType(), Material.BARRIER);
                    if(newBlockType == null) continue;

                    previousFrameStates.put(affectedBlockVector, affectedBlock.getState());

                    var newBlockState = affectedBlock.getState();
                    newBlockState.setType(newBlockType);
                    newFrameBlockStates.put(affectedBlockVector, newBlockState);
                }
            }
        }

        if(resetLastFrames){
            for(var vector : lastFrameBlockStates.keySet()){
                if(!newFrameBlockStates.containsKey(vector) && !visitedTrailLocations.containsKey(vector)) newFrameBlockStates.put(vector, lastFrameBlockStates.get(vector));
            }
        }

        player.sendBlockChanges(newFrameBlockStates.values());
        lastFrameBlockStates = previousFrameStates;
        return false;
    }

    public void CancelMask(){
        PulseCore.BlockMasks.remove(player.getUniqueId());
        player.sendBlockChanges(lastFrameBlockStates.values());
        player.sendBlockChanges(visitedTrailLocations.values());
    }

    public static Builder builder(){return new Builder();}
    public static class Builder {
        private HashMap<Material, Material> blockExceptions = new HashMap<>();
        private boolean deleteMaskOnNull = false;
        private boolean ignoreAir = true;
        private boolean resetLastFrames = true;
        private boolean keepTrailTheSame = false;
        private double minDistance = 0;
        private double maxX = 5;
        private double maxY = 5;
        private double maxZ = 5;

        public Builder blockExceptions(HashMap<Material, Material> blockExceptions){
            for(var key : blockExceptions.keySet()) this.blockExceptions.put(key, blockExceptions.get(key));
            return this;
        }

        public Builder blockExceptions(Material target, Material view){
            blockExceptions.put(target, view);
            return this;
        }

        public Builder deleteMaskOnNull(boolean deleteMaskOnNull){
            this.deleteMaskOnNull = deleteMaskOnNull;
            return this;
        }

        public Builder ignoreAir(boolean ignoreAir){
            this.ignoreAir = ignoreAir;
            return this;
        }

        public Builder resetLastFrames(boolean resetLastFrames){
            this.resetLastFrames = resetLastFrames;
            return this;
        }

        public Builder keepTrailTheSame(boolean keepTrailTheSame){
            this.keepTrailTheSame = keepTrailTheSame;
            return this;
        }

        public Builder minDistance(double minDistance){
            this.minDistance = minDistance;
            return this;
        }

        public Builder maxX(double maxX){
            this.maxX = maxX;
            return this;
        }

        public Builder maxY(double maxY){
            this.maxY = maxY;
            return this;
        }

        public Builder maxZ(double maxZ){
            this.maxZ = maxZ;
            return this;
        }

        public BlockMask CreateMask(Player player){
            var storedBlockMask = PulseCore.BlockMasks.getOrDefault(player.getUniqueId(), null);
            if(storedBlockMask != null){
                storedBlockMask.AddToExceptions(blockExceptions);
                return storedBlockMask;
            }
            var createdBlockMask = new BlockMask(player, deleteMaskOnNull, ignoreAir, resetLastFrames, keepTrailTheSame, minDistance, maxX, maxY, maxZ, blockExceptions);
            PulseCore.BlockMasks.put(player.getUniqueId(), createdBlockMask);
            return createdBlockMask;
        }
    }
}
