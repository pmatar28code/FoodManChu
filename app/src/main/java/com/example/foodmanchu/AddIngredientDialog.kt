package com.example.foodmanchu


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodmanchu.databinding.FragmentAddIngredientBinding


class AddIngredientDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val binding = FragmentAddIngredientBinding.inflate(inflater)

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton("Add"){_,_ ->

                val ingredientTextView = binding.ingredientNameEditText
                val ingredientText = ingredientTextView.text?.toString() ?: ""

                if (ingredientText != "") {
                    val newIngredient = Ingredients(ingredientName = ingredientText)
                    Repository.IngredientsList.add(newIngredient)
                    val mainActivity = activity as MainActivity
                    mainActivity.addIngredient(newIngredient)
                }else{
                    Toast.makeText(requireContext(),"Please enter ingredient name actual: $ingredientText",Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("Cancel",null)
            .create()



    }

}