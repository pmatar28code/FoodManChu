package com.example.foodmanchu

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientsDao {
    @Insert
    fun addIngredient(ingredient:Ingredients)

    @Query("SELECT * FROM Ingredients")
    fun getAllIngredients(): List<Ingredients>

    @Query("DELETE FROM Ingredients WHERE ingredientName = :ingredient")
    fun deleteFromIngredients(ingredient: String?)
}