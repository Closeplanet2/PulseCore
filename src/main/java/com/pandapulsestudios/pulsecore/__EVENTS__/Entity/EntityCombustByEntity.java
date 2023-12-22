package com.pandapulsestudios.pulsecore.__Events__.Entity;

import com.pandapulsestudios.pulsecore.Block.PersistentDataAPI;
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
import org.bukkit.event.entity.EntityCombustByEntityEvent;

@CustomEvent
public class EntityCombustByEntity implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityCombustByEntityEvent event){
        Handler(event.getEntity(), event, false);
        Handler(event.getCombuster(), event, true);
    }

    private void Handler(Entity entity, EntityCombustByEntityEvent event, boolean isAttacker){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        var isEntityPlayer = livingEntity instanceof Player;
        var inventoryItems = isEntityPlayer ? PlayerAPI.ReturnALlPlayerItems((Player) livingEntity) : PlayerAPI.ReturnALlPlayerItems(livingEntity);

        for(var itemStack : inventoryItems.keySet()){
            if(itemStack.getItemMeta() == null) continue;
            for(var nbtListener : PulseCoreMain.nbtListeners){
                if(!event.isCancelled() && nbtListener.EntityCombustByEntityEvent(event, itemStack, NBTAPI.GetAll(itemStack), livingEntity, isAttacker)) event.setCancelled(true);
            }

            for(var pulseEnchantment : EnchantmentAPI.ReturnCustomEnchantmentOnItems(itemStack)){
                if(!event.isCancelled() && pulseEnchantment.EntityCombustByEntityEvent(event, itemStack, inventoryItems.get(itemStack), isAttacker)) event.setCancelled(true);
            }

            var pulseItemStack = ItemStackAPI.ReturnPulseItem(itemStack);
            if(pulseItemStack != null) if(!event.isCancelled() && pulseItemStack.EntityCombustByEntityEvent(event, itemStack, inventoryItems.get(itemStack), isAttacker)) event.setCancelled(true);
        }

        if(PulseCoreMain.handlePlayerActionEventsInHouse && isEntityPlayer){
            if(!event.isCancelled() && !PlayerAPI.CanDoAction(PlayerAction.EntityCombustByEntity, (Player) livingEntity)) event.setCancelled(true);
        }

        var world = livingEntity.getLocation().getWorld();
        if(PulseCoreMain.playerActionLock.containsKey(world)  && isEntityPlayer)  if(!event.isCancelled()) event.setCancelled(PulseCoreMain.playerActionLock.get(world).contains(PlayerAction.EntityCombustByEntity));

        for(var pulseLocation :  LocationAPI.ReturnAllPulseLocations(livingEntity.getLocation(), true)){
            if(!event.isCancelled() && pulseLocation.EntityCombustByEntityEvent(event, livingEntity.getLocation(), isAttacker)) event.setCancelled(true);
        }

        for(var coreEvent : PulseCoreMain.pulseCoreEvents) if(!event.isCancelled() && coreEvent.EntityCombustByEntityEvent(event)) event.setCancelled(true);
    }
}
