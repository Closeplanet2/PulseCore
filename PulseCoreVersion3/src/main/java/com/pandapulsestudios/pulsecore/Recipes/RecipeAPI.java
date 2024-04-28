package com.pandapulsestudios.pulsecore.Recipes;

import com.pandapulsestudios.pulsecore.PulseCore;

import java.util.LinkedHashMap;

public class RecipeAPI {
    public static LinkedHashMap<String, PulseRecipe> ReturnAllStoredRecipes(){
        return PulseCore.customRecipes;
    }

    public static PulseRecipe ReturnPulseRecipeByName(String recipeName){
        return PulseCore.customRecipes.getOrDefault(recipeName, null);
    }
}
