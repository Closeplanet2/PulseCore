package com.pandapulsestudios.pulsecore.Events;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.Data.*;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Dir.DirCreatedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Dir.DirDeletedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.File.FileCreatedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.File.FileDeletedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramAddLineEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramCreatedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramDeleteLineEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Holograms.HologramEditLineEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.PlayerAction.TogglePlayerActionEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard.ScoreboardCreationEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard.ScoreboardPlayerAddEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Scoreboard.ScoreboardPlayerRemovedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Sound.PlayerPlaySoundEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.Sound.WorldPlaySoundEvent;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@PulseAutoRegister
public class TestEvents implements Listener {
    @EventHandler
    public void Test(PersistentDataAddedEvent event){ }
    @EventHandler
    public void Test(PersistentDataRemovedEvent event){ }
    @EventHandler
    public void Test(ServerDataAddedEvent event){ }
    @EventHandler
    public void Test(ServerDataRemovedEvent event){ }
    @EventHandler
    public void Test(TempBlockDataAddedEvent event){ }
    @EventHandler
    public void Test(TempBlockDataRemovedEvent event){ }
    @EventHandler
    public void Test(UUIDDataAddedEvent event){ }
    @EventHandler
    public void Test(UUIDDataRemovedEvent event){ }
    @EventHandler
    public void Test(DirCreatedEvent event){ }
    @EventHandler
    public void Test(DirDeletedEvent event){ }
    @EventHandler
    public void Test(FileCreatedEvent event){ }
    @EventHandler
    public void Test(FileDeletedEvent event){ }
    @EventHandler
    public void Test(HologramAddLineEvent event){ }
    @EventHandler
    public void Test(HologramCreatedEvent event){ }
    @EventHandler
    public void Test(HologramDeleteLineEvent event){ }
    @EventHandler
    public void Test(HologramEditLineEvent event){ }
    @EventHandler
    public void Test(TogglePlayerActionEvent event){ }
    @EventHandler
    public void Test(ScoreboardCreationEvent event){ }
    @EventHandler
    public void Test(ScoreboardPlayerAddEvent event){ }
    @EventHandler
    public void Test(ScoreboardPlayerRemovedEvent event){ }
    @EventHandler
    public void Test(PlayerPlaySoundEvent event){ }
    @EventHandler
    public void Test(WorldPlaySoundEvent event){ }
}
