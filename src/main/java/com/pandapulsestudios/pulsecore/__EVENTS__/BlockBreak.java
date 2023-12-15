package com.pandapulsestudios.pulsecore.__Events__;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@CustomEvent
public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
       if(!(event.getBlock().getState() instanceof Container)){
           //Check to see if pulse enchantment will cancel the event and allow user to call custom code on block break
           for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(event.getPlayer().getInventory().getItemInMainHand())){
               var state = pulseEnchantment.BlockBreakEvent(event, event.getPlayer().getInventory().getItemInMainHand());
               if(!event.isCancelled()) event.setCancelled(state);
           }

           //Check to see if the item the player is holding to call custom event
           var pulseItemStack = ItemStackAPI.ReturnPulseItem(event.getPlayer().getInventory().getItemInMainHand());
           if(pulseItemStack != null){
               var state = pulseItemStack.BlockBreakEvent(event);
               if(!event.isCancelled()) event.setCancelled(state);
           }

           //Check to see if the location of the player is being held to call custom event
           for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(event.getBlock().getLocation(), true)){
               var state = pulseLocation.BlockBreakEvent(event);
               if(!event.isCancelled()) event.setCancelled(state);
           }
       }
    }

}
