package com.pandapulsestudios.pulsecore.WorldAPI.Interface;

import com.pandapulsestudios.pulsecore.WorldAPI.API.WorldAPI;
import org.bukkit.World;

import java.util.ArrayList;

public interface PulseWorld {
    default String defaultWorldName() {
        return getClass().getSimpleName();
    }

    default boolean IsWorldLoaded(String overrideWorldName){
        return WorldAPI.IsWorldLoaded(overrideWorldName == null ? defaultWorldName() : overrideWorldName);
    }

    default World LoadWorld(String overrideWorldName){
        return WorldAPI.LoadWorld(overrideWorldName == null ? defaultWorldName() : overrideWorldName);
    }

    default void UnLoadWorld(String overrideWorldName){
        WorldAPI.UnloadWorld(overrideWorldName == null ? defaultWorldName() : overrideWorldName);
    }

    default void DeleteWorld(String overrideWorldName){
        WorldAPI.DeleteWorld(overrideWorldName == null ? defaultWorldName() : overrideWorldName);
    }

    default World CloneWorldFromBase(String newWorldName, boolean deleteOldWorld, ArrayList<String> ignore){
        return WorldAPI.CreateCopy(defaultWorldName(), newWorldName, deleteOldWorld, ignore);
    }
}
