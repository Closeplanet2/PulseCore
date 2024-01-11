package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Player.Enums.HandlePlayerAction;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerAPI {
    public static boolean CanDoAction(PlayerAction playerAction, Player... players){
        for(var player : players){
            if(player == null) continue;
            var playerHashmap = PulseCore.PlayerToggeableActions.getOrDefault(playerAction, new HashMap<>());
            var playerActionState = playerHashmap.getOrDefault(player.getUniqueId(), true);
            if(!playerActionState) return false;
        }
        return true;
    }

    public static void TogglePlayerAction(PlayerAction playerAction, boolean state, Player... players){
        for(var player : players){
            var playerHashmap = PulseCore.PlayerToggeableActions.getOrDefault(playerAction, new HashMap<>());
            playerHashmap.put(player.getUniqueId(), state);
            PulseCore.PlayerToggeableActions.put(playerAction, playerHashmap);
        }
    }

    public static void HandlePlayerActions(HandlePlayerAction handlePlayerAction){
        if(!PulseCore.handlePlayerActionEventsInHouse) return;
        PulseCore.handlePlayerActionEventsInHouse = handlePlayerAction == HandlePlayerAction.InPulseCore;
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

    public static HashMap<ItemStack, ItemLocation> ReturnALlPlayerItems(LivingEntity livingEntity){
        var foundInformation = new HashMap<ItemStack, ItemLocation>();
        for(var itemStack : livingEntity.getEquipment().getArmorContents()) foundInformation.put(itemStack, ItemLocation.EntityArmor);
        foundInformation.put(livingEntity.getEquipment().getItemInOffHand(), ItemLocation.EntityMainHand);
        foundInformation.put(livingEntity.getEquipment().getItemInOffHand(), ItemLocation.EntityOffHand);
        return foundInformation;
    }

    private static boolean ArmorContentsContains(ItemStack[] itemStacks, ItemStack itemStack){
        for(var i : itemStacks) if(itemStack.isSimilar(i)) return true;
        return false;
    }
}
