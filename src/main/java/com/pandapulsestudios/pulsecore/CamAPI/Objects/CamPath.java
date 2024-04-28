package com.pandapulsestudios.pulsecore.CamAPI.Objects;

import com.pandapulsestudios.pulsecore.CamAPI.BukkitRunnable.CamPathRunnable;
import com.pandapulsestudios.pulsecore.CamAPI.Enum.LookAtType;
import com.pandapulsestudios.pulsecore.CamAPI.Events.CamPathStartEvent;
import com.pandapulsestudios.pulsecore.CamAPI.Events.CamPathStopEvent;
import com.pandapulsestudios.pulsecore.CamAPI.Events.PlayerAddedCamPathEvent;
import com.pandapulsestudios.pulsecore.CamAPI.Events.PlayerStoppedCamPathEvent;
import com.pandapulsestudios.pulsecore.PulseCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class CamPath{
    @Getter
    private List<CamSet> camSets = new ArrayList<>();

    private HashMap<UUID, GameMode> playersGamemodesBefore = new HashMap<>();
    private HashMap<UUID, Location> playersLocationsBefore = new HashMap<>();
    private HashMap<UUID, Boolean> playersFlyingBefore = new HashMap<>();
    @Getter
    private List<Player> players = new ArrayList<>();

    private BukkitRunnable bukkitRunnable;


    public void StartCamPath(List<Player> players){
        if(bukkitRunnable != null) return;
        if(new CamPathStartEvent(this).isCancelled()) return;
        for(var player : players){
            this.players.add(player);
            playersGamemodesBefore.put(player.getUniqueId(), player.getGameMode());
            playersLocationsBefore.put(player.getUniqueId(), player.getLocation());
            playersFlyingBefore.put(player.getUniqueId(), player.isFlying());
            player.setGameMode(GameMode.SPECTATOR);
        }
        Bukkit.broadcastMessage("starting cam event");
        bukkitRunnable = new CamPathRunnable(this);
        bukkitRunnable.runTaskTimer(PulseCore.PulseCore, 0L, 1L);
    }

    public void EndCamPath(){
        new CamPathStopEvent(this);
        for(var player : new ArrayList<>(this.players)) RemovePlayerFromPath(player);
        bukkitRunnable.cancel();
        bukkitRunnable = null;
    }

    public void RemovePlayerFromPath(Player player){
        if(!players.contains(player)) return;
        new PlayerStoppedCamPathEvent(player, this);
        player.setGameMode(playersGamemodesBefore.getOrDefault(player.getUniqueId(), player.getGameMode()));
        player.teleport(playersLocationsBefore.getOrDefault(player.getUniqueId(), player.getLocation()));
        player.setFlying(playersFlyingBefore.getOrDefault(player.getUniqueId(), player.isFlying()));
        this.players.remove(player);
    }

    public static CamPathBuilder CamPathBuilder(){return new CamPathBuilder();}
    public static class CamPathBuilder{
        private List<CamSet> camSets = new ArrayList<>();
        private List<Player> players = new ArrayList<>();

        public CamPathBuilder addTravelPath(Location start, Location end, LookAtType lookAtType, Object data, int durationInTicks){
            var pathLocations = new ArrayList<Location>();
            pathLocations.add(start);

            var stepX = (end.getX() - start.getX()) / durationInTicks;
            var stepY = (end.getY() - start.getY()) / durationInTicks;
            var stepZ = (end.getZ() - start.getZ()) / durationInTicks;
            var stepYaw = (end.getYaw() - start.getYaw()) / durationInTicks;
            var stepPitch = (end.getPitch() - start.getPitch()) / durationInTicks;
            var step = new Location(start.getWorld(), stepX, stepY, stepZ, stepYaw, stepPitch);

            for (int i = 1; i <= durationInTicks; i++) {
                var prevLocation = pathLocations.get(i-1).clone();
                var nextlocation = prevLocation.add(step);
                var yaw = nextlocation.getYaw() + stepYaw;
                var pitch = nextlocation.getPitch() + stepPitch;
                nextlocation.setYaw(yaw);
                nextlocation.setPitch(pitch);
                pathLocations.add(nextlocation);
            }

            camSets.add(new CamSet(pathLocations, lookAtType, data));
            return this;
        }

        public CamPathBuilder players(Player... players){
            for(var player : players) if(!new PlayerAddedCamPathEvent(player).isCancelled()) this.players.add(player);
            return this;
        }

        public CamPath Play(String camPathName){
            var camPath = new CamPath();
            camPath.camSets = camSets;
            camPath.players = players;
            PulseCore.CamPaths.put(camPathName, camPath);
            camPath.StartCamPath(players);
            return camPath;
        }

        public CamPath Store(String camPathName){
            var camPath = new CamPath();
            camPath.camSets = camSets;
            PulseCore.CamPaths.put(camPathName, camPath);
            return camPath;
        }
    }
}
