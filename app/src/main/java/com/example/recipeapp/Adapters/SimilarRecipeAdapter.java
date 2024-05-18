package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;
import com.example.recipeapp.RecipeClickListener;
import com.example.recipeapp.SimilarRecipesListener;
import com.example.recipeapp.models.SimilarRecipesResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder> {
    Context context;
    List<SimilarRecipesResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipesResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipies,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.textView_similar_title.setText(list.get(position).title);
        holder.textView_similar_title.setSelected(true);
        holder.textView_servings.setText(list.get(position).servings+" Persons");
        Picasso.get().load("https://img.spoonacular.com/recipes/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageView_similar_recipe);
        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView similar_recipe_holder;
    TextView textView_similar_title,textView_servings;
    ImageView imageView_similar_recipe;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_recipe_holder=itemView.findViewById(R.id.similar_recipe_holder);
        textView_similar_title=itemView.findViewById(R.id.textView_similar_title);
        textView_servings=itemView.findViewById(R.id.textView_servings);
        imageView_similar_recipe=itemView.findViewById(R.id.imageView_similar_recipe);

    }
}
