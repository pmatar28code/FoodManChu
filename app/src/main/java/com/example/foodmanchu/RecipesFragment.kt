package com.example.foodmanchu

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.RecipesFragmentBinding
import java.util.*

class RecipesFragment: Fragment(R.layout.recipes_fragment) {
    lateinit var recipesAdapter : RecipesAdapter
    companion object{
        lateinit var databaseOnRecipes:Database

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RecipesFragmentBinding.bind(view)
        Log.e("Testing on Load frag","$databaseOnRecipes")

        recipesAdapter = RecipesAdapter({ recipeDetails ->
            Repository.listOfRecipeForDetail.clear()
            Repository.listOfRecipeForDetail.add(recipeDetails)
            val mainActivity = activity as MainActivity
            mainActivity.swapFragments(RecipeDetailsFragment())
        },{recipeDuplicate ->
            val duplicateRecipe = Recipes(
                    recipeName = recipeDuplicate.recipeName +"_copy"+Date().time.toString(),
                    recipeCategory = recipeDuplicate.recipeCategory,
                    description = recipeDuplicate.description,
                    prepTime = recipeDuplicate.prepTime,
                    ingredientsToUse = recipeDuplicate.ingredientsToUse,
                    cookingInstructions = recipeDuplicate.cookingInstructions,
                    recipeImage = recipeDuplicate.recipeImage
            )
            //Repository.recipesList.add(duplicateRecipe)
            //Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
            AsyncTask.execute {
                databaseOnRecipes.recipesDao().addRecipe(duplicateRecipe)
                recipesAdapter.submitList(Repository.recipesListFilterForCategoryClick)
                runOnUiThread{}
                recipesAdapter.notifyDataSetChanged()
            }
            //recipesAdapter.notifyDataSetChanged()



        },{recipeDelete ->
            Repository.recipesList.remove(recipeDelete)
            Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
            recipesAdapter.submitList(Repository.recipesListFilterForCategoryClick)
            recipesAdapter.notifyDataSetChanged()
            val mainActivity = activity as MainActivity
            mainActivity.deleteRecipe(recipeDelete.recipeName)
        },{recipeEdit ->
            Repository.recipeToEdit = recipeEdit
            EditRecipeDialog.create {
                recipesAdapter.submitList(Repository.recipesListFilterForCategoryClick)
                recipesAdapter.notifyDataSetChanged()
            }.show(parentFragmentManager,"Open Edit Recipe")
        })

        binding.apply {
            addRecipeFab.setOnClickListener {
                AddRecipeDialog.create {
                    recipesAdapter.submitList(Repository.recipesListFilterForCategoryClick)
                    recipesAdapter.notifyDataSetChanged()
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
    fun copyAsync(){
        AsyncTask.execute {
            runOnUiThread{

            }
        }
    }
}