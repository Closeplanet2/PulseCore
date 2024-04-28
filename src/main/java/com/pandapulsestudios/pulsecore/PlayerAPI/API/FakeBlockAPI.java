package com.pandapulsestudios.pulsecore.PlayerAPI.API;

import com.pandapulsestudios.pulsecore.PlayerAPI.Object.FakeBlock;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FakeBlockAPI {
    public static void CreateFakeBlock(String id, Location location, Material material, Player... players){
        var fakeBlock = new FakeBlock(location, material);
        for(var player : players) fakeBlock.addPlayer(player);
        PulseCore.FakeBlocks.put(id, fakeBlock);
    }

    public static void RemovePlayerFromFakeBlock(Player player, Location location){
        for(var fakeBlock : PulseCore.FakeBlocks.values()){
            if(!fakeBlock.IsPlayerAndLocation(player, location)) continue;
            fakeBlock.removePlayer(player);
        }
    }

    public static void RemovePlayerFromFakeBlock(Player player, String id){
        var fakeBlock = PulseCore.FakeBlocks.getOrDefault(id, null);
        if(fakeBlock != null) fakeBlock.removePlayer(player);
    }

    public static void RemoveFakeBlock(Location location){
        for(var fakeBlock : PulseCore.FakeBlocks.values()){
            if(!fakeBlock.IsLocation(location)) continue;
            fakeBlock.RemoveAllPlayers();
        }
    }

    public static void RemoveFakeBlock(String id){
        var fakeBlock = PulseCore.FakeBlocks.getOrDefault(id, null);
        if(fakeBlock != null) fakeBlock.RemoveAllPlayers();
    }

    public static Material ReturnMaterialForPlayer(Player player, Location location){
        for(var fakeBlock : PulseCore.FakeBlocks.values()){
            if(!fakeBlock.IsPlayerAndLocation(player, location)) continue;
            return fakeBlock.getMaterial();
        }
        return null;
    }

    public static Material ReturnMaterialForID(String id){
        var fakeBlock = PulseCore.FakeBlocks.getOrDefault(id, null);
        return fakeBlock != null ? fakeBlock.getMaterial() : null;
    }
}
