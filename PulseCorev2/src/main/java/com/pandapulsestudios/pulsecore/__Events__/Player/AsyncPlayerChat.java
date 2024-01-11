package com.pandapulsestudios.pulsecore.__Events__.Player;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.Enums.MessageType;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CustomEvent
public class AsyncPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(AsyncPlayerChatEvent event){

        var inventoryItems = PlayerAPI.ReturnALlPlayerItems(event.getPlayer());

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.AsyncPlayerChatEvent(event, itemStack, NBTAPI.GetAll(itemStack), event.getPlayer())) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.AsyncPlayerChatEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.AsyncPlayerChatEvent(event, itemStack, inventoryItems.get(itemStack))) event.setCancelled(true);
        }

        var world = event.getPlayer().getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world))  if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.AsyncPlayerChatSend));

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getPlayer().getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.AsyncPlayerChatEvent(event, event.getPlayer().getLocation())) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse){
            if(!event.isCancelled()) event.setCancelled(!PlayerAPI.CanDoAction(PlayerAction.AsyncPlayerChatSend, event.getPlayer()));


            if(!event.isCancelled()){
                event.setCancelled(true);
                for(var player : event.getRecipients()) ChatAPI.SendChat(event.getMessage(), MessageType.PlayerMessageFromPlayer, true, event.getPlayer(), player);
            }
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.AsyncPlayerChatEvent(event)) event.setCancelled(true);
    }
}
