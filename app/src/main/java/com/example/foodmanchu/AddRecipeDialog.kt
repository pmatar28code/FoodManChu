package com.example.foodmanchu

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodmanchu.databinding.FragmentAddRecipeBinding

class AddRecipeDialog:DialogFragment() {
    lateinit var binding: FragmentAddRecipeBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val inflater = LayoutInflater.from(requireContext())
    binding = FragmentAddRecipeBinding.inflate(inflater)

    val ingredientsAvailable = Repository.IngredientsList
    val adapterIngredients =
            ArrayAdapter(requireContext(),R.layout.ingredients_listing, ingredientsAvailable)

    (binding.addRecipeIngredientsLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterIngredients)


    val categoriesAvailable = Repository.categoryList
    val adapterCategories =
            ArrayAdapter(requireContext(),R.layout.ingredients_listing,categoriesAvailable)
    (binding.addRecipeCategoryLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterCategories)

    binding.button3.setOnClickListener {
        showDialog()
    }

    return AlertDialog.Builder(requireContext())
        .setView(binding.root)
        .setPositiveButton("Add"){_,_ ->
            addRecipeToListAndDataBase(binding)
        }
        .setNegativeButton("Cancel",null)
        .create()
    }

    private fun showDialog(){
        Repository.listOfSelectedIngredientsForRecipe.clear()
        lateinit var dialog:AlertDialog
        var arrayIngredients:Array<String> = Repository.IngredientsList.map { it.ingredientName }.toTypedArray()
        var mutableListFalse = mutableListOf<Boolean>()

        for(ingredient in Repository.IngredientsList){
            mutableListFalse.add(false)
        }

        var arrayChecked:BooleanArray = mutableListFalse.map { false }.toBooleanArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose your ingredients.")

        builder.setMultiChoiceItems(arrayIngredients, arrayChecked) { dialog, which, isChecked->
            arrayChecked[which] = isChecked

            //val ingredient = arrayIngredients[which]
            // Display the clicked item text
            //Log.e
        }

        builder.setPositiveButton("Add") { _, _ ->
            binding.textView.text = "Selected Ingredients \n"
            for (i in arrayIngredients.indices) {
                val checked = arrayChecked?.get(i)
                if (checked!!) {
                    binding.textView.text = "${binding.textView.text} ${arrayIngredients[i]},"
                    Repository.listOfSelectedIngredientsForRecipe.add(arrayIngredients[i])
                }
            }
        }
        dialog = builder.create()
        dialog.show()
    }

    fun addRecipeToListAndDataBase(binding:FragmentAddRecipeBinding){
        var ingredientsSelectedString = ""
        for(ingredient in Repository.listOfSelectedIngredientsForRecipe){
            ingredientsSelectedString += "$ingredient,"
        }
        ingredientsSelectedString.dropLast(1)
        var newRecipe = Recipes(
            recipeName = binding.addRecipeNameEditText.text?.toString()?:"",
            ingredientsToUse = ingredientsSelectedString,
            description = binding.addRecipeDescriptionEditText.text?.toString()?:"",
            cookingInstructions = binding.addRecipeInstructionsEditText.text?.toString()?:"",
            prepTime = binding.addRecipePrepTimeEditText.text?.toString()?:"",
            recipeCategory = binding.addRecipeCategoryLayout.editText?.text?.toString()?:""
        )
        Log.e("WHAT CATEGORY NEWRECIPE","${newRecipe.recipeCategory}")
        Repository.recipesList.add(newRecipe)
        Repository.recipesListFilterForCategoryClick.add(newRecipe)
        var mainActivity = activity as MainActivity
        mainActivity.addRecipe(newRecipe)
    }
}
