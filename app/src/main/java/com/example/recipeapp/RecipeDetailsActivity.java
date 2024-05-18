package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapp.Adapters.IngredientsAdapter;
import com.example.recipeapp.Adapters.SimilarRecipeAdapter;
import com.example.recipeapp.models.InstructionsResponse;
import com.example.recipeapp.models.RecipeDetailsResponse;
import com.example.recipeapp.models.SimilarRecipesResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_mealName,textView_meal_source,textView_meal_summaries;
    ImageView imageView_meal;
    RecyclerView recycler_meal_ingredients,recycler_meal_similar,recycler_meal_instructions;
    RequestManager manager;
    ProgressDialog dialog;
    SimilarRecipeAdapter similarRecipeAdapter;
    IngredientsAdapter ingredientsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        Log.i("chedi","id"+id);
        init();
        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getSimilarRecipes(similarRecipesListener,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading details ...");
        Log.i("chedi","id"+recipeDetailsListener);
        dialog.show();
        Log.i("chedi","id"+recipeDetailsListener);

    }
    private void init(){
        textView_mealName=findViewById(R.id.textView_mealName);
        textView_meal_source=findViewById(R.id.textView_meal_source);
        textView_meal_summaries=findViewById(R.id.textView_meal_summaries);
        imageView_meal=findViewById(R.id.imageView_meal);
        recycler_meal_ingredients=findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar=findViewById(R.id.recycler_meal_similar);
        recycler_meal_instructions=findViewById(R.id.recycler_meal_instructions);
    }
    private final RecipeDetailsListener recipeDetailsListener=new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse recipeDetailsResponse, String message) {
            dialog.dismiss();
            textView_mealName.setText(recipeDetailsResponse.title);
            textView_meal_source.setText(recipeDetailsResponse.sourceName);
            textView_meal_summaries.setText(recipeDetailsResponse.summary);
            Picasso.get().load(recipeDetailsResponse.image).into(imageView_meal);
            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter=new IngredientsAdapter(RecipeDetailsActivity.this,recipeDetailsResponse.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }
        @Override
        public void didError(String error) {
            Toast.makeText(RecipeDetailsActivity.this,"error",Toast.LENGTH_SHORT).show();

        }
    };
    private final SimilarRecipesListener similarRecipesListener=new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipesResponse> response, String message) {
            dialog.dismiss();
            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapter=new SimilarRecipeAdapter(RecipeDetailsActivity.this,response,recipeClickListener);
            recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }
        @Override
        public void didError(String error) {
            Toast.makeText(RecipeDetailsActivity.this,error,Toast.LENGTH_SHORT).show();

        }
    };

    private final RecipeClickListener recipeClickListener=new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            Intent intent =new Intent(RecipeDetailsActivity.this,RecipeDetailsActivity.class).putExtra("id",id);
            startActivity(intent);
        }
    };
}