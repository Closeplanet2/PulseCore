package com.pandapulsestudios.pulsecore.NMS;

import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketAPI {
    public static PlayerConnection ReturnPlayerConnection(Player player){
        var craftPlayer = (CraftPlayer) player;
        var entityPlayer = craftPlayer.getHandle();
        return entityPlayer.c;
    }

    public static void SendPacket(Player player, Packet<?> packet){
        var connection = ReturnPlayerConnection(player);
        connection.a(packet);
    }
}
