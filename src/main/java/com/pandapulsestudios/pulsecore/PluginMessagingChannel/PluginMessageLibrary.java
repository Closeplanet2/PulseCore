package com.pandapulsestudios.pulsecore.PluginMessagingChannel;

import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessageLibrary implements PluginMessageListener {
    private static String CHANNEL_NAME = "BungeeCord";

    public PluginMessageLibrary(JavaPlugin javaPlugin){
        javaPlugin.getServer().getMessenger().registerOutgoingPluginChannel(javaPlugin, CHANNEL_NAME);
        javaPlugin.getServer().getMessenger().registerIncomingPluginChannel(javaPlugin, CHANNEL_NAME, this);
    }

    public void TerminateConnection(JavaPlugin javaPlugin){
        javaPlugin.getServer().getMessenger().unregisterOutgoingPluginChannel(javaPlugin);
        javaPlugin.getServer().getMessenger().unregisterIncomingPluginChannel(javaPlugin);
    }

    public void ConnectToSubServer(Player player, String serverName){
        var out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(PulseCoreMain.Instance, CHANNEL_NAME, out.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        Bukkit.getServer().getPluginManager().callEvent(new PluginMessageReceivedEvent(channel, player, message));
    }
}
