package com.example.foodmanchu

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.foodmanchu.databinding.RecipeDetailsFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class RecipeDetailsFragment: Fragment(R.layout.recipe_details_fragment) {
    companion object{
        lateinit var databaseDetails:Database
        lateinit var recipe:Recipes
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = RecipeDetailsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        lateinit var mainActivity: MainActivity

        recipe = Repository.listOfRecipeForDetail[0]

        val defaultImageId = R.drawable.ic_default_image
        val uriDefaultImage = Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(defaultImageId))
                .appendPath(resources.getResourceTypeName(defaultImageId))
                .appendPath(resources.getResourceEntryName(defaultImageId))
                .build()

        binding.apply {
            recipeDetailsNameText.text = recipe.recipeName
            recipeDetailsDescriptionText.text = recipe.description
            recipeDetailsIngredients.text = recipe.ingredientsToUse
            recipeDetailsInstructionsText.text =recipe.cookingInstructions
            recipeDetailsPreptimeText.text = recipe.prepTime
            recipesDetailsCategoryText.text = recipe.recipeCategory
            detailsImage.setImageURI(
                    if(recipe.recipeImage == ""){
                        uriDefaultImage
                    }else{
                        recipe.recipeImage.toUri()
                    })
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
                CoroutineScope(IO).launch {
                    addDuplicateRecipe(duplicateRecipe)
                }
                mainActivity = activity as MainActivity
                mainActivity.swapFragments(RecipesFragment())
            }
            detailsDeleteRecipeButton.setOnClickListener {
               CoroutineScope(IO).launch {
                   deleteRecipe(recipe)
               }
                mainActivity = activity as MainActivity
                mainActivity.swapFragments(RecipesFragment())
            }
            detailsBackFab.setOnClickListener {
                mainActivity = activity as MainActivity
                mainActivity.swapFragments(RecipesFragment())
            }
        }
    }

    suspend fun addDuplicateRecipe(duplicateRecipe:Recipes){
        databaseDetails.recipesDao().addRecipe(duplicateRecipe)
    }
    suspend fun deleteRecipe(recipe:Recipes){
        databaseDetails.recipesDao().deleteFromRecipes(recipe.key)
    }

    fun setRecipeVar(thisRecipe:Recipes){
          recipe = thisRecipe
    }

    suspend fun getRecipeForDetails(key:Int):Recipes{
        val xrecipe = databaseDetails.recipesDao().findRecipeByKey(key)
        return xrecipe[0]
    }
}