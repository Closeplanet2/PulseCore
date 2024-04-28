package com.pandapulsestudios.pulsecore.NMS.API;

import com.pandapulsestudios.pulsecore.NMS.Builder.SynchedEntityDataBuilder;
import net.minecraft.world.entity.Entity;
import org.bukkit.entity.Player;

public class GlowAPI {
    public static void GlowEntity(Entity entity, Player player){
        var dataBuilder = SynchedEntityDataBuilder.CreateBuilder().isGlowing(true);
        var datWatcher = NMS_API.EntityMetaWatcher(entity, dataBuilder);
        PacketAPI.Play.Server.SetEntityMetaDataPacket(entity, datWatcher, player);
    }
}
