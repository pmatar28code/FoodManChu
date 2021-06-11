package com.example.foodmanchu

import androidx.fragment.app.Fragment

interface DatabaseInterface {
    fun addIngredient(ingredient : Ingredients)
    fun deleteIngredient(ingredient:String)
    fun addRecipe(recipe:Recipes)
    fun deleteRecipe(recipe: String)
    fun swapFragments(fragment: Fragment)
}