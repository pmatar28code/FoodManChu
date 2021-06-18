package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.foodmanchu.databinding.RecipeDetailsFragmentBinding
import java.util.*

class RecipeDetailsFragment: Fragment(R.layout.recipe_details_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RecipeDetailsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        lateinit var mainActivity: MainActivity
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
                    mainActivity = activity as MainActivity
                    mainActivity.swapFragments(RecipeDetailsFragment())
                }.show(parentFragmentManager,"edit dialog")
            }
            detailsDuplicateRecipeButton.setOnClickListener {
                var duplicateRecipe = Recipes(
                        recipeName = recipe.recipeName +"_copy"+ Date().time.toString(),
                        recipeCategory = recipe.recipeCategory,
                        description = recipe.description,
                        prepTime = recipe.prepTime,
                        ingredientsToUse = recipe.ingredientsToUse,
                        cookingInstructions = recipe.cookingInstructions,
                        recipeImage = recipe.recipeImage
                )
                Repository.recipesList.add(duplicateRecipe)
                Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
                mainActivity = activity as MainActivity
                mainActivity.addRecipe(duplicateRecipe)
                mainActivity.swapFragments(RecipesFragment())
            }
            detailsDeleteRecipeButton.setOnClickListener {
                Repository.recipesList.remove(recipe)
                Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
                mainActivity = activity as MainActivity
                mainActivity.deleteRecipe(recipe.recipeName)
                mainActivity.swapFragments(RecipesFragment())
            }
            detailsBackImageAsButton.setOnClickListener {
                mainActivity = activity as MainActivity
                mainActivity.swapFragments(RecipesFragment())
            }
        }
    }
}