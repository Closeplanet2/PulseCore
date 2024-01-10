package com.pandapulsestudios.pulsecore.__Events__.Entity;

import com.pandapulsestudios.pulsecore.Enchantment.EnchantmentAPI;
import com.pandapulsestudios.pulsecore.Events.CustomEvent;
import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Location.LocationAPI;
import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import com.pandapulsestudios.pulsecore.Player.PlayerAPI;
import com.pandapulsestudios.pulsecore.Player.Enums.PlayerAction;
import com.pandapulsestudios.pulsecore.PulseCoreMain;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
        var inventoryItems = isEntityPlayer ? PlayerAPI.ReturnALlPlayerItems((Player) livingEntity) : PlayerAPI.ReturnALlPlayerItems(livingEntity);

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.EntityDamageByEntityEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity, isAttacker)) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.EntityDamageByEntityEvent(event, itemStack, inventoryItems.get(itemStack), isAttacker)) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.EntityDamageByEntityEvent(event, itemStack, inventoryItems.get(itemStack), isAttacker)) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse && isEntityPlayer){
            var playerAction = isAttacker ? PlayerAction.EntityDamageByEntityAttacker : PlayerAction.EntityDamageByEntity;
            if(!event.isCancelled() && !PlayerAPI.CanDoAction(playerAction, (Player) livingEntity)) event.setCancelled(true);
        }

        var world = livingEntity.getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world) && isEntityPlayer) {
            var playerAction = isAttacker ? PlayerAction.EntityDamageByEntityAttacker : PlayerAction.EntityDamageByEntity;
            if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(playerAction));
        }

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.EntityDamageByEntityEvent(event, livingEntity.getLocation(), isAttacker)) event.setCancelled(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.EntityDamageByEntityEvent(event, isAttacker)) event.setCancelled(true);
    }
}
