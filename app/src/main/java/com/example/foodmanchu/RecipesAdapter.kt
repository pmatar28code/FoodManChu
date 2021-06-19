package com.example.foodmanchu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmanchu.databinding.ItemRecipesBinding

class RecipesAdapter(
        val onCLickForDetails:(Recipes) -> Unit,
        val onClickForDuplicate:(Recipes) -> Unit,
        val onClickForDelete:(Recipes) -> Unit,
        val onClickForEdit:(Recipes) -> Unit
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
        return RecipesViewHolder(binding,onCLickForDetails,onClickForDuplicate,onClickForDelete,onClickForEdit)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener { onCLickForDetails(getItem(position)) }
    }

    class RecipesViewHolder(
            private val binding : ItemRecipesBinding,
            private val onCLickForDetails: (Recipes) -> Unit,
            private val onClickForDuplicate: (Recipes) -> Unit,
            private val onClickForDelete: (Recipes) -> Unit,
            private val onClickForEdit: (Recipes) -> Unit
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(recipe: Recipes){
            binding.apply {
                recipeNameText.text = recipe.recipeName
                itemRecipeImage.setImageURI(
                        if(recipe.recipeImage ==""){
                            Repository.repoDefaultImageUri
                        }else{
                            recipe.recipeImage.toUri()
                        })

                itemRecipeImage.setOnClickListener {
                    onCLickForDetails(recipe)
                }
                recipeNameText.setOnClickListener {
                    onCLickForDetails(recipe)
                }
                duplicateRecipeImage.setOnClickListener {
                    onClickForDuplicate(recipe)
                }
                deleteRecipeImage.setOnClickListener {
                    onClickForDelete(recipe)
                }
                editRecipeImage.setOnClickListener {
                    onClickForEdit(recipe)
                }
            }
        }
    }
}