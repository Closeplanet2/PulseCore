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
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@CustomEvent
public class EntityDamageByEntity implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityDamageByEntityEvent event){
        Handler(event.getEntity(), event, false);
        Handler(event.getDamager(), event, true);
    }

    private void Handler(Entity entity, EntityDamageByEntityEvent event, boolean isAttacker){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        var isEntityPlayer = livingEntity instanceof Player;

        for(var pulseCoreEvent : PulseCore.PulseCoreEvents){
            if(pulseCoreEvent.CanDoEvent(isEntityPlayer ? (Player) event.getEntity() : null, event.getEntity().getLocation())){
                var state = pulseCoreEvent.pulseCoreEvents.EntityDamageByEntityEvent(event, isAttacker);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            var state = pulseLocation.EntityDamageByEntityEvent(event, livingEntity.getLocation(), isAttacker);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        if(PulseCore.handlePlayerActionEventsInHouse && isEntityPlayer){
            var state = PlayerAPI.CanDoAction(PlayerAction.EntityDamageByEntity, (Player) livingEntity);
            if(!event.isCancelled()) event.setCancelled(!state);
        }

        var playerWorld = livingEntity.getWorld();
        if(PulseCore.playerActionLock.containsKey(playerWorld) && isEntityPlayer){
            var state = PulseCore.playerActionLock.get(playerWorld).contains(PlayerAction.EntityDamageByEntity);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        for(var pulseLocation : LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            var state = pulseLocation.EntityDamageByEntityEvent(event, livingEntity.getLocation(), isAttacker);
            if(!event.isCancelled()) event.setCancelled(state);
        }

        var playerInventoryItems = PlayerAPI.ReturnALlPlayerItems(livingEntity);
        for(var itemStack : playerInventoryItems.keySet()){
            if(itemStack == null || itemStack.getItemMeta() == null) continue;

            for(var nbtListener : PulseCore.nbtListeners){
                var state = nbtListener.EntityDamageByEntityEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity, isAttacker);
                if(!event.isCancelled()) event.setCancelled(state);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItem(itemStack)){
                var state = pulseEnchantment.EntityDamageByEntityEvent(event, itemStack, playerInventoryItems.get(itemStack), isAttacker);
                if(!event.isCancelled()) event.setCancelled(state);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null){
                var state = pulseItemStack.EntityDamageByEntityEvent(event, itemStack, playerInventoryItems.get(itemStack), isAttacker);
                if(!event.isCancelled()) event.setCancelled(state);
            }
        }
    }
}
