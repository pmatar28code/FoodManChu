package com.example.foodmanchu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngredientsViewModel: ViewModel() {
    var liveIngredientsList = MutableLiveData<MutableList<Ingredients>>()

    init{
        liveIngredientsList.postValue(Repository.IngredientsList)
    }

    fun updateLiveIngredients(ingredientsList:MutableList<Ingredients>){
        liveIngredientsList.postValue(ingredientsList)
    }
}