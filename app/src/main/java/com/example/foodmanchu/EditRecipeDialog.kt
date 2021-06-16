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
import com.example.foodmanchu.databinding.FragmentEditRecipeBinding

class EditRecipeDialog(): DialogFragment() {
    lateinit var recipe:Recipes
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentEditRecipeBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = FragmentEditRecipeBinding.inflate(inflater)
        recipe = Repository.recipeToEdit
        mainActivity = activity as MainActivity

        binding.apply {
            editRecipeNameEditText.setText(recipe.recipeName)
            editRecipeCategoryLayout.editText?.setText(recipe.recipeCategory)
            editRecipePrepTimeEditText.setText(recipe.prepTime)
            editRecipeInstructionsEditText.setText(recipe.cookingInstructions)
            editRecipeDescriptionEditText.setText(recipe.description)
            editTextView.text = recipe.ingredientsToUse


            val ingredientsAvailable = Repository.IngredientsList
            val adapterIngredients =
                    ArrayAdapter(requireContext(), R.layout.ingredients_listing, ingredientsAvailable)

            (editRecipeIngredientsLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterIngredients)


            val categoriesAvailable = Repository.categoryList
            val adapterCategories =
                    ArrayAdapter(requireContext(), R.layout.ingredients_listing, categoriesAvailable)
            (editRecipeCategoryLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterCategories)

            editIngredientsButton.setOnClickListener {
                showDialog()
            }
        }

        return AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Apply"){_,_ ->
                    Repository.recipesList.remove(recipe)
                    mainActivity.deleteRecipe(recipe.recipeName)
                    addRecipeToListAndDataBase(binding)
                }
                .setNegativeButton("Cancel",null)
                .create()
    }

    private fun showDialog(){
        Repository.listOfSelectedIngredientsForRecipe.clear()
        lateinit var dialog: AlertDialog
        var arrayIngredients:Array<String> = Repository.IngredientsList.map { it.ingredientName }.toTypedArray()
        var mutableListFalse = mutableListOf<Boolean>()

        var recipe = Repository.recipeToEdit
        var listOfIngredientsS = recipe.ingredientsToUse.split(",")
        Log.e("ListOfIngredientsSSS","$listOfIngredientsS")
        for(ingredient in Repository.IngredientsList){
            if(listOfIngredientsS.contains(ingredient.ingredientName) || listOfIngredientsS.contains(ingredient.ingredientName+" ,") ||  listOfIngredientsS.contains(" "+ingredient.ingredientName)){
                mutableListFalse.add(true)
            }else {
                mutableListFalse.add(false)
            }
        }

        var arrayChecked:BooleanArray = mutableListFalse.map { it }.toBooleanArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose your ingredients.")

        builder.setMultiChoiceItems(arrayIngredients, arrayChecked) { dialog, which, isChecked->
            arrayChecked[which] = isChecked

            //val ingredient = arrayIngredients[which]
            // Display the clicked item text
            //Log.e
        }

        builder.setPositiveButton("Add") { _, _ ->
            binding.editTextView.text = "Selected Ingredients \n"
            for (i in arrayIngredients.indices) {
                val checked = arrayChecked?.get(i)
                if (checked!!) {
                    binding.editTextView.text = "${binding.editTextView.text} ${arrayIngredients[i]},"
                    Repository.listOfSelectedIngredientsForRecipe.add(arrayIngredients[i])
                }
            }
        }
        dialog = builder.create()
        dialog.show()
    }

    fun addRecipeToListAndDataBase(binding: FragmentEditRecipeBinding){
        var ingredientsSelectedString = ""
        for(ingredient in Repository.listOfSelectedIngredientsForRecipe){
            ingredientsSelectedString += "$ingredient,"
        }
        ingredientsSelectedString = ingredientsSelectedString.dropLast(1)
        var newRecipe = Recipes(
                recipeName = binding.editRecipeNameEditText.text?.toString()?:"",
                ingredientsToUse = ingredientsSelectedString,
                description = binding.editRecipeDescriptionEditText.text?.toString()?:"",
                cookingInstructions = binding.editRecipeInstructionsEditText.text?.toString()?:"",
                prepTime = binding.editRecipePrepTimeEditText.text?.toString()?:"",
                recipeCategory = binding.editRecipeCategoryLayout.editText?.text?.toString()?:""
        )
        Log.e("WHAT CATEGORY NEWRECIPE","${newRecipe.recipeCategory}")
        Repository.recipesList.add(newRecipe)
        Repository.recipesListFilterForCategoryClick.add(newRecipe)
        var mainActivity = activity as MainActivity
        mainActivity.addRecipe(newRecipe)
    }
}