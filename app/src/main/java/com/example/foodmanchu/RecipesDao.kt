package com.example.foodmanchu

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipesDao {
    @Insert
    fun addRecipe(ingredient:Recipes)

    @Query("SELECT * FROM Recipes")
    fun getAllRecipes(): List<Recipes>

    @Query("SELECT * FROM Recipes WHERE ingredientsToUse LIKE :search  ")
    fun findWordsInclude(search:String):MutableList<Recipes>

}