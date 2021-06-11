package com.example.foodmanchu

import android.content.AsyncTaskLoader
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

object Repository {
    var listTestCheckedIngredients = mutableListOf<IngredientsChecked>()

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

    var IngredientsCheckedList = mutableListOf<IngredientsChecked>(
            IngredientsChecked(ingredientName = "Beef",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Chicken",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Lettuce",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Pepper",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Beans",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Tomato",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Tomato",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "White",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Onion",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Purple onion",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Fish",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Vegetarian",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Shrimp",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Rice",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Garlic",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Salt",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Olive oil",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Egg",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Bacon",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Green chili",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Oil",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Ground beef",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Zucchini",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Carrot",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Potato",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Lemon",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Meat",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Basil",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "White cheese",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Yellow cheese",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Ham",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Sausage",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Avocado",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Butter",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Peas",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Broccoli",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Cauliflower",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Salmon",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Tuna",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Pork",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Lamb",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Mushroom",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Bread",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Bell pepper",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Chili pepper",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Tortilla",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Flour tortilla",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Asparagus",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Pasta",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Steak",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Spinach",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Cabbage",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Nuts",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Corn",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Mayonnaise",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Beef ribs",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Worcestershire sauce",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Maggi sauce",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Vegetarian",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Soy sauce",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Bread crumbs",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Mustard",ingredientIsChecked = false),
            IngredientsChecked(ingredientName = "Peperoni",ingredientIsChecked = false)
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