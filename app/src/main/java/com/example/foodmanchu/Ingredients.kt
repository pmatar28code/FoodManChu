package com.example.foodmanchu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ingredients")
data class Ingredients(
        @PrimaryKey(autoGenerate = true) val key:Int = 0,
        @ColumnInfo(name = "ingredientName") val ingredientName:String
)