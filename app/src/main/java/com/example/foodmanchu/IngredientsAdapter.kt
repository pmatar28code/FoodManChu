package com.example.foodmanchu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmanchu.databinding.ItemIngredientsBinding

class IngredientsAdapter(
    ): ListAdapter<Ingredients,IngredientsAdapter.IngredientsViewHolder>(diff) {
        companion object{
            val diff = object : DiffUtil.ItemCallback<Ingredients>(){
                override fun areItemsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
                    return oldItem == newItem
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemIngredientsBinding.inflate(inflater,parent,false)
            return IngredientsViewHolder(binding)
        }

        override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
            holder.onBind(getItem(position))

        }

        class IngredientsViewHolder(
            private val binding : ItemIngredientsBinding
        ): RecyclerView.ViewHolder(binding.root){
            fun onBind(ingredient: Ingredients){
                binding.apply {
                    ingredientText.text = ingredient.ingredientName

                }
            }
        }
}