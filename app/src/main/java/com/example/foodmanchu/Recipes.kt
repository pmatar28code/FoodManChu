package com.example.foodmanchu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true)val key:Int = 0,
    @ColumnInfo(name = "recipeName") var recipeName:String,
    @ColumnInfo(name = "ingredientsToUse") val ingredientsToUse:String,
    @ColumnInfo(name= "description") val description:String,
    @ColumnInfo(name = "cookingInstructions") val cookingInstructions:String,
    @ColumnInfo(name = "prepTime") val prepTime:String,
    @ColumnInfo(name = "recipeCategory") val recipeCategory:String
)