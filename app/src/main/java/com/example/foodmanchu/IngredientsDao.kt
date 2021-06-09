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



    //@Query("SELECT * FROM Words WHERE word NOT LIKE :notSearch0 AND word NOT LIKE :notSearch AND word NOT LIKE :notSearch2 AND word NOT LIKE :notSearch3 AND word NOT LIKE :notSearch4 AND word NOT LIKE :notSearch5 AND word NOT LIKE :notSearch6 AND word NOT LIKE :notSearch7 AND word NOT LIKE :notSearch8 AND word NOT LIKE :notSearch9 AND word NOT LIKE :notSearch10 AND word NOT LIKE :notSearch11 AND word NOT LIKE :notSearch12 AND word NOT LIKE :notSearch13 AND word NOT LIKE :notSearch14 AND word NOT LIKE :notSearch15 AND word NOT LIKE :notSearch16 AND word NOT LIKE :notSearch17 AND word NOT LIKE :notSearch18 AND word NOT LIKE :notSearch19")
    //fun findWordExclude(notSearch0:String,notSearch:String,notSearch2:String,notSearch3:String,notSearch4:String,notSearch5:String,notSearch6:String,notSearch7:String,notSearch8:String,notSearch9:String,notSearch10:String,notSearch11:String,notSearch12:String,notSearch13:String,notSearch14:String,notSearch15:String,notSearch16:String,notSearch17:String,notSearch18:String,notSearch19:String):MutableList<Words>

}