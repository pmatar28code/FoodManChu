package com.example.foodmanchu


import android.app.Dialog
import android.app.FragmentBreadCrumbs
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment



class AddIngredientDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_add_ingredient)
            .setPositiveButton("Add"){_,_ ->
                var ingredientText = view?.findViewById<EditText>(R.id.ingredient_name_edit_text).text
                if (!ingredientText.isBlank()) {
                    var newIngredient = Ingredients(ingredientName = ingredientText.toString())
                    Repository.IngredientsList.add(newIngredient)

                }
            }
            .setNegativeButton("Cancel",null)
            .create()



    }

}