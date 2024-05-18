package com.example.recipeapp;

import com.example.recipeapp.models.SimilarRecipesResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipesResponse> response,String message);
    void didError(String error);

}
