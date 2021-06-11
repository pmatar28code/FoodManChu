package com.example.foodmanchu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmanchu.databinding.ItemRecipesBinding

class RecipesAdapter(
): ListAdapter<Recipes,RecipesAdapter.RecipesViewHolder>(diff) {
    companion object{
        val diff = object : DiffUtil.ItemCallback<Recipes>(){
            override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipesBinding.inflate(inflater,parent,false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.onBind(getItem(position))

    }

    class RecipesViewHolder(
            private val binding : ItemRecipesBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(recipe: Recipes){
            binding.apply {
                recipeNameText.text = recipe.recipeName

            }
        }
    }
}