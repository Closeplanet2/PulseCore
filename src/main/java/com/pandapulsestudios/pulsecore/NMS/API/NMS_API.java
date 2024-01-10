package com.pandapulsestudios.pulsecore.NMS.API;

import com.mojang.authlib.GameProfile;
import com.pandapulsestudios.pulsecore.NMS.Builder.SynchedEntityDataBuilder;
import com.pandapulsestudios.pulsecore.Reflection.ReflectionAPI;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NMS_API {
    public static ItemEntity CreateEntityItem(Location location, ItemStack itemStack){
        var craftWorld = ((CraftWorld) location.getWorld()).getHandle();
        var craftItemStack = CraftItemStack.asNMSCopy(itemStack);
        return new ItemEntity(craftWorld, location.getX(), location.getY(), location.getZ(), craftItemStack);
    }

    public static SynchedEntityData EntityMetaWatcher(Entity entity, SynchedEntityDataBuilder.builder builder){
        var dataWatcher = entity.getEntityData();
        dataWatcher.set(new EntityDataAccessor<>(0, EntityDataSerializers.BYTE), builder.Entity0Index());
        return dataWatcher;
    }

    public static ServerLevel ReturnServerLevel(Location location){
        var minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        return ((CraftWorld) location.getWorld()).getHandle();
    }

    public static ServerPlayer CreateServerPlayer(GameProfile gameProfile, Location location, Player player){
        var minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        var serverLevel = ((CraftWorld) location.getWorld()).getHandle();
        var serverPlayer = new ServerPlayer(minecraftServer, serverLevel, gameProfile, ClientInformation.createDefault());
        ReflectionAPI.Set(serverPlayer, "c", ((CraftPlayer) player).getHandle().connection);
        serverPlayer.moveTo(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        return serverPlayer;
    }
}
