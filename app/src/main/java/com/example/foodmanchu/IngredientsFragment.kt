package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.IngredientsFragmentBinding
import java.math.RoundingMode

class IngredientsFragment:Fragment(R.layout.ingredients_fragment) {

    private lateinit var ingredientsAdapter: IngredientsAdapter 

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = IngredientsFragmentBinding.bind(view)

        ingredientsAdapter = IngredientsAdapter()

        val ingredientsViewModel = ViewModelProvider(
                this).get(IngredientsViewModel::class.java)

        val liveIngredients = ingredientsViewModel.liveIngredientsList



        binding.apply {
            ingredientsRecyclerview.apply{
                liveIngredients.observe(viewLifecycleOwner, Observer {
                    adapter = ingredientsAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                    ingredientsAdapter.submitList(it)
                    ingredientsAdapter.notifyDataSetChanged()

                })

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
