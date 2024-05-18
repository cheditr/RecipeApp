package com.example.recipeapp;

import com.example.recipeapp.models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
     void didFetch(RecipeDetailsResponse recipeDetailsResponse,String message);
     void didError(String error);
}
