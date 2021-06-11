package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.RecipesFragmentBinding

class RecipesFragment: Fragment(R.layout.recipes_fragment) {
    lateinit var recipesAdapter : RecipesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RecipesFragmentBinding.bind(view)

        recipesAdapter = RecipesAdapter()

        binding.apply {
            addRecipeFab.setOnClickListener {
                AddRecipeDialog().show(parentFragmentManager,"Open Add Recipe")
            }

            recipesRecyclerView.apply {
                adapter = recipesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                recipesAdapter.submitList(Repository.recipesListFilterForCategoryClick)
                recipesAdapter.notifyDataSetChanged()
            }
        }
    }
}