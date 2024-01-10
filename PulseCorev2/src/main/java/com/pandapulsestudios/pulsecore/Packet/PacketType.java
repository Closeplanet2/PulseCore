package com.pandapulsestudios.pulsecore.NMS;

import com.pandapulsestudios.pulsecore.NMS.Enums.Sender;
import net.minecraft.network.EnumProtocol;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutGameStateChange;

import java.util.*;

public class PacketType {
    private EnumProtocol enumProtocol;
    private Sender sender;
    private int packetID;

    public PacketType(EnumProtocol enumProtocol, Sender sender, int packetID){
        this.sender = sender;
        this.packetID = packetID;
        this.enumProtocol = enumProtocol;
    }

    public Packet<?> CreatePacket(int value, Object... data){
        switch (packetID){
            case 0x20:
                return new PacketPlayOutGameStateChange(new PacketPlayOutGameStateChange.a(value), (float) Arrays.stream(data).toList().get(0));
        };
        return null;
    }

    public static class Play {
        private static final EnumProtocol ENUM_PROTOCOL = EnumProtocol.b;
        public static class Server {
            private static Sender SENDER = Sender.SERVER;
            public static final PacketType BUNDLE = new PacketType(ENUM_PROTOCOL, SENDER, 0x00);
            public static final PacketType GAME_EVENT = new PacketType(ENUM_PROTOCOL, SENDER, 0x20);
        }
    }
}
