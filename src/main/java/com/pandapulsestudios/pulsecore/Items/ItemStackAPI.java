package com.pandapulsestudios.pulsecore.Items;

import com.pandapulsestudios.pulsecore.NBT.NBTAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemStackAPI {
    public static int CountItem(Player player, ItemStack itemStack){
        if(player == null || itemStack == null) return 0;
        return CountItem(player.getInventory(), itemStack);
    }

    public static int CountItem(Inventory inventory, ItemStack itemStack){
        if(inventory == null || itemStack == null) return 0;
        var count = 0;
        for(var item : inventory.getContents()){
            if(IsItemTheSame(item, itemStack)) count += item.getAmount();
        }
        return count;
    }

    public static int CountItem(Player player, Material itemStack){
        if(player == null || itemStack == null) return 0;
        return CountItem(player.getInventory(), itemStack);
    }

    public static int CountItem(Inventory inventory, Material itemStack){
        if(inventory == null || itemStack == null) return 0;
        var count = 0;
        for(var item : inventory.getContents()){
            if(item.getType() == itemStack) count += item.getAmount();
        }
        return count;
    }

    public static boolean IsItemTheSame(ItemStack a, ItemStack b){
        if(a == null || b == null) return false;
        return a.isSimilar(b);
    }

    public static Builder builder(){return new Builder();}
    public static final class Builder{
        private String itemName = "test";
        private Material itemMaterial = Material.DIAMOND_PICKAXE;
        private int itemAmount = 1;
        private List<String> itemLore = new ArrayList<>();
        private List<ItemFlag> itemFlags = new ArrayList<>();
        private HashMap<String, String> nbtTags = new HashMap<>();
        private HashMap<Enchantment, Integer> enchantments = new HashMap<>();

        public Builder(){}

        public Builder itemName(String itemName){
            this.itemName = itemName;
            return this;
        }

        public Builder itemMaterial(Material itemMaterial){
            this.itemMaterial = itemMaterial;
            return this;
        }

        public Builder itemAmount(int itemAmount){
            this.itemAmount = itemAmount;
            return this;
        }

        public Builder itemLore(String... itemLore){
            this.itemLore.addAll(Arrays.asList(itemLore));
            return this;
        }

        public Builder itemFlags(ItemFlag... itemFlags){
            this.itemFlags.addAll(Arrays.asList(itemFlags));
            return this;
        }

        public Builder addNBTTag(String key, String value){
            nbtTags.put(key, value);
            return this;
        }

        public Builder addEnchantment(Enchantment enchantment, Integer level){
            enchantments.put(enchantment, level);
            return this;
        }

        public ItemStack build(){
            ItemStack itemStack = new ItemStack(itemMaterial, itemAmount);
            var itemMeta = itemStack.getItemMeta();
            if(itemMeta != null) itemMeta.setDisplayName(itemName);
            if(itemMeta != null) itemMeta.setLore(itemLore);
            if(itemMeta != null) for(var enchantment : enchantments.keySet()) itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
            if(itemMeta != null) for(var itemFlag : itemFlags) itemMeta.addItemFlags(itemFlag);
            if(itemMeta != null) itemStack.setItemMeta(itemMeta);
            for(String key : nbtTags.keySet()) NBTAPI.AddNBT(itemStack, key, nbtTags.get(key));
            return itemStack;
        }
    }
}
