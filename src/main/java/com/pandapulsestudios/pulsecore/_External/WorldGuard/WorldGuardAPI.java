package com.pandapulsestudios.pulsecore._External.WorldGuard;

import com.pandapulsestudios.pulsecore.Java.PluginAPI;
import com.pandapulsestudios.pulsecore.Java.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldGuardAPI {

    private static World ConvertWorld(org.bukkit.World world){
        return BukkitAdapter.adapt(world);
    }

    private static RegionContainer ReturnRegionContainer(){
        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
        return WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    private static RegionManager ReturnRegionManager(org.bukkit.World world){
        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
        var regionContainer = ReturnRegionContainer();
        if(regionContainer == null) return null;
        return regionContainer.get(ConvertWorld(world));
    }

    public static class REGION{
        public static ProtectedRegion ReturnProtectedRegion(org.bukkit.World world, String regionName){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
            var regionManager = ReturnRegionManager(world);
            if(regionManager == null) return null;
            return regionManager.getRegion(regionName);
        }

        public static ProtectedRegion CreateCuboidProtectedRegion(String regionName, org.bukkit.World world, List<BlockVector3> points, boolean addToManager){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
            var region = new ProtectedCuboidRegion(regionName, points.get(0), points.get(1));
            if(addToManager) AddProtectedRegion(world, region);
            return region;
        }

        public static ProtectedRegion CreatePolygonProtectedRegion(String regionName, org.bukkit.World world, int minY, int maxY, List<BlockVector2> points, boolean addToManager){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
            var region = new ProtectedPolygonalRegion(regionName, points, minY, maxY);
            if(addToManager) AddProtectedRegion(world, region);
            return region;
        }

        public static ProtectedRegion CreateglobalProtectedRegion(String regionName, org.bukkit.World world, boolean addToManager){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
            var region = new GlobalProtectedRegion(regionName);
            if(addToManager) AddProtectedRegion(world, region);
            return region;
        }

        public static void AddProtectedRegion(org.bukkit.World world, ProtectedRegion protectedRegion){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return;
            if(protectedRegion == null) return;
            var regionManager = ReturnRegionManager(world);
            if(regionManager == null) return;
            regionManager.addRegion(protectedRegion);
        }

        public static void RemoveProtectedRegion(org.bukkit.World world, String regionName){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return;
            var regionManager = ReturnRegionManager(world);
            if(regionManager == null) return;
            regionManager.removeRegion(regionName);
        }

        public static boolean IsLocationInRegion(org.bukkit.World world, String regionName, Location location){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            return IsLocationInRegion(ReturnProtectedRegion(world, regionName), location);
        }

        public static boolean IsLocationInRegion(ProtectedRegion protectedRegion, Location location){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            if(protectedRegion == null) return false;
            return protectedRegion.contains(BlockVector3.at(location.getX(), location.getY(), location.getZ()));
        }

        public static boolean DoesRegionOverlap(org.bukkit.World world, String regionNameA, String regionNameB){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            return DoesRegionOverlap(ReturnProtectedRegion(world, regionNameA), ReturnProtectedRegion(world, regionNameB));
        }

        public static boolean DoesRegionOverlap(ProtectedRegion protectedRegionA, ProtectedRegion protectedRegionB){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            if(protectedRegionA == null || protectedRegionB == null) return false;
            var regionOverlaps = ReturnOverlapRegions(protectedRegionA, protectedRegionB);
            return regionOverlaps.contains(protectedRegionB);
        }

        public static boolean DoAllRegionOverlap(ProtectedRegion protectedRegionA, List<ProtectedRegion> protectedRegions){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            var regionOverlaps = ReturnAllOverlapRegions(protectedRegionA, protectedRegions);
            for(var region : protectedRegions) if(!regionOverlaps.contains(region)) return false;
            return true;
        }

        public static  List<ProtectedRegion> ReturnOverlapRegions(org.bukkit.World world, String regionNameA, String regionNameB){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return new ArrayList<>();
            return ReturnOverlapRegions(ReturnProtectedRegion(world, regionNameA), ReturnProtectedRegion(world, regionNameB));
        }

        public static  List<ProtectedRegion> ReturnOverlapRegions(ProtectedRegion protectedRegionA, ProtectedRegion protectedRegionB){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return new ArrayList<>();
            return ReturnAllOverlapRegions(protectedRegionA, Collections.singletonList(protectedRegionB));
        }

        public static List<ProtectedRegion> ReturnAllOverlapRegions(ProtectedRegion protectedRegionA, List<ProtectedRegion> protectedRegions){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return new ArrayList<>();
            if(protectedRegions.isEmpty()) return new ArrayList<>();
            return protectedRegionA.getIntersectingRegions(protectedRegions);
        }

        public static boolean IsPlayerWithinRegion(Player player, org.bukkit.World world, String regionName){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            return IsPlayerWithinRegion(player, ReturnProtectedRegion(world, regionName));
        }

        public static boolean IsPlayerWithinRegion(Player player, ProtectedRegion protectedRegion){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return false;
            return IsLocationInRegion(protectedRegion, player.getLocation());
        }
    }

    public static class FLAGS{

        private static FlagRegistry ReturnFlagRegistry(){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return null;
            return WorldGuard.getInstance().getFlagRegistry();
        }

        public static void RegisterCustomFlag(String flagName, boolean state){
            if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldGuard)) return;
            var flagRegistry = ReturnFlagRegistry();
            if(flagRegistry == null) return;

            try{
                var flag = new StateFlag(flagName, state);
                flagRegistry.register(flag);
            }catch (FlagConflictException ignored){}
        }
    }
}
