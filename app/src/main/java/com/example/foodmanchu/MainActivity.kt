package com.example.foodmanchu

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.foodmanchu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),DatabaseInterface {
    companion object {
        private const val DATABASE_NAME = "RDATABASE"
    }

    var database: Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
                applicationContext,
                Database::class.java,
                DATABASE_NAME
        ).fallbackToDestructiveMigration()
                .build()

        Repository.checkIfDatabaseForIngredientsIsEmpty(database!!)

        val categoriesAdapter = CategoriesAdapter(onClick = { category ->
            Toast.makeText(this, "this category test: $category", Toast.LENGTH_LONG).show()
        })
        binding.apply {
            //categoriesRecyclerView.apply {
            //    adapter = categoriesAdapter
            //   layoutManager = LinearLayoutManager(this@MainActivity)
            //  categoriesAdapter.submitList(Repository.categoryList)
            //}


            bottomNavigationView.setOnNavigationItemSelectedListener {
                handeBottomNavigation(it.itemId, binding)
            }
        }

    }

    private fun handeBottomNavigation(menuItemId: Int, binding: ActivityMainBinding): Boolean = when (menuItemId) {

        R.id.menu_recipes -> {
             swapFragments(RecipesFragment())
             true
        }
        R.id.menu_ingredients -> {
             swapFragments(IngredientsFragment())
             true
        }
        R.id.menu_search -> {
            true
        }
        else -> false
    }

    private fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun addIngredient(ingredient: Ingredients) {
        AsyncTask.execute {
            database?.ingredientsDao()?.addIngredient(ingredient)
        }
    }

    override fun deleteIngredient(ingredient: String) {
        AsyncTask.execute {
            database?.ingredientsDao()?.deleteFromIngredients(ingredient)
        }
    }

}
