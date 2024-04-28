package com.pandapulsestudios.pulsecore.RecipeAPI.API;

import com.pandapulsestudios.pulsecore.PulseCore;
import com.pandapulsestudios.pulsecore.RecipeAPI.Interface.PulseRecipe;

import java.util.LinkedHashMap;

public class RecipeAPI {
    public static LinkedHashMap<String, PulseRecipe> ReturnAllStoredRecipes(){
        return PulseCore.PulseRecipes;
    }

    public static PulseRecipe ReturnPulseRecipeByName(String recipeName){
        return PulseCore.PulseRecipes.getOrDefault(recipeName, null);
    }
}
