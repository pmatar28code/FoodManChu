package com.example.foodmanchu

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodmanchu.databinding.FragmentAddRecipeBinding

class AddRecipeDialog:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val binding = FragmentAddRecipeBinding.inflate(inflater)

        val ingredientsAvailable = Repository.IngredientsList
        val adapterIngredients = ArrayAdapter(requireContext(), R.layout.ingredients_listing, ingredientsAvailable)

        (binding.addRecipeIngredientsLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterIngredients)


        val categoriesAvailable = Repository.categoryList
        val adapterCategories = ArrayAdapter(requireContext(),R.layout.ingredients_listing,categoriesAvailable)
        (binding.addRecipeCategoryLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterCategories)


        return AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Add"){_,_ ->


                }
                .setNegativeButton("Cancel",null)
                .create()

    }
}