package com.pandapulsestudios.pulsecore.NMS.API;

import com.pandapulsestudios.pulsecore.NMS.Builder.SynchedEntityDataBuilder;
import com.pandapulsestudios.pulsecore.PulseCore;
import net.minecraft.ChatFormatting;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.scores.PlayerTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class GlowAPI {
    public static void GlowEntity(Entity entity, Player player){
        var dataBuilder = SynchedEntityDataBuilder.CreateBuilder().isGlowing(true);
        var datWatcher = NMS_API.EntityMetaWatcher(entity, dataBuilder);
        PacketAPI.Play.Server.SetEntityMetaDataPacket(entity, datWatcher, player);
    }
}
