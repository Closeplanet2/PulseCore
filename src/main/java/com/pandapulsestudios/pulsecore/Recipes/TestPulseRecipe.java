package com.pandapulsestudios.pulsecore.Recipes;

import com.pandapulsestudios.pulsecore.Items.ItemStackAPI;
import com.pandapulsestudios.pulsecore.Items.PulseItemStack;
import com.pandapulsestudios.pulsecore.Items.PulseItemStackAPI;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@PulseAutoRegister
public class TestPulseRecipe implements PulseRecipe, PulseItemStack {

    @Override
    public String itemName() {
        return "Test Item";
    }

    @Override
    public Material itemType() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public int itemAmount() {
        return 0;
    }

    @Override
    public RecipeType recipeType() {
        return RecipeType.ShapedRecipe;
    }

    @Override
    public ItemStack recipeResult() {
        return PulseItemStackAPI.ReturnItemStack(this);
    }
}
