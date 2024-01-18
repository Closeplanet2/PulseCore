package com.pandapulsestudios.pulsecore._Common.Events.Entity;

import com.pandapulsestudios.pulsecore.Block.API.PersistentDataAPI;
import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.PulseCore;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;

@CustomEvent
public class EntityCombustByBlock implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void OnBlockBreak(EntityCombustByBlockEvent event){
        if(!(event.getEntity() instanceof LivingEntity livingEntity)) return;
        var isEntityPlayer = livingEntity instanceof Player;

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            var state = pulseCoreEvent.EntityCombustByBlockEvent(event);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            var state = pulseLocation.EntityCombustByBlockEvent(event, livingEntity.getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerActionEventsInHouse && isEntityPlayer){
            var state = PlayerAPI.CanDoAction(PlayerAction.EntityCombustByBlock, (Player) livingEntity);
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = livingEntity.getWorld();
        if(PulseCore.playerActionLock.containsKey(playerWorld) && isEntityPlayer){
            var state = PulseCore.playerActionLock.get(playerWorld).contains(PlayerAction.EntityCombustByBlock);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            var state = pulseLocation.EntityCombustByBlockEvent(event, livingEntity.getLocation());
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(livingEntity);
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                var state = nbtListener.EntityCombustByBlockEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity);
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.EntityCombustByBlockEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.EntityCombustByBlockEvent(event, itemStack, playerInventoryItems.get(itemStack));
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var persistentDataCallback : PulseCore.persistentDataCallbacks){
            if(!PersistentDataAPI.CanBeCalled(persistentDataCallback, event.getCombuster())) continue;
            var feedbackState = persistentDataCallback.EntityCombustByBlockEvent(event, event.getCombuster(), PersistentDataAPI.GetAll(event.getCombuster()));
            if(!event.isCancelled()) event.setCancelled(feedbackState);
        }
    }

}
