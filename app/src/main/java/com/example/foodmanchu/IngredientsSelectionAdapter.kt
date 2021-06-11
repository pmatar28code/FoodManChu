package com.example.foodmanchu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmanchu.databinding.ItemIngredientsRecipeSelectionBinding

class IngredientsSelectionAdapter(

): ListAdapter<IngredientsChecked,IngredientsSelectionAdapter.IngredientsSelectionViewHolder>(diff) {
    companion object{
        val diff = object : DiffUtil.ItemCallback<IngredientsChecked>(){
            override fun areItemsTheSame(oldItem: IngredientsChecked, newItem: IngredientsChecked): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: IngredientsChecked, newItem: IngredientsChecked): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsSelectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientsRecipeSelectionBinding.inflate(inflater,parent,false)
        return IngredientsSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsSelectionViewHolder, position: Int) {
        holder.onBind(getItem(position))

    }

    class IngredientsSelectionViewHolder(

            private val binding : ItemIngredientsRecipeSelectionBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(ingredient: IngredientsChecked){
            binding.apply {
                ingredientRecipeSelectionNameText.text = ingredient.ingredientName
                var isChecked = ingredientCheckbox.isChecked
                if(isChecked){
                    Repository.IngredientsCheckedList.add(ingredient)
                }else{
                    Repository.IngredientsCheckedList.remove(ingredient)
                }

            }
        }
    }
}