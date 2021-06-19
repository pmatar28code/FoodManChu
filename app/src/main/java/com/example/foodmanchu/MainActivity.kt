package com.example.foodmanchu

import android.content.ContentResolver
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.foodmanchu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),DatabaseInterface {
    companion object {
        private const val DATABASE_NAME = "RDATABASE"
    }

    lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        swapFragments(CategoriesFragment())
        getDefaultImageUri()

        database = Room.databaseBuilder(
                applicationContext,
                Database::class.java,
                DATABASE_NAME
        ).fallbackToDestructiveMigration()
                .build()

        Repository.checkIfDatabaseForIngredientsIsEmpty(database)
        Repository.checkIfDatabaseForRecipesIsEmpty(database)

        binding.apply {
            bottomNavigationView.setOnNavigationItemSelectedListener {
                handleBottomNavigation(it.itemId, binding)
            }
        }
    }

    private fun handleBottomNavigation(
            menuItemId: Int, binding: ActivityMainBinding
    ): Boolean = when (menuItemId) {

        R.id.menu_categories -> {
             Repository.recipesListFilterForCategoryClick.clear()
             Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
             swapFragments(CategoriesFragment())
             true
        }
        R.id.menu_recipes -> {
            Repository.recipesListFilterForCategoryClick.clear()
            Repository.recipesListFilterForCategoryClick = Repository.recipesList.map { it }.toMutableList()
            swapFragments(RecipesFragment())
            true
        }
        R.id.menu_ingredients -> {
             swapFragments(IngredientsFragment())
             true
        }
        R.id.menu_search -> {
            swapFragments(SearchFragment())
            true
        }
        else -> false
    }

     override fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("back")
            .commit()
    }

    override fun searchBy(filter: String, word: String) {
        when(filter){
            "Ingredients" -> {
                Repository.recipesListFilterForCategoryClick.clear()
                AsyncTask.execute {
                    var ingredientsFilterResult =
                    database.recipesDao().findIngredientFilter(word)
                    Repository.recipesListFilterForCategoryClick =
                    ingredientsFilterResult.map { it }.toMutableList()
                    swapFragments(RecipesFragment())
                }
            }
            "PrepTime" -> {
                Repository.recipesListFilterForCategoryClick.clear()
                AsyncTask.execute {
                    var prepTimeFilterResult =
                    database.recipesDao().findPrepTimeFilter(word)
                    Repository.recipesListFilterForCategoryClick =
                    prepTimeFilterResult.map { it }.toMutableList()
                    swapFragments(RecipesFragment())
                }
            }
            "RecipeName" -> {
                Repository.recipesListFilterForCategoryClick.clear()
                AsyncTask.execute {
                    var recipeNameFilterResult =
                    database.recipesDao().findNameFilter(word)
                    Repository.recipesListFilterForCategoryClick =
                    recipeNameFilterResult.map { it }.toMutableList()
                    swapFragments(RecipesFragment())
                }
            }
            "Description" -> {
                Repository.recipesListFilterForCategoryClick.clear()
                AsyncTask.execute {
                    var descriptionFilterResult =
                    database.recipesDao().findDescriptionFilter(word)
                    Repository.recipesListFilterForCategoryClick =
                    descriptionFilterResult.map { it }.toMutableList()
                    swapFragments(RecipesFragment())
                }
            }
            "Category" -> {
                Repository.recipesListFilterForCategoryClick.clear()
                AsyncTask.execute {
                    var categoryFilterResult =
                    database.recipesDao().findCategoryFilter(word)
                    Repository.recipesListFilterForCategoryClick =
                    categoryFilterResult.map { it }.toMutableList()
                    swapFragments(RecipesFragment())
                }
            }
        }
    }

    private fun getDefaultImageUri(): Uri {
        val defaultImageId = R.drawable.ic_default_image
        val uriDefaultImage = Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(defaultImageId))
                .appendPath(resources.getResourceTypeName(defaultImageId))
                .appendPath(resources.getResourceEntryName(defaultImageId))
                .build()
        Repository.repoDefaultImageUri = uriDefaultImage
        return uriDefaultImage
    }

    override fun addIngredient(ingredient: Ingredients) {
        AsyncTask.execute {
            database.ingredientsDao().addIngredient(ingredient)
        }
    }

    override fun deleteIngredient(ingredient: String) {
        AsyncTask.execute {
            database.ingredientsDao().deleteFromIngredients(ingredient)
        }
    }

    override fun addRecipe(recipe: Recipes) {
        AsyncTask.execute {
            database.recipesDao().addRecipe(recipe)
        }
    }

    override fun deleteRecipe(recipe: String) {
        AsyncTask.execute {
            database.recipesDao().deleteFromRecipes(recipe)
        }
    }
}
