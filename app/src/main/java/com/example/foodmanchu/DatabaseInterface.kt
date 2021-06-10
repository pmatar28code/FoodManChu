package com.example.foodmanchu

interface DatabaseInterface {
    fun addIngredient(ingredient : Ingredients)
    fun deleteIngredient(ingredient:String)
}