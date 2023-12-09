package com.pandapulsestudios.pulsecore.World;

import com.pandapulsestudios.pulsecore.Files.DirAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.Time.TimeLock;
import org.bukkit.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class WorldAPI {
    public static World CreateCloneFromWorld(String sourceName, String targetName, String worldName, boolean tryDeleteOld, boolean tryDeleteNew, ArrayList<String> ignore){
        if(sourceName == null || targetName == null || worldName == null) return null;
        var rootDirectory = Bukkit.getServer().getWorldContainer().getAbsolutePath();
        return CreateCloneFromWorld(new File(rootDirectory + "/" + sourceName), new File(rootDirectory + "/" + targetName), worldName, tryDeleteOld, tryDeleteNew, ignore);
    }

    public static World CreateCloneFromWorld(File source, File target, String worldName, boolean tryDeleteOld, boolean tryDeleteNew, ArrayList<String> ignore){
        if(source == null || target == null) return null;
        if(ignore == null) ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.dat"));
        if(IsWorldLoaded(worldName)) return null;
        if(tryDeleteOld) DeleteWorld(target);
        if(tryDeleteNew) DeleteWorld(source);
        DirAPI.CopyAllFiles(source, target, ignore);
        return LoadWorld(worldName);
    }

    public static boolean DeleteWorld(String source){
        if(source == null) return false;
        return DeleteWorld(Bukkit.getWorld(source));
    }

    public static boolean DeleteWorld(World source){
        if(source == null) return false;
        return DeleteWorld(source.getWorldFolder());
    }

    public static boolean DeleteWorld(File source){
        if(source == null) return false;
        DirAPI.DeleteAllFiles(source);
        return(source.delete());
    }

    public static boolean IsWorldLoaded(String worldName){
        if(worldName == null) return false;
        return Bukkit.getWorld(worldName) == null;
    }

    public static boolean IsWorldLoaded(UUID worldUUID){
        if(worldUUID == null) return false;
        return Bukkit.getWorld(worldUUID) == null;
    }

    public static boolean UnloadWorld(String worldName){
        return UnloadWorld(Bukkit.getWorld(worldName));
    }

    public static boolean UnloadWorld(World world){
        if(world == null) return false;
        if(IsWorldLoaded(world.getUID())) return false;
        Bukkit.getServer().unloadWorld(world, true);
        return true;
    }

    public static World LoadWorld(String worldName){
        if(worldName == null) return null;
        return Bukkit.createWorld(new WorldCreator(worldName));
    }//

    public static void TimeLock(String world, TimeLock timeLock){ TimeLock(Bukkit.getWorld(world), timeLock);}
    public static void TimeLock(World world, TimeLock timeLock){
        if(timeLock == null) PulseCore.timeLockLock.remove(world);
        else PulseCore.timeLockLock.put(world, timeLock);
    }

    public static void DifficultyLock(String world, Difficulty difficulty){ DifficultyLock(Bukkit.getWorld(world), difficulty);}
    public static void DifficultyLock(World world, Difficulty difficulty){
        if(difficulty == null) PulseCore.difficultyLock.remove(world);
        else PulseCore.difficultyLock.put(world, difficulty);
    }

    public static void GameModeLock(String world, GameMode gameMode){ GameModeLock(Bukkit.getWorld(world), gameMode);}
    public static void GameModeLock(World world, GameMode gameMode){
        if(gameMode == null) PulseCore.gameModeLock.remove(world);
        else PulseCore.gameModeLock.put(world, gameMode);
    }

    public static void HeartLock(String world, int heartLevel){}
    public static void HeartLock(World world, int heartLevel){
        if(heartLevel <= 0) PulseCore.heartLockLock.remove(world);
        else PulseCore.heartLockLock.put(world, Math.min(20, heartLevel));
    }

    public static void HungerLock(String world, int hungerLevel){}
    public static void HungerLock(World world, int hungerLevel){
        if(hungerLevel <= 0) PulseCore.hungerLockLock.remove(world);
        else PulseCore.hungerLockLock.put(world, Math.min(20, hungerLevel));
    }

    public static void SaturationLock(String world, int saturationLevel){}
    public static void SaturationLock(World world, int saturationLevel){
        if(saturationLevel <= 0) PulseCore.saturationLockLock.remove(world);
        else PulseCore.saturationLockLock.put(world, Math.min(20, saturationLevel));
    }

    public static void HandleLoop(){
        for(var world : PulseCore.timeLockLock.keySet()) world.setTime(PulseCore.timeLockLock.get(world).time);
        for(var world : PulseCore.difficultyLock.keySet()) world.setDifficulty(PulseCore.difficultyLock.get(world));
        for(var player : Bukkit.getOnlinePlayers()){
            for(var world : PulseCore.gameModeLock.keySet())
                if(world.getPlayers().contains(player)) player.setGameMode(PulseCore.gameModeLock.get(world));
            for(var world : PulseCore.heartLockLock.keySet())
                if(world.getPlayers().contains(player)) player.setHealth(PulseCore.heartLockLock.get(world));
            for(var world : PulseCore.hungerLockLock.keySet())
                if(world.getPlayers().contains(player)) player.setFoodLevel(PulseCore.hungerLockLock.get(world));
            for(var world : PulseCore.saturationLockLock.keySet())
                if(world.getPlayers().contains(player)) player.setSaturation(PulseCore.saturationLockLock.get(world));
        }
    }
}
