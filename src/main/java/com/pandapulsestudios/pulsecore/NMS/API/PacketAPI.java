package com.pandapulsestudios.pulsecore.NMS.API;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.NMS.Builder.SynchedEntityDataBuilder;
import com.pandapulsestudios.pulsecore.NMS.Enum.EntityAnimationType;
import com.pandapulsestudios.pulsecore.NMS.Enum.GameProfileKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.UUID;

public class PacketAPI {
    public static class Play {
        public static class Server{
            public static ItemEntity SpawnEntityPacket(Location location, SynchedEntityDataBuilder.builder builder, ItemStack itemStack, Player player){
                if(location == null) location = player.getLocation();
                var itemEntity = NMS_API.CreateEntityItem(location, itemStack);
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundAddEntityPacket(itemEntity));
                SetEntityMetaDataPacket(itemEntity,  NMS_API.EntityMetaWatcher(itemEntity, builder), player);
                return itemEntity;
            }

            public static ServerPlayer SpawnEntityPacket(UUID entityUUID, String entityName, String skinValue, String skinSignature, Location location, Player player){
                var gameProfile = new GameProfile(entityUUID, entityName);
                gameProfile.getProperties().put(GameProfileKeys.TEXTURES.key, new Property(GameProfileKeys.TEXTURES.key, skinValue, skinSignature));
                var serverPlayer = NMS_API.CreateServerPlayer(gameProfile, location, player);
                PlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, serverPlayer, player);
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundAddEntityPacket(serverPlayer));
                return serverPlayer;
            }

            public static ServerPlayer SpawnPlayerCLonePacket(Location location, Player player){
                var currentProfile = ((CraftPlayer) player).getHandle().getGameProfile();
                var currentProfileProp = currentProfile.getProperties();
                var textureProp = currentProfileProp.get(GameProfileKeys.TEXTURES.key).iterator().next();
                var newGameProfile = new GameProfile(UUID.randomUUID(), player.getDisplayName());
                newGameProfile.getProperties().put(GameProfileKeys.TEXTURES.key, new Property(GameProfileKeys.TEXTURES.key, textureProp.value(), textureProp.signature()));
                var serverPlayer = NMS_API.CreateServerPlayer(newGameProfile, location, player);
                PlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, serverPlayer, player);
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundAddEntityPacket(serverPlayer));
                return serverPlayer;
            }

            public static ExperienceOrb SpawnExperienceOrb(Location location, int amount, Player player){
                var experienceOrb = new ExperienceOrb(NMS_API.ReturnServerLevel(player.getLocation()), location.getX(), location.getY(), location.getZ(), amount);
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundAddExperienceOrbPacket(experienceOrb));
                return experienceOrb;
            }

            public static void EntityAnimationPacket(Entity entity, EntityAnimationType entityAnimationType, Player player){
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundAnimatePacket(entity, entityAnimationType.animationInt));
            }

            public static void PlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action action, ServerPlayer serverPlayer, Player player){
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundPlayerInfoUpdatePacket(action, serverPlayer));
            }

            public static void SetEntityMetaDataPacket(Entity entity, SynchedEntityData synchedEntityData, Player player){
                ((CraftPlayer) player).getHandle().connection.send(new ClientboundSetEntityDataPacket(entity.getId(), synchedEntityData.packDirty()));
            }
        }
    }


}
