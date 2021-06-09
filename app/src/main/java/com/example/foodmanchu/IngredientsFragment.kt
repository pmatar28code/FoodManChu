package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.IngredientsFragmentBinding

class IngredientsFragment:Fragment(R.layout.ingredients_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = IngredientsFragmentBinding.bind(view)

        var ingredientsAdapter = IngredientsAdapter()
        val testing = ""
        binding.apply {
            ingredientsRecyclerview.apply{
                adapter = ingredientsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                ingredientsAdapter.submitList(Repository.IngredientsList)
            }

            addIngredientFab.setOnClickListener{
                AddIngredientDialog().show(parentFragmentManager,"Show Add Ingredient")
            }

        }

    }
}