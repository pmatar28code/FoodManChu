package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodmanchu.databinding.RecipeDetailsFragmentBinding

class RecipeDetailsFragment: Fragment(R.layout.recipe_details_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RecipeDetailsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val recipe = Repository.listOfRecipeForDetail[0]

        binding.apply {
            recipeDetailsNameText.text = recipe.recipeName
            recipeDetailsDescriptionText.text = recipe.description
            recipeDetailsIngredients.text = recipe.ingredientsToUse
            recipeDetailsInstructionsText.text =recipe.cookingInstructions
            recipeDetailsPreptimeText.text = recipe.prepTime
            recipesDetailsCategoryText.text = recipe.recipeCategory
        }


    }
}