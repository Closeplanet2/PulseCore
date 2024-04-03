package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

//TODO add world to limit mask to world
//TODO add region to limit mask to region
//TODO same chunk
public class EntityMask {
    private final Player player;
    private final boolean deleteMaskOnNull;
    private final double minDistance;
    private final double maxDistance;
    private final EntityMaskType entityMaskType;
    private final ArrayList<EntityType> entityTypeExceptions;
    private final ArrayList<UUID> uuidExceptions;
    private ArrayList<Entity> lastFrameEntities = new ArrayList<>();

    public EntityMask(Player player, boolean deleteMaskOnNull, double minDistance, double maxDistance,
                      EntityMaskType entityMaskType, ArrayList<EntityType> entityTypeExceptions, ArrayList<UUID> uuidExceptions){
        this.player = player;
        this.deleteMaskOnNull = deleteMaskOnNull;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.entityMaskType = entityMaskType;
        this.entityTypeExceptions = entityTypeExceptions;
        this.uuidExceptions = uuidExceptions;
    }

    public void AddToExceptions(ArrayList<EntityType> entityTypeExceptions){
        for(var type : entityTypeExceptions) if(!entityTypeExceptions.contains(type)) entityTypeExceptions.add(type);
    }

    public boolean UpdateEntityMask(){
        if(player == null || !player.isOnline()) return deleteMaskOnNull;
        player.sendMessage("0");

        var newEntityStates = new ArrayList<Entity>();

        var playerWorld = player.getWorld();

        player.sendMessage("0.0");
        for(var entity : playerWorld.getEntities()){
            player.sendMessage("1");
            if(entityTypeExceptions.contains(entity.getType())) continue;
            player.sendMessage("2");
            if(uuidExceptions.contains(entity.getUniqueId())) continue;
            player.sendMessage("3");
            if(!IsCorrectForMaskType(entity)) continue;
            var distanceToEntity = player.getLocation().distance(entity.getLocation());
            player.sendMessage("4");
            if(distanceToEntity < minDistance || distanceToEntity > maxDistance) continue;
            player.sendMessage("5");
            newEntityStates.add(entity);
        }

        for(var entity : lastFrameEntities) VanishAPI.ShowTargetToViewer(entity, player);
        for(var entity : newEntityStates) VanishAPI.HideTargetFromViewer(entity, player);
        lastFrameEntities = newEntityStates;
        return false;
    }

    private boolean IsCorrectForMaskType(Entity entity){
        if(entityMaskType == EntityMaskType.Entity) return true;
        if(entityMaskType == EntityMaskType.LivingEntity && entity instanceof LivingEntity) return true;
        if(entityMaskType == EntityMaskType.Player && entity instanceof Player) return true;
        return false;
    }

    public void CancelMask(){
        PulseCore.entityMasksPerPlayer.remove(player.getUniqueId());
    }

    public static Builder builder(){return new Builder();}
    public static class Builder {
        private boolean deleteMaskOnNull = false;
        private double minDistance = 0;
        private double maxDistance = 5;
        private EntityMaskType entityMaskType = EntityMaskType.Player;
        private ArrayList<UUID> uuidExceptions = new ArrayList<>();
        private ArrayList<EntityType> entityTypeExceptions = new ArrayList<>();

        public Builder entityTypeExceptions(ArrayList<EntityType> entityTypeExceptions){
            for(var type : entityTypeExceptions) if(!entityTypeExceptions.contains(type)) entityTypeExceptions.add(type);
            return this;
        }

        public Builder uuidExceptions(ArrayList<UUID> uuidExceptions){
            for(var type : entityTypeExceptions) if(!entityTypeExceptions.contains(type)) entityTypeExceptions.add(type);
            return this;
        }

        public Builder deleteMaskOnNull(boolean deleteMaskOnNull){
            this.deleteMaskOnNull = deleteMaskOnNull;
            return this;
        }

        public Builder minDistance(double minDistance){
            this.minDistance = minDistance;
            return this;
        }

        public Builder maxDistance(double maxDistance){
            this.maxDistance = maxDistance;
            return this;
        }

        public Builder entityMaskType(EntityMaskType entityMaskType){
            this.entityMaskType = entityMaskType;
            return this;
        }

        public EntityMask CreateMask(Player player){
            var storedEntityMask = PulseCore.entityMasksPerPlayer.getOrDefault(player.getUniqueId(), null);
            if(storedEntityMask != null){
                storedEntityMask.AddToExceptions(entityTypeExceptions);
                return storedEntityMask;
            }
            var createdEntityMask = new EntityMask(player, deleteMaskOnNull, minDistance, maxDistance, entityMaskType, entityTypeExceptions, uuidExceptions);
            PulseCore.entityMasksPerPlayer.put(player.getUniqueId(), createdEntityMask);
            return createdEntityMask;
        }
    }


}
