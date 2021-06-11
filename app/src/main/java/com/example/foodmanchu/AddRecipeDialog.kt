package com.example.foodmanchu

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodmanchu.databinding.FragmentAddRecipeBinding

class AddRecipeDialog:DialogFragment() {
    lateinit var binding: FragmentAddRecipeBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        binding = FragmentAddRecipeBinding.inflate(inflater)

        val ingredientsAvailable = Repository.IngredientsList
        val adapterIngredients = ArrayAdapter(requireContext(), R.layout.ingredients_listing, ingredientsAvailable)

        (binding.addRecipeIngredientsLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterIngredients)


        val categoriesAvailable = Repository.categoryList
        val adapterCategories = ArrayAdapter(requireContext(),R.layout.ingredients_listing,categoriesAvailable)
        (binding.addRecipeCategoryLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterCategories)

        binding.button3.setOnClickListener {
            showDialog()
        }

        return AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Add"){_,_ ->


                }
                .setNegativeButton("Cancel",null)
                .create()

    }

    private fun showDialog(){
        // Late initialize an alert dialog object
        lateinit var dialog:AlertDialog

        // Initialize an array of colors
        var arrayIngredients:Array<String> = Repository.IngredientsList.map { it.ingredientName }.toTypedArray()

        var mutableListFalse = mutableListOf<Boolean>()
        for(ingredient in Repository.IngredientsList){
            mutableListFalse.add(false)
        }

        // Initialize a boolean array of checked items
        var arrayChecked:BooleanArray = mutableListFalse.map { false }.toBooleanArray()


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(requireContext())

        // Set a title for alert dialog
        builder.setTitle("Choose favorite colors.")



        // Define multiple choice items for alert dialog
        builder.setMultiChoiceItems(arrayIngredients, arrayChecked) { dialog, which, isChecked->
            // Update the clicked item checked status
            arrayChecked[which] = isChecked

            // Get the clicked item
            val color = arrayIngredients[which]

            // Display the clicked item text
            //toast("$color clicked.")
        }


        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { _, _ ->
            // Do something when click positive button
            binding.textView.text = "Your preferred colors..... \n"
            for (i in arrayIngredients.indices) {
                val checked = arrayChecked?.get(i)
                if (checked!!) {
                    binding.textView.text = "${binding.textView.text}  ${arrayIngredients[i]} \n"
                }
            }
        }


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}
