package com.example.foodmanchu

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Ingredients::class, Recipes::class],
    version = 2
)

abstract class Database: RoomDatabase(){
    abstract fun ingredientsDao(): IngredientsDao
    abstract fun recipesDao():RecipesDao
}