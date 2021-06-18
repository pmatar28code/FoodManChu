package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
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
            detailsImage.setImageURI(recipe.recipeImage.toUri())
            detailsEditRecipeButton.setOnClickListener {
                Repository.recipeToEdit = recipe
                EditRecipeDialog.create{
                    var mainActivity = activity as MainActivity
                    mainActivity.swapFragments(RecipeDetailsFragment())
                }.show(parentFragmentManager,"edit dialog")
            }
            detailsDuplicateRecipeButton.setOnClickListener {

            }
            detailsDeleteRecipeButton.setOnClickListener {

            }
        }
    }
}