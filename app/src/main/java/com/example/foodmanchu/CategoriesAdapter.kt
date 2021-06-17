package com.example.foodmanchu

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmanchu.databinding.ItemCategoriesBinding

class CategoriesAdapter(
    var onClick : (String) -> Unit
): ListAdapter<String,CategoriesAdapter.CategoriesViewHolder>(diff) {
    companion object{
        val diff = object : DiffUtil.ItemCallback<String>(){
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater,parent,false)
        return CategoriesViewHolder(binding,onClick)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(getItem(position))
        var item = getItem(position)
    }

    class CategoriesViewHolder(
        private val binding : ItemCategoriesBinding,
        private val onClick: (String) -> Unit
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: String){
            binding.apply {
                categorieText.text = item
                categorieText.setOnClickListener {
                    onClick(item)
                }
                when(item){
                    "Meat" -> {
                        categoryImage.setImageResource(R.drawable.meat)
                    }
                    "Vegetarian" -> {
                        categoryImage.setImageResource(R.drawable.vegetarian)
                    }
                    "Vegan" -> {
                        categoryImage.setImageResource(R.drawable.vegan)
                    }
                    "Paleo" -> {
                        categoryImage.setImageResource(R.drawable.paleo)
                    }
                    "Keto" -> {
                        categoryImage.setImageResource(R.drawable.keto)
                    }
                    else -> {}
                }
            }
        }
    }
}