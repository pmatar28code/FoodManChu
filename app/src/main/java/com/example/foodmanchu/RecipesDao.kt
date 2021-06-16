package com.example.foodmanchu

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipesDao {
    @Insert
    fun addRecipe(ingredient:Recipes)

    @Query("DELETE FROM Recipes WHERE recipeName = :recipeName")
    fun deleteFromRecipes(recipeName: String)

    @Query("SELECT * FROM Recipes")
    fun getAllRecipes(): List<Recipes>

    @Query("SELECT * FROM Recipes WHERE ingredientsToUse LIKE '%' || :search || '%'  ")
    fun findIngredientFilter(search:String):MutableList<Recipes>

    @Query("SELECT * FROM Recipes WHERE recipeName LIKE '%' || :search || '%'  ")
    fun findNameFilter(search:String):MutableList<Recipes>

    @Query("SELECT * FROM Recipes WHERE description LIKE '%' || :search || '%'  ")
    fun findDescriptionFilter(search:String):MutableList<Recipes>

    @Query("SELECT * FROM Recipes WHERE recipeCategory LIKE '%' || :search || '%'  ")
    fun findCategoryFilter(search:String):MutableList<Recipes>

    @Query("SELECT * FROM Recipes WHERE prepTime LIKE '%' || :search || '%' ")
    fun findPrepTimeFilter(search:String):MutableList<Recipes>
}