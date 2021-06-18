package com.example.foodmanchu

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodmanchu.databinding.FragmentDeleteIngredientBinding

class DeleteIngredientDialog(): DialogFragment() {
    companion object{
        fun create(listener:()->Unit):DeleteIngredientDialog{
            return DeleteIngredientDialog().apply {
                this.listener = listener
            }
        }
    }
    lateinit var currentRecipe:Recipes
    private var listener : () -> Unit = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var theIngredientToDeleteFromAll:String
        val inflater = LayoutInflater.from(requireContext())
        val binding = FragmentDeleteIngredientBinding.inflate(inflater)

        var ingredientsToDelete = mutableListOf<String>()
        var defaultIngredientListString = Repository.defaultIngredientsList.map { it.ingredientName }
        var ingredientsListString = Repository.IngredientsList.map { it.ingredientName }

        for(ingredient in ingredientsListString){
            if(!defaultIngredientListString.contains(ingredient)){
                    ingredientsToDelete.add(ingredient)
            }
        }
        Log.e("FINAL LIST DROPDOWS","$ingredientsToDelete")


        val adapter = ArrayAdapter(requireContext(), R.layout.ingredients_listing, ingredientsToDelete)
        (binding.ingredientNameSelectionLayout.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        return AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Delete"){_,_ ->
                    var index:Int ?= null
                    for(ingredient in Repository.IngredientsList){
                        if(ingredient.ingredientName == binding.ingredientNameSelectionLayout.editText?.text.toString()){
                            Repository.IngredientsList.remove(ingredient)
                            index = 0
                            listener()
                            theIngredientToDeleteFromAll = binding.ingredientNameSelectionLayout.editText?.text.toString()
                            break
                        }
                    }
                    if(index !=null) {
                        //Log.e("INDEX","$index")
                        //Repository.IngredientsList. removeAt(index)
                        val mainActivity = activity as MainActivity
                        mainActivity.deleteIngredient(binding.ingredientNameSelectionLayout.editText?.text.toString())
                        removeDeletedIngredientFromAllRecipesThatIncludedIt(theIngredientToDeleteFromAll)

                    }

                }
                .setNegativeButton("Cancel") { _,_ ->
                    //ingredientsToDelete.clear()
                }
                .create()
    }

    fun removeDeletedIngredientFromAllRecipesThatIncludedIt(theIngredientToDeleteFromAll:String){
        var tempRecipesList = Repository.recipesList.map { it }.toMutableList()
        for(recipe in Repository.recipesList){
            currentRecipe = recipe
            if(recipe.ingredientsToUse.contains(theIngredientToDeleteFromAll)){
                tempRecipesList.remove(recipe)
                val mainActivity = activity as MainActivity
                mainActivity.deleteRecipe(recipe.recipeName)
                currentRecipe.ingredientsToUse = currentRecipe.ingredientsToUse.replace(theIngredientToDeleteFromAll,"*")
                Log.e("DELETED INGREDIENT FREC","${currentRecipe.ingredientsToUse}")
                tempRecipesList.add(currentRecipe)
                mainActivity.addRecipe(currentRecipe)
            }
        }
        Repository.recipesList.clear()
        Repository.recipesList = tempRecipesList.map { it }.toMutableList()
        Repository.recipesListFilterForCategoryClick.clear()
        Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
    }
}