package com.pandapulsestudios.pulsecore.LocationAPI.Object;

import com.pandapulsestudios.pulsecore.LocationAPI.Enum.LocationLimiterStart;
import com.pandapulsestudios.pulsecore.LocationAPI.Enum.LocationLimiterType;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocationLimiter {
    private final UUID id = UUID.randomUUID();
    private List<Player> players = new ArrayList<Player>();
    private LocationLimiterType locationLimiterType = LocationLimiterType.SNAP_TO_ORIGIN;
    private LocationLimiterStart locationLimiterStart = LocationLimiterStart.ORIGIN_POINT;
    private String edgeMessage = "You have reached the edge of the location!";
    private Location originPoint;
    private int distanceExtents = 10;
    private boolean stopped = false;

    private void StartLocationLimiter(){
        if(locationLimiterStart == LocationLimiterStart.ORIGIN_POINT) for(var player : players) player.teleport(originPoint);
    }

    public void TickLocationLimiter(){
        if(stopped) return;
        for(var player : players){
            if(!player.getWorld().getName().equals(originPoint.getWorld().getName())){
                player.teleport(originPoint);
                return;
            }

            var distance = player.getLocation().distance(originPoint);
            if(locationLimiterType == LocationLimiterType.SNAP_TO_ORIGIN && distance >= distanceExtents) player.teleport(originPoint);
        }
    }

    public void ToggleLocationLimiter(boolean state){
        stopped = !state;
    }

    public void StopLocationLimiter(){
        stopped = true;
        PulseCore.LocationLimiters.remove(id);
    }


    public static class LocationLimiterBuilder{
        private List<Player> players = new ArrayList<Player>();
        private LocationLimiterType locationLimiterType = LocationLimiterType.SNAP_TO_ORIGIN;
        private LocationLimiterStart locationLimiterStart = LocationLimiterStart.ORIGIN_POINT;
        private String edgeMessage = "You have reached the edge of the location!";
        private int distanceExtents = 10;

        public LocationLimiterBuilder addPlayer(Player player){
            players.add(player);
            return this;
        }

        public LocationLimiterBuilder locationLimiterType(LocationLimiterType locationLimiterType){
            this.locationLimiterType = locationLimiterType;
            return this;
        }

        public LocationLimiterBuilder locationLimiterStart(LocationLimiterStart locationLimiterStart){
            this.locationLimiterStart = locationLimiterStart;
            return this;
        }

        public LocationLimiterBuilder edgeMessage(String edgeMessage){
            this.edgeMessage = edgeMessage;
            return this;
        }

        public LocationLimiterBuilder distanceExtents(int distanceExtents){
            this.distanceExtents = distanceExtents;
            return this;
        }

        public void Build(Location originPoint){
            var locationLimiter = new LocationLimiter();
            locationLimiter.players = players;
            locationLimiter.locationLimiterType = locationLimiterType;
            locationLimiter.locationLimiterStart = locationLimiterStart;
            locationLimiter.edgeMessage = edgeMessage;
            locationLimiter.originPoint = originPoint;
            locationLimiter.distanceExtents = distanceExtents;
            locationLimiter.StartLocationLimiter();
            PulseCore.LocationLimiters.put(locationLimiter.id, locationLimiter);
        }
    }
}
