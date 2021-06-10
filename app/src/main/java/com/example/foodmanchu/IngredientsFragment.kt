package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.IngredientsFragmentBinding

class IngredientsFragment:Fragment(R.layout.ingredients_fragment) {

    private lateinit var ingredientsAdapter: IngredientsAdapter 

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = IngredientsFragmentBinding.bind(view)

        ingredientsAdapter = IngredientsAdapter()

        binding.apply {
            ingredientsRecyclerview.apply{
                adapter = ingredientsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                ingredientsAdapter.submitList(Repository.IngredientsList)
            }

            addIngredientFab.setOnClickListener{
                AddIngredientDialog().show(parentFragmentManager,"Show Add Ingredient")
            }

            deleteIngredientFab.setOnClickListener {
                DeleteIngredientDialog().show(parentFragmentManager,"Show Delete Ingredient")
                //ingredientsAdapter?.notifyItemRemoved(DeleteIngredientDialog.indexx!!)
            }
            /*
            deleteIngredientFab.setOnClickListener{
                var index:Int ?= null
                for(ingredient in Repository.IngredientsList){
                    if(ingredient.ingredientName == "test"){
                        index = Repository.IngredientsList.indexOf(ingredient)
                        break
                    }
                }
                if(index !=null) {
                    Repository.IngredientsList.removeAt(index)
                    updateAdapter()
                    val mainActivity = activity as MainActivity
                    mainActivity.deleteIngredient("test")
                }else{
                    Toast.makeText(requireContext(),"Cant delete Ingredient, non found",Toast.LENGTH_LONG).show()
                }


            }*/

        }

    }



    fun updateAdapter(){
        ingredientsAdapter.notifyDataSetChanged()
    }

}
