package com.example.foodmanchu

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import com.example.foodmanchu.databinding.FragmentEditRecipeBinding

class EditRecipeDialog(): DialogFragment() {
    companion object{
        fun create(listener:()->Unit):EditRecipeDialog{
            return EditRecipeDialog().apply {
                this.listener = listener
            }
        }
        private const val PICK_IMAGE = 101
    }
    lateinit var newRecipeForDetails:Recipes
    private var imageUri ="".toUri()
    private var listener : () -> Unit = {}
    lateinit var recipe:Recipes
    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentEditRecipeBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = FragmentEditRecipeBinding.inflate(inflater)
        recipe = Repository.recipeToEdit
        mainActivity = activity as MainActivity

        binding.apply {
            editRecipeNameEditText.setText(recipe.recipeName)
            editRecipeCategoryLayout.editText?.setText(recipe.recipeCategory)
            editRecipePrepTimeEditText.setText(recipe.prepTime)
            editRecipeInstructionsEditText.setText(recipe.cookingInstructions)
            editRecipeDescriptionEditText.setText(recipe.description)
            editTextView.text = recipe.ingredientsToUse
            editAddImage.setImageURI(recipe.recipeImage.toUri())


            //val ingredientsAvailable = Repository.IngredientsList
           // val adapterIngredients =
                 //   ArrayAdapter(requireContext(), R.layout.ingredients_listing, ingredientsAvailable)

            //(editRecipeIngredientsLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterIngredients)


            val categoriesAvailable = Repository.categoryList
            val adapterCategories =
                    ArrayAdapter(requireContext(), R.layout.ingredients_listing, categoriesAvailable)
            (editRecipeCategoryLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterCategories)

            editIngredientsButton.setOnClickListener {
                showDialog()
            }
        }

        binding.editAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        return AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Apply"){_,_ ->
                    Repository.recipesList.remove(recipe)
                    Repository.recipesListFilterForCategoryClick.remove(recipe)
                    mainActivity.deleteRecipe(recipe.recipeName)
                    addRecipeToListAndDataBase(binding)
                    Repository.listOfRecipeForDetail.clear()
                    Repository.listOfRecipeForDetail.add(newRecipeForDetails)
                    listener()
                }
                .setNegativeButton("Cancel",null)
                .create()
    }

    private fun showDialog(){
        Repository.listOfSelectedIngredientsForRecipe.clear()
        lateinit var dialog: AlertDialog
        var arrayIngredients:Array<String> = Repository.IngredientsList.map { it.ingredientName }.toTypedArray()
        var mutableListFalse = mutableListOf<Boolean>()

        var recipe = Repository.recipeToEdit
        var listOfIngredientsS = recipe.ingredientsToUse.split(",")
        Log.e("ListOfIngredientsSSS","$listOfIngredientsS")
        for(ingredient in Repository.IngredientsList){
            if(listOfIngredientsS.contains(ingredient.ingredientName) || listOfIngredientsS.contains(ingredient.ingredientName+" ,") ||  listOfIngredientsS.contains(" "+ingredient.ingredientName)){
                mutableListFalse.add(true)
            }else {
                mutableListFalse.add(false)
            }
        }

        var arrayChecked:BooleanArray = mutableListFalse.map { it }.toBooleanArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose your ingredients.")

        builder.setMultiChoiceItems(arrayIngredients, arrayChecked) { dialog, which, isChecked->
            arrayChecked[which] = isChecked

            //val ingredient = arrayIngredients[which]
            // Display the clicked item text
            //Log.e
        }

        builder.setPositiveButton("Add") { _, _ ->
            binding.editTextView.text = "Selected Ingredients \n"
            for (i in arrayIngredients.indices) {
                val checked = arrayChecked?.get(i)
                if (checked!!) {
                    binding.editTextView.text = "${binding.editTextView.text} ${arrayIngredients[i]},"
                    Repository.listOfSelectedIngredientsForRecipe.add(arrayIngredients[i])
                }
            }
        }
        dialog = builder.create()
        dialog.show()
    }

    fun addRecipeToListAndDataBase(binding: FragmentEditRecipeBinding){
        var ingredientsSelectedString = ""
        for(ingredient in Repository.listOfSelectedIngredientsForRecipe){
            ingredientsSelectedString += "$ingredient,"
        }
        ingredientsSelectedString = ingredientsSelectedString.dropLast(1)
        var newRecipe = Recipes(
                recipeName = binding.editRecipeNameEditText.text?.toString()?:"",
                ingredientsToUse = if(ingredientsSelectedString ==""){
                    binding.editTextView.text.toString()
                }else{
                    ingredientsSelectedString
                },
                description = binding.editRecipeDescriptionEditText.text?.toString()?:"",
                cookingInstructions = binding.editRecipeInstructionsEditText.text?.toString()?:"",
                prepTime = binding.editRecipePrepTimeEditText.text?.toString()?:"",
                recipeCategory = binding.editRecipeCategoryLayout.editText?.text?.toString()?:"",
                recipeImage = if(imageUri=="".toUri()){
                    recipe.recipeImage
                }else{
                    imageUri.toString()
                }
        )
        Log.e("WHAT CATEGORY NEWRECIPE","${newRecipe.recipeCategory}")
        Repository.recipesList.add(newRecipe)
        Repository.recipesListFilterForCategoryClick.add(newRecipe)
        var mainActivity = activity as MainActivity
        mainActivity.addRecipe(newRecipe)
        newRecipeForDetails = newRecipe
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){
            intent?.run{
                imageUri = data ?: "".toUri()

                // this is the special bit, here you're grabbing any flags that were previously
                // attached to the intent, then you're using a bitwise `and` and a bitwise `or`
                // (these are low level linux operations in regards to permissions) to get the
                // permissions to hold on to access to the URI forever
                val takeFlags = flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

                // now you need to let Android know you want these permissions forever
                requireActivity().contentResolver.takePersistableUriPermission(imageUri, takeFlags)

                val addImage = dialog?.findViewById<ImageView>(R.id.edit_add_image)
                addImage?.setImageURI(imageUri)
            }


        }
    }
}