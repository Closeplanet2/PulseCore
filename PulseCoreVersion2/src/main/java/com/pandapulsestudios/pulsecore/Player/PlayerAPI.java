package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.NMS.Enum.GameProfileKeys;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PlayerAPI {

    public static LinkedHashMap<PlayerAction, Boolean> ReturnPlayerActionData(Player player){
        return PulseCore.playerActionLocks.getOrDefault(player.getUniqueId(), new LinkedHashMap<>());
    }

    public static boolean CanPlayerAction(PlayerAction playerAction, Player player){
        var playerActionData = ReturnPlayerActionData(player);
        return playerActionData.getOrDefault(playerAction, true);
    }

    public static void TogglePlayerAction(PlayerAction playerAction, boolean actionState, Player player){
        var playerActionData = ReturnPlayerActionData(player);
        playerActionData.put(playerAction, actionState);
        PulseCore.playerActionLocks.put(player.getUniqueId(), playerActionData);
    }

    public static void HandlePlayerActions(HandlePlayerAction handlePlayerAction){
        if(PulseCore.handlePlayerAction == HandlePlayerAction.InOwnPlugin) return;
        PulseCore.handlePlayerAction = handlePlayerAction;
    }

    public static HashMap<ItemStack, ItemLocation> ReturnALlPlayerItems(Player player){
        var foundInformation = new HashMap<ItemStack, ItemLocation>();
        for(var itemStack : player.getInventory().getContents()){
            if(itemStack == null) continue;
            else if(itemStack.isSimilar(player.getInventory().getItemInMainHand())) foundInformation.put(itemStack, ItemLocation.EntityMainHand);
            else if(itemStack.isSimilar(player.getInventory().getItemInOffHand())) foundInformation.put(itemStack, ItemLocation.EntityOffHand);
            else if(ArmorContentsContains(player.getInventory().getArmorContents(), itemStack)) foundInformation.put(itemStack, ItemLocation.EntityArmor);
            else foundInformation.put(itemStack, ItemLocation.EntityInventory);
        }
        return foundInformation;
    }

    public static String[] GetPlayerTexture(Player player){
        var currentProfile = ((CraftPlayer) player).getHandle().getGameProfile();
        var currentProfileProp = currentProfile.getProperties();
        var textureProp = currentProfileProp.get(GameProfileKeys.TEXTURES.key).iterator().next();
        return new String[]{textureProp.value(), textureProp.signature()};
    }

    private static boolean ArmorContentsContains(ItemStack[] itemStacks, ItemStack itemStack){
        for(var i : itemStacks) if(itemStack.isSimilar(i)) return true;
        return false;
    }
}
