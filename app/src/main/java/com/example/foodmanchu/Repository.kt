package com.example.foodmanchu

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

object Repository {

    var listOfSelectedIngredientsForRecipe = mutableListOf<String>()

    var categoryList = listOf(
        "Meat", "Vegetarian", "Vegan", "Paleo", "Keto"
    )

    var recipesList = mutableListOf<Recipes>(
            Recipes(
                recipeName = "scrambled Eggs",
                ingredientsToUse = "eggs,salt",
                description = "delicious scrambled eggs",
                cookingInstructions = "mix to eggs,add salt,cook for 3.5 minutes,enjoy",
                prepTime = "4 minutes",
                recipeCategory = "Keto"
            )
    )
    var recipesListFilterForCategoryClick = mutableListOf<Recipes>()

    val defaultIngredientsList = listOf(
        Ingredients(ingredientName = "beef"),
        Ingredients(ingredientName = "chicken"),
        Ingredients(ingredientName = "lettuce"),
        Ingredients(ingredientName = "pepper"),
        Ingredients(ingredientName = "beans"),
        Ingredients(ingredientName = "tomato"),
        Ingredients(ingredientName = "white"),
        Ingredients(ingredientName = "onion"),
        Ingredients(ingredientName = "purple_onion"),
        Ingredients(ingredientName = "fish"),
        Ingredients(ingredientName = "vegetarian"),
        Ingredients(ingredientName = "shrimp"),
        Ingredients(ingredientName = "rice"),
        Ingredients(ingredientName = "garlic"),
        Ingredients(ingredientName = "salt"),
        Ingredients(ingredientName = "olive oil"),
        Ingredients(ingredientName = "egg"),
        Ingredients(ingredientName = "bacon"),
        Ingredients(ingredientName = "green_chili"),
        Ingredients(ingredientName = "oil"),
        Ingredients(ingredientName = "ground beef"),
        Ingredients(ingredientName = "zucchini"),
        Ingredients(ingredientName = "carrot"),
        Ingredients(ingredientName = "potato"),
        Ingredients(ingredientName = "lemon"),
        Ingredients(ingredientName = "meat"),
        Ingredients(ingredientName = "basil"),
        Ingredients(ingredientName = "white_cheese"),
        Ingredients(ingredientName = "yellow_cheese"),
        Ingredients(ingredientName = "ham"),
        Ingredients(ingredientName = "sausage"),
        Ingredients(ingredientName = "avocado"),
        Ingredients(ingredientName = "butter"),
        Ingredients(ingredientName = "peas"),
        Ingredients(ingredientName = "broccoli"),
        Ingredients(ingredientName = "cauliflower"),
        Ingredients(ingredientName = "salmon"),
        Ingredients(ingredientName = "tuna"),
        Ingredients(ingredientName = "pork"),
        Ingredients(ingredientName = "lamb"),
        Ingredients(ingredientName = "mushroom"),
        Ingredients(ingredientName = "bread"),
        Ingredients(ingredientName = "bell_pepper"),
        Ingredients(ingredientName = "chili_pepper"),
        Ingredients(ingredientName = "tortilla"),
        Ingredients(ingredientName = "flour_tortilla"),
        Ingredients(ingredientName = "asparagus"),
        Ingredients(ingredientName = "pasta"),
        Ingredients(ingredientName = "steak"),
        Ingredients(ingredientName = "spinach"),
        Ingredients(ingredientName = "cabbage"),
        Ingredients(ingredientName = "nuts"),
        Ingredients(ingredientName = "corn"),
        Ingredients(ingredientName = "mayonnaise"),
        Ingredients(ingredientName = "beef_ribs"),
        Ingredients(ingredientName = "worcestershire_sauce"),
        Ingredients(ingredientName = "maggi_sauce"),
        Ingredients(ingredientName = "vegetarian"),
        Ingredients(ingredientName = "soy_sauce"),
        Ingredients(ingredientName = "bread_crumbs"),
        Ingredients(ingredientName = "mustard"),
        Ingredients(ingredientName = "peperoni")
    )
    var IngredientsList = mutableListOf(
        Ingredients(ingredientName = "beef"),
        Ingredients(ingredientName = "chicken"),
        Ingredients(ingredientName = "lettuce"),
        Ingredients(ingredientName = "pepper"),
        Ingredients(ingredientName = "beans"),
        Ingredients(ingredientName = "tomato"),
        Ingredients(ingredientName = "white"),
        Ingredients(ingredientName = "onion"),
        Ingredients(ingredientName = "purple_onion"),
        Ingredients(ingredientName = "fish"),
        Ingredients(ingredientName = "vegetarian"),
        Ingredients(ingredientName = "shrimp"),
        Ingredients(ingredientName = "rice"),
        Ingredients(ingredientName = "garlic"),
        Ingredients(ingredientName = "salt"),
        Ingredients(ingredientName = "olive oil"),
        Ingredients(ingredientName = "egg"),
        Ingredients(ingredientName = "bacon"),
        Ingredients(ingredientName = "green_chili"),
        Ingredients(ingredientName = "oil"),
        Ingredients(ingredientName = "ground beef"),
        Ingredients(ingredientName = "zucchini"),
        Ingredients(ingredientName = "carrot"),
        Ingredients(ingredientName = "potato"),
        Ingredients(ingredientName = "lemon"),
        Ingredients(ingredientName = "meat"),
        Ingredients(ingredientName = "basil"),
        Ingredients(ingredientName = "white_cheese"),
        Ingredients(ingredientName = "yellow_cheese"),
        Ingredients(ingredientName = "ham"),
        Ingredients(ingredientName = "sausage"),
        Ingredients(ingredientName = "avocado"),
        Ingredients(ingredientName = "butter"),
        Ingredients(ingredientName = "peas"),
        Ingredients(ingredientName = "broccoli"),
        Ingredients(ingredientName = "cauliflower"),
        Ingredients(ingredientName = "salmon"),
        Ingredients(ingredientName = "tuna"),
        Ingredients(ingredientName = "pork"),
        Ingredients(ingredientName = "lamb"),
        Ingredients(ingredientName = "mushroom"),
        Ingredients(ingredientName = "bread"),
        Ingredients(ingredientName = "bell_pepper"),
        Ingredients(ingredientName = "chili_pepper"),
        Ingredients(ingredientName = "tortilla"),
        Ingredients(ingredientName = "flour_tortilla"),
        Ingredients(ingredientName = "asparagus"),
        Ingredients(ingredientName = "pasta"),
        Ingredients(ingredientName = "steak"),
        Ingredients(ingredientName = "spinach"),
        Ingredients(ingredientName = "cabbage"),
        Ingredients(ingredientName = "nuts"),
        Ingredients(ingredientName = "corn"),
        Ingredients(ingredientName = "mayonnaise"),
        Ingredients(ingredientName = "beef_ribs"),
        Ingredients(ingredientName = "worcestershire_sauce"),
        Ingredients(ingredientName = "maggi_sauce"),
        Ingredients(ingredientName = "vegetarian"),
        Ingredients(ingredientName = "soy_sauce"),
        Ingredients(ingredientName = "bread_crumbs"),
        Ingredients(ingredientName = "mustard"),
        Ingredients(ingredientName = "peperoni")
        )

    var defaultRecipesList = mutableListOf<Recipes>(
            Recipes(
                    recipeName = "scrambled Eggs",
                    ingredientsToUse = "eggs,salt",
                    description = "delicious scrambled eggs",
                    cookingInstructions = "mix to eggs,add salt,cook for 3.5 minutes,enjoy",
                    prepTime = "4 minutes",
                    recipeCategory = "Keto"
            )
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

    fun checkIfDatabaseForRecipesIsEmpty(database: Database){
        AsyncTask.execute {
            var testListRecipes = database.recipesDao().getAllRecipes()

            if (testListRecipes.isEmpty()) {
                Log.e("ServerEmpty", "so we are adding default Recipes to Database")
                for (recipe in defaultRecipesList) {
                    AsyncTask.execute {
                        database.recipesDao().addRecipe(recipe)
                    }
                }
            } else {
                Log.e("ServerNotEmpty", "so we are copying Database Recipes to list")
                recipesList.clear()
                for (recipe in testListRecipes) {
                    recipesList.add(recipe)
                }
            }
        }
    }

    fun filterRecipesByCategoryForCategoryClick(context:Context,category:String,activity: MainActivity){
        recipesListFilterForCategoryClick.clear()
        when(category){
            "Meat" -> {
                forLoopAddRecipeByCategoryAndSwap(context,category, activity)
            }
            "Vegetarian" -> {
                forLoopAddRecipeByCategoryAndSwap(context,category,activity)
            }
            "Vegan" -> {
                forLoopAddRecipeByCategoryAndSwap(context,category,activity)
            }
            "Paleo" -> {
                forLoopAddRecipeByCategoryAndSwap(context,category,activity)
            }
            "Keto" -> {
                forLoopAddRecipeByCategoryAndSwap(context,category,activity)
            }
            else -> {
                }
        }

    }

    fun forLoopAddRecipeByCategoryAndSwap(context:Context,category: String,activity: MainActivity){
        for(recipe in recipesList){
            if(recipe.recipeCategory == category){
                recipesListFilterForCategoryClick.add(recipe)
            }
        }
        if(recipesListFilterForCategoryClick.isNotEmpty()){
            var activityMain = activity
            activityMain.swapFragments(RecipesFragment())
        }else{
            Toast.makeText(context,"There are no recipes in this category: $category",Toast.LENGTH_SHORT).show()
        }
    }
}

