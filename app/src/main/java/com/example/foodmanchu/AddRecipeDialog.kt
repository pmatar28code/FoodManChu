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
        var index = 0
        var arrayColors:Array<String> = Repository.IngredientsList.map { it.ingredientName }.toTypedArray()

        var mutableListFalse = mutableListOf<Boolean>()
        for(ingredient in Repository.IngredientsList){
            mutableListFalse.add(false)
        }

        index = 0
        // Initialize a boolean array of checked items
        var arrayChecked:BooleanArray = mutableListFalse.map { false }.toBooleanArray()


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(requireContext())

        // Set a title for alert dialog
        builder.setTitle("Choose favorite colors.")

        /*
            **** reference source developer.android.com ***

            AlertDialog.Builder setMultiChoiceItems (CharSequence[] items,
                            boolean[] checkedItems,
                            DialogInterface.OnMultiChoiceClickListener listener)

            Set a list of items to be displayed in the dialog as the content, you will be notified
            of the selected item via the supplied listener. The list will have a check mark
            displayed to the right of the text for each checked item. Clicking on an item
            in the list will not dismiss the dialog. Clicking on a button will dismiss the dialog.

            Parameters
                items CharSequence : the text of the items to be displayed in the list.

                checkedItems boolean : specifies which items are checked. It should be null in which
                    case no items are checked. If non null it must be exactly the same length
                    as the array of items.

                listener DialogInterface.OnMultiChoiceClickListener : notified when an item on the
                    list is clicked. The dialog will not be dismissed when an item is clicked. It
                    will only be dismissed if clicked on a button, if no buttons are supplied
                    it's up to the user to dismiss the dialog.

            Returns
                AlertDialog.Builder This Builder object to allow for chaining of calls to set methods
        */

        // Define multiple choice items for alert dialog
        builder.setMultiChoiceItems(arrayColors, arrayChecked, {dialog,which,isChecked->
            // Update the clicked item checked status
            arrayChecked?.set(which, isChecked)

            // Get the clicked item
            val color = arrayColors?.get(which)

            // Display the clicked item text
            //toast("$color clicked.")
        })


        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { _, _ ->
            // Do something when click positive button
            binding.textView.text = "Your preferred colors..... \n"
            if (arrayColors != null) {
                for (i in 0 until arrayColors.size) {
                    val checked = arrayChecked?.get(i)
                    if (checked!!) {
                        binding.textView.text = "${binding.textView.text}  ${arrayColors[i]} \n"
                    }
                }
            }
        }


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}
