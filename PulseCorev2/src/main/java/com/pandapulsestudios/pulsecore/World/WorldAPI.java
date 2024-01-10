package com.pandapulsestudios.pulsecore.World;

import com.pandapulsestudios.pulsecore.FilesSystem.DirAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import com.pandapulsestudios.pulsecore.Time.TimeLock;
import org.bukkit.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class WorldAPI {
    public static boolean IsWorldLoaded(String worldName){
        if(worldName == null) return false;
        return Bukkit.getWorld(worldName) != null;
    }

    public static boolean IsWorldLoaded(UUID worldUUID){
        if(worldUUID == null) return false;
        return Bukkit.getWorld(worldUUID) != null;
    }

    public static World LoadWorld(String worldName){
        if(worldName == null) return null;
        return Bukkit.createWorld(new WorldCreator(worldName));
    }

    public static void UnloadWorld(String worldName){
        if(!IsWorldLoaded(worldName)) return;
        UnloadWorld(Bukkit.getWorld(worldName));
    }

    public static void UnloadWorld(UUID worldUUID){
        if(!IsWorldLoaded(worldUUID)) return;
       UnloadWorld(Bukkit.getWorld(worldUUID));
    }

    public static void UnloadWorld(World world){
        if(world == null || !IsWorldLoaded(world.getUID())) return;
        Bukkit.getServer().unloadWorld(world, true);
    }

    public static void DeleteWorld(String worldName){
        if(!IsWorldLoaded(worldName)) return;
        DeleteWorld(Bukkit.getWorld(worldName));
    }

    public static void DeleteWorld(UUID worldUUID){
        if(!IsWorldLoaded(worldUUID)) return;
        DeleteWorld(Bukkit.getWorld(worldUUID));
    }

    public static void DeleteWorld(World world){
        if(world == null) return;
        DeleteWorld(world.getWorldFolder());
    }

    public static void DeleteWorld(File worldSource){
        if(worldSource == null) return;
        DirAPI.DeleteAllFiles(worldSource);
    }

    public static World CreateCopy(String baseWorldName, String newWorldName, boolean deleteOldWorld, ArrayList<String> ignore){
        var rootDirectory = Bukkit.getServer().getWorldContainer().getAbsolutePath();

        if(!IsWorldLoaded(baseWorldName) || IsWorldLoaded(newWorldName)) return null;
        Bukkit.broadcastMessage(rootDirectory + "/" + baseWorldName + ":" + rootDirectory + "/" + newWorldName);
        var baseWorldFile = new File(rootDirectory + "/" + baseWorldName);
        var newWorldFile = new File(rootDirectory + "/" + newWorldName);
        ignore = ignore == null ? new ArrayList<>(Arrays.asList("uid.dat", "session.dat")) : ignore;
        DirAPI.CopyAllFiles(baseWorldFile, newWorldFile, ignore);
        if(deleteOldWorld) DirAPI.DeleteAllFiles(baseWorldFile);
        return LoadWorld(newWorldName);
    }

    public static void WorldLockPlayerAction(String worldName, PlayerAction playerAction, boolean playerActionState){
        WorldLockPlayerAction(Bukkit.getWorld(worldName), playerAction, playerActionState);
    }
    public static void WorldLockPlayerAction(World world, PlayerAction playerAction, boolean playerActionState){
        if(!PulseCoreMain.playerActionLock.containsKey(world)) PulseCoreMain.playerActionLock.put(world, new ArrayList<>());
        if(!playerActionState){
            if(PulseCoreMain.playerActionLock.get(world).contains(playerAction)) return;
            PulseCoreMain.playerActionLock.get(world).add(playerAction);
        }else{
            PulseCoreMain.playerActionLock.get(world).remove(playerAction);
        }
    }

    public static void TimeLock(String world, TimeLock timeLock){ TimeLock(Bukkit.getWorld(world), timeLock);}
    public static void TimeLock(World world, TimeLock timeLock){
        if(timeLock == null) PulseCoreMain.timeLockLock.remove(world);
        else PulseCoreMain.timeLockLock.put(world, timeLock);
    }

    public static void DifficultyLock(String world, Difficulty difficulty){ DifficultyLock(Bukkit.getWorld(world), difficulty);}
    public static void DifficultyLock(World world, Difficulty difficulty){
        if(difficulty == null) PulseCoreMain.difficultyLock.remove(world);
        else PulseCoreMain.difficultyLock.put(world, difficulty);
    }

    public static void GameModeLock(String world, GameMode gameMode){ GameModeLock(Bukkit.getWorld(world), gameMode);}
    public static void GameModeLock(World world, GameMode gameMode){
        if(gameMode == null) PulseCoreMain.gameModeLock.remove(world);
        else PulseCoreMain.gameModeLock.put(world, gameMode);
    }

    public static void HeartLock(String world, int heartLevel){ HeartLock(Bukkit.getWorld(world), heartLevel); }
    public static void HeartLock(World world, int heartLevel){
        if(heartLevel <= 0) PulseCoreMain.heartLockLock.remove(world);
        else PulseCoreMain.heartLockLock.put(world, Math.min(20, heartLevel));
    }

    public static void HungerLock(String world, int hungerLevel){ HungerLock(Bukkit.getWorld(world), hungerLevel); }
    public static void HungerLock(World world, int hungerLevel){
        if(hungerLevel <= 0) PulseCoreMain.hungerLockLock.remove(world);
        else PulseCoreMain.hungerLockLock.put(world, Math.min(20, hungerLevel));
    }

    public static void SaturationLock(String world, int saturationLevel){ SaturationLock(Bukkit.getWorld(world), saturationLevel); }
    public static void SaturationLock(World world, int saturationLevel){
        if(saturationLevel <= 0) PulseCoreMain.saturationLockLock.remove(world);
        else PulseCoreMain.saturationLockLock.put(world, Math.min(20, saturationLevel));
    }

    public static void HandleLoop(){
        for(var world : PulseCoreMain.timeLockLock.keySet()) world.setTime(PulseCoreMain.timeLockLock.get(world).time);
        for(var world : PulseCoreMain.difficultyLock.keySet()) world.setDifficulty(PulseCoreMain.difficultyLock.get(world));
        for(var player : Bukkit.getOnlinePlayers()){
            for(var world : PulseCoreMain.gameModeLock.keySet())
                if(world.getPlayers().contains(player)) player.setGameMode(PulseCoreMain.gameModeLock.get(world));
            for(var world : PulseCoreMain.heartLockLock.keySet())
                if(world.getPlayers().contains(player)) player.setHealth(PulseCoreMain.heartLockLock.get(world));
            for(var world : PulseCoreMain.hungerLockLock.keySet())
                if(world.getPlayers().contains(player)) player.setFoodLevel(PulseCoreMain.hungerLockLock.get(world));
            for(var world : PulseCoreMain.saturationLockLock.keySet())
                if(world.getPlayers().contains(player)) player.setSaturation(PulseCoreMain.saturationLockLock.get(world));
        }
    }
}
