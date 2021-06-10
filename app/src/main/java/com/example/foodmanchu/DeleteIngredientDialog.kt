package com.example.foodmanchu

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodmanchu.databinding.FragmentDeleteIngredientBinding

class DeleteIngredientDialog(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
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
                            Log.e("SEE IF REMOVED F LIST","${Repository.IngredientsList}")
                            index = 0
                            val ingredientsViewModel = ViewModelProvider(
                            this).get(IngredientsViewModel::class.java)
                            ingredientsViewModel.updateLiveIngredients(Repository.IngredientsList)
                            break
                        }
                    }
                    if(index !=null) {
                        //Log.e("INDEX","$index")
                        //Repository.IngredientsList. removeAt(index)
                        val mainActivity = activity as MainActivity
                        mainActivity.deleteIngredient(binding.ingredientNameSelectionLayout.editText?.text.toString())
                    }

                }
                .setNegativeButton("Cancel") { _,_ ->
                    //ingredientsToDelete.clear()
                }
                .create()
    }
}