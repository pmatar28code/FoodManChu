package com.example.foodmanchu

import android.content.AsyncTaskLoader
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

object Repository {
    var categoryList = listOf(
        "Meat", "Vegetarian", "Vegan", "Paleo", "Keto"
    )
    val defaultIngredientsList = listOf(
            Ingredients(ingredientName = "Beef"),
            Ingredients(ingredientName = "Chicken"),
            Ingredients(ingredientName = "Lettuce"),
            Ingredients(ingredientName = "Pepper"),
            Ingredients(ingredientName = "Beans"),
            Ingredients(ingredientName = "Tomato"),
            Ingredients(ingredientName = "Tomato"),
            Ingredients(ingredientName = "White"),
            Ingredients(ingredientName = "Onion"),
            Ingredients(ingredientName = "Purple onion"),
            Ingredients(ingredientName = "Fish"),
            Ingredients(ingredientName = "Vegetarian"),
            Ingredients(ingredientName = "Shrimp"),
            Ingredients(ingredientName = "Rice"),
            Ingredients(ingredientName = "Garlic"),
            Ingredients(ingredientName = "Salt"),
            Ingredients(ingredientName = "Olive oil"),
            Ingredients(ingredientName = "Egg"),
            Ingredients(ingredientName = "Bacon"),
            Ingredients(ingredientName = "Green chili"),
            Ingredients(ingredientName = "Oil"),
            Ingredients(ingredientName = "Ground beef"),
            Ingredients(ingredientName = "Zucchini"),
            Ingredients(ingredientName = "Carrot"),
            Ingredients(ingredientName = "Potato"),
            Ingredients(ingredientName = "Lemon"),
            Ingredients(ingredientName = "Meat"),
            Ingredients(ingredientName = "Basil"),
            Ingredients(ingredientName = "White cheese"),
            Ingredients(ingredientName = "Yellow cheese"),
            Ingredients(ingredientName = "Ham"),
            Ingredients(ingredientName = "Sausage"),
            Ingredients(ingredientName = "Avocado"),
            Ingredients(ingredientName = "Butter"),
            Ingredients(ingredientName = "Peas"),
            Ingredients(ingredientName = "Broccoli"),
            Ingredients(ingredientName = "Cauliflower"),
            Ingredients(ingredientName = "Salmon"),
            Ingredients(ingredientName = "Tuna"),
            Ingredients(ingredientName = "Pork"),
            Ingredients(ingredientName = "Lamb"),
            Ingredients(ingredientName = "Mushroom"),
            Ingredients(ingredientName = "Bread"),
            Ingredients(ingredientName = "Bell pepper"),
            Ingredients(ingredientName = "Chili pepper"),
            Ingredients(ingredientName = "Tortilla"),
            Ingredients(ingredientName = "Flour tortilla"),
            Ingredients(ingredientName = "Asparagus"),
            Ingredients(ingredientName = "Pasta"),
            Ingredients(ingredientName = "Steak"),
            Ingredients(ingredientName = "Spinach"),
            Ingredients(ingredientName = "Cabbage"),
            Ingredients(ingredientName = "Nuts"),
            Ingredients(ingredientName = "Corn"),
            Ingredients(ingredientName = "Mayonnaise"),
            Ingredients(ingredientName = "Beef ribs"),
            Ingredients(ingredientName = "Worcestershire sauce"),
            Ingredients(ingredientName = "Maggi sauce"),
            Ingredients(ingredientName = "Vegetarian"),
            Ingredients(ingredientName = "Soy sauce"),
            Ingredients(ingredientName = "Bread crumbs"),
            Ingredients(ingredientName = "Mustard"),
            Ingredients(ingredientName = "Peperoni")
    )
    var IngredientsList = mutableListOf(
        Ingredients(ingredientName = "Beef"),
        Ingredients(ingredientName = "Chicken"),
        Ingredients(ingredientName = "Lettuce"),
        Ingredients(ingredientName = "Pepper"),
        Ingredients(ingredientName = "Beans"),
        Ingredients(ingredientName = "Tomato"),
        Ingredients(ingredientName = "Tomato"),
        Ingredients(ingredientName = "White"),
        Ingredients(ingredientName = "Onion"),
        Ingredients(ingredientName = "Purple onion"),
        Ingredients(ingredientName = "Fish"),
        Ingredients(ingredientName = "Vegetarian"),
        Ingredients(ingredientName = "Shrimp"),
        Ingredients(ingredientName = "Rice"),
        Ingredients(ingredientName = "Garlic"),
        Ingredients(ingredientName = "Salt"),
        Ingredients(ingredientName = "Olive oil"),
        Ingredients(ingredientName = "Egg"),
        Ingredients(ingredientName = "Bacon"),
        Ingredients(ingredientName = "Green chili"),
        Ingredients(ingredientName = "Oil"),
        Ingredients(ingredientName = "Ground beef"),
        Ingredients(ingredientName = "Zucchini"),
        Ingredients(ingredientName = "Carrot"),
        Ingredients(ingredientName = "Potato"),
        Ingredients(ingredientName = "Lemon"),
        Ingredients(ingredientName = "Meat"),
        Ingredients(ingredientName = "Basil"),
        Ingredients(ingredientName = "White cheese"),
        Ingredients(ingredientName = "Yellow cheese"),
        Ingredients(ingredientName = "Ham"),
        Ingredients(ingredientName = "Sausage"),
        Ingredients(ingredientName = "Avocado"),
        Ingredients(ingredientName = "Butter"),
        Ingredients(ingredientName = "Peas"),
        Ingredients(ingredientName = "Broccoli"),
        Ingredients(ingredientName = "Cauliflower"),
        Ingredients(ingredientName = "Salmon"),
        Ingredients(ingredientName = "Tuna"),
        Ingredients(ingredientName = "Pork"),
        Ingredients(ingredientName = "Lamb"),
        Ingredients(ingredientName = "Mushroom"),
        Ingredients(ingredientName = "Bread"),
        Ingredients(ingredientName = "Bell pepper"),
        Ingredients(ingredientName = "Chili pepper"),
        Ingredients(ingredientName = "Tortilla"),
        Ingredients(ingredientName = "Flour tortilla"),
        Ingredients(ingredientName = "Asparagus"),
        Ingredients(ingredientName = "Pasta"),
        Ingredients(ingredientName = "Steak"),
        Ingredients(ingredientName = "Spinach"),
        Ingredients(ingredientName = "Cabbage"),
        Ingredients(ingredientName = "Nuts"),
        Ingredients(ingredientName = "Corn"),
        Ingredients(ingredientName = "Mayonnaise"),
        Ingredients(ingredientName = "Beef ribs"),
        Ingredients(ingredientName = "Worcestershire sauce"),
        Ingredients(ingredientName = "Maggi sauce"),
        Ingredients(ingredientName = "Vegetarian"),
        Ingredients(ingredientName = "Soy sauce"),
        Ingredients(ingredientName = "Bread crumbs"),
        Ingredients(ingredientName = "Mustard"),
        Ingredients(ingredientName = "Peperoni")
    )

    fun checkIfDatabaseForIngredientsIsEmpty(database: Database){
        AsyncTask.execute {
            var testListIngredients = database.ingredientsDao().getAllIngredients()

            if (testListIngredients.isEmpty()) {
                Log.e("ServerEmpty", "so we are adding default ingredients to Database")
                for (ingredient in defaultIngredientsList) {
                    AsyncTask.execute {
                        database.ingredientsDao().addIngredient(ingredient)
                    }
                }
            } else {
                Log.e("ServerNotEmpty", "so we are copying Database ingredients to list")
                IngredientsList.clear()
                for (ingredient in testListIngredients) {
                    IngredientsList.add(ingredient)
                }
            }
        }
    }
}