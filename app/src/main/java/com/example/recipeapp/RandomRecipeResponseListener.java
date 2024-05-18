package com.example.recipeapp;

import com.example.recipeapp.models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didfetch(RandomRecipeApiResponse reponse,String message);
    void didError(String error);
}
