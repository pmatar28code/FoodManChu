package com.example.foodmanchu

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.FragmentSelectIngredientBinding

class SelectIngredientDialog: DialogFragment() {
    lateinit var ingredientsSelectionAdapter: IngredientsSelectionAdapter
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val binding = FragmentSelectIngredientBinding.inflate(inflater)

        var ingredientAdapter :Ingredients ?=null
        ingredientsSelectionAdapter = IngredientsSelectionAdapter()
        binding.apply {
            ingredientsSelectionRecycler.apply {
                adapter = ingredientsSelectionAdapter
                layoutManager = LinearLayoutManager(requireContext())
                ingredientsSelectionAdapter.submitList(Repository.IngredientsCheckedList)
                ingredientsSelectionAdapter.notifyDataSetChanged()
            }
        }


        return AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Add") { _, _ ->
                Log.e("list of Checked ","${Repository.listTestCheckedIngredients}")

                }
                .setNegativeButton("Cancel", null)
                .create()
    }
}
