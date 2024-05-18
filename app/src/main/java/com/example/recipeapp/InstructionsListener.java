package com.example.recipeapp;

import com.example.recipeapp.models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String error);
}
