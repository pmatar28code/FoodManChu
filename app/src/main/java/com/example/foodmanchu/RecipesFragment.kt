package com.example.foodmanchu

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.RecipesFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class RecipesFragment: Fragment(R.layout.recipes_fragment) {
    companion object{
        lateinit var databaseOnRecipes:Database
    }
    lateinit var recipesAdapter : RecipesAdapter
    lateinit var duplicateRecipe:Recipes


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RecipesFragmentBinding.bind(view)
        Log.e("Testing on Load frag","$databaseOnRecipes")

        recipesAdapter = RecipesAdapter(
        { recipeDetails ->
            RecipeDetailsFragment.databaseDetails = databaseOnRecipes
            RecipeDetailsFragment.recipe = recipeDetails
            Repository.listOfRecipeForDetail.clear()
            Repository.listOfRecipeForDetail.add(recipeDetails)
            val mainActivity = activity as MainActivity
            mainActivity.swapFragments(RecipeDetailsFragment())
        },{recipeDuplicate ->
            duplicateRecipe = Recipes(
                    recipeName = recipeDuplicate.recipeName +"_copy"+Date().time.toString(),
                    recipeCategory = recipeDuplicate.recipeCategory,
                    description = recipeDuplicate.description,
                    prepTime = recipeDuplicate.prepTime,
                    ingredientsToUse = recipeDuplicate.ingredientsToUse,
                    cookingInstructions = recipeDuplicate.cookingInstructions,
                    recipeImage = recipeDuplicate.recipeImage
            )
                CoroutineScope(IO).launch{
                    makeCopy()
                }
        },{recipeDelete ->
            CoroutineScope(IO).launch {
                deleteRecipe(recipeDelete)
            }
        },{recipeEdit ->
            Repository.recipeToEdit = recipeEdit
            EditRecipeDialog.create {
                CoroutineScope(IO).launch {
                    addToAdapterAndNotify()
                }
            }.show(parentFragmentManager,"Open Edit Recipe")
        })

        binding.apply {
            addRecipeFab.setOnClickListener {
                AddRecipeDialog.create {
                    CoroutineScope(IO).launch {
                        addToAdapterAndNotify()
                    }
                }.show(parentFragmentManager,"Open Add Recipe")
            }
            recipesRecyclerView.apply {
                adapter = recipesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                AsyncTask.execute {
                    var recipesList = databaseOnRecipes?.recipesDao()?.getAllRecipes()
                    Log.e("From DATABASE","$recipesList")
                    recipesAdapter.submitList(recipesList?.toMutableList())
                    recipesAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    suspend fun updateRecipesAdapter(){
        withContext(Main){
            recipesAdapter.notifyDataSetChanged()
        }
    }
    suspend fun makeCopy(){
        databaseOnRecipes.recipesDao().addRecipe(duplicateRecipe)
        recipesAdapter.submitList(databaseOnRecipes.recipesDao().getAllRecipes())
        updateRecipesAdapter()
    }

    suspend fun deleteRecipe(recipe:Recipes){
        databaseOnRecipes.recipesDao().deleteFromRecipes(recipe.key)
        recipesAdapter.submitList(databaseOnRecipes.recipesDao().getAllRecipes())
        updateRecipesAdapter()
    }

    suspend fun addToAdapterAndNotify(){
        recipesAdapter.submitList(databaseOnRecipes.recipesDao().getAllRecipes())
        updateRecipesAdapter()
    }
}