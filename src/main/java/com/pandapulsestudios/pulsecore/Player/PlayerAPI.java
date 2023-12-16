package com.pandapulsestudios.pulsecore.Player;

import com.pandapulsestudios.pulsecore.Items.ItemLocation;
import com.pandapulsestudios.pulsecore.Player.Enums.HandlePlayerAction;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerAPI {
    public static boolean CanDoAction(PlayerAction playerAction, Player... players){
        for(var player : players) if(!PulseCoreMain.playerToggleActions.get(playerAction).getOrDefault(player.getUniqueId(), true)) return false;
        return true;
    }

    public static void TogglePlayerAction(PlayerAction playerAction, boolean state, Player... players){
        for(var player : players) PulseCoreMain.playerToggleActions.get(playerAction).put(player.getUniqueId(), state);
    }

    public static void HandlePlayerActions(HandlePlayerAction handlePlayerAction){
        if(!PulseCoreMain.handlePlayerActionEventsInHouse) return;
        PulseCoreMain.handlePlayerActionEventsInHouse = handlePlayerAction == HandlePlayerAction.InPulseCore;
    }

    public static HashMap<ItemStack, ItemLocation> ReturnALlPlayerItems(Player player){
        var foundInformation = new HashMap<ItemStack, ItemLocation>();
        for(var itemStack : player.getInventory().getContents()){
            if(itemStack == null) continue;
            else if(itemStack.isSimilar(player.getInventory().getItemInMainHand())) foundInformation.put(itemStack, ItemLocation.PlayerMainHand);
            else if(itemStack.isSimilar(player.getInventory().getItemInOffHand())) foundInformation.put(itemStack, ItemLocation.PlayerOffHand);
            else if(ArmorContentsContains(player.getInventory().getArmorContents(), itemStack)) foundInformation.put(itemStack, ItemLocation.PlayerArmor);
            else foundInformation.put(itemStack, ItemLocation.PlayerInventory);
        }
        return foundInformation;
    }

    private static boolean ArmorContentsContains(ItemStack[] itemStacks, ItemStack itemStack){
        for(var i : itemStacks) if(itemStack.isSimilar(i)) return true;
        return false;
    }
}
