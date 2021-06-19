package com.example.foodmanchu

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri

object Repository {
    var repoDefaultImageUri ="".toUri()

    var recipeToEdit = Recipes(
            recipeName = "",
            ingredientsToUse = "",
            description = "",
            cookingInstructions = "",
            prepTime = "",
            recipeCategory = "",
            recipeImage = ""
    )

    var filtersList = mutableListOf(
            "Ingredients",
            "PrepTime",
            "RecipeName",
            "Description",
            "Category"
    )

    var listOfRecipeForDetail = mutableListOf<Recipes>()

    var listOfSelectedIngredientsForRecipe = mutableListOf<String>()

    var categoryList = listOf(
        "Meat", "Vegetarian", "Vegan", "Paleo", "Keto"
    )

    var recipesList = mutableListOf<Recipes>()
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
            recipeName = "Lasagna",
            ingredientsToUse = "garlic,salt,meat,tomato,white_cheese",
            description = "delicious lasagna",
            cookingInstructions = "To assemble, spread 1 1/2 cups of meat sauce in the bottom of a 9x13-inch baking dish. Arrange 6 noodles lengthwise over meat sauce. Spread with one half of the ricotta cheese mixture. Top with a third of mozzarella cheese slices. Spoon 1 1/2 cups meat sauce over mozzarella, and sprinkle with 1/4 cup Parmesan cheese. Repeat layers, and top with remaining mozzarella and Parmesan cheese. Cover with foil: to prevent sticking, either spray foil with cooking spray, or make sure the foil does not touch the cheese Bake in preheated oven for 25 minutes. Remove foil, and bake an additional 25 minutes. Cool for 15 minutes before serving.",
            prepTime = "45 minutes",
            recipeCategory = "Meat",
            recipeImage = ""
        ),
        Recipes(
            recipeName = "scrambled eggs",
            ingredientsToUse = "egg,salt",
            description = "scrambled eggs for breakfast",
            cookingInstructions = "mix eggs, add salt, cook them at low heat",
            prepTime = "4 minutes",
            recipeCategory = "Keto",
            recipeImage = ""
        )
    )

    fun checkIfDatabaseForIngredientsIsEmpty(database: Database){
        AsyncTask.execute {
            val testListIngredients = database.ingredientsDao().getAllIngredients()

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
            val testListRecipes = database.recipesDao().getAllRecipes()

            if (testListRecipes.isEmpty()) {
                Log.e("ServerEmpty", "so we are adding default Recipes to Database")
                for (recipe in defaultRecipesList) {
                    AsyncTask.execute {
                        database.recipesDao().addRecipe(recipe)
                    }
                    recipesList.add(recipe)
                    recipesListFilterForCategoryClick.add(recipe)
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
            val activityMain = activity
            activityMain.swapFragments(RecipesFragment())
        }else{
            Toast.makeText(context,"There are no recipes in this category: $category",Toast.LENGTH_SHORT).show()
        }
    }
}

