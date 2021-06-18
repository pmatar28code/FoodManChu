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
import com.example.foodmanchu.databinding.FragmentAddRecipeBinding

class AddRecipeDialog:DialogFragment() {
    companion object{
        fun create(listener:()->Unit):AddRecipeDialog{
            return AddRecipeDialog().apply {
                this.listener = listener
            }
        }
        private const val PICK_IMAGE = 101
    }
    var imageUri = "".toUri()
    private var listener : () -> Unit = {}
    lateinit var binding: FragmentAddRecipeBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val inflater = LayoutInflater.from(requireContext())
    binding = FragmentAddRecipeBinding.inflate(inflater)

    //val ingredientsAvailable = Repository.IngredientsList
    //val adapterIngredients =
           // ArrayAdapter(requireContext(),R.layout.ingredients_listing, ingredientsAvailable)

    //(binding.addRecipeIngredientsLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterIngredients)

        binding.addImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

    val categoriesAvailable = Repository.categoryList
    val adapterCategories =
            ArrayAdapter(requireContext(),R.layout.ingredients_listing,categoriesAvailable)
    (binding.addRecipeCategoryLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterCategories)

    binding.button3.setOnClickListener {
        showDialog()
    }

    return AlertDialog.Builder(requireContext())
        .setView(binding.root)
        .setPositiveButton("Add"){_,_ ->
            addRecipeToListAndDataBase(binding)
            listener()
        }
        .setNegativeButton("Cancel",null)
        .create()
    }

    private fun showDialog(){
        Repository.listOfSelectedIngredientsForRecipe.clear()
        lateinit var dialog:AlertDialog
        var arrayIngredients:Array<String> = Repository.IngredientsList.map { it.ingredientName }.toTypedArray()
        var mutableListFalse = mutableListOf<Boolean>()

        for(ingredient in Repository.IngredientsList){
            mutableListFalse.add(false)
        }

        var arrayChecked:BooleanArray = mutableListFalse.map { false }.toBooleanArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose your ingredients.")

        builder.setMultiChoiceItems(arrayIngredients, arrayChecked) { dialog, which, isChecked->
            arrayChecked[which] = isChecked

            //val ingredient = arrayIngredients[which]
            // Display the clicked item text
            //Log.e
        }

        builder.setPositiveButton("Add") { _, _ ->
            binding.textView.text = "Selected Ingredients \n"
            for (i in arrayIngredients.indices) {
                val checked = arrayChecked?.get(i)
                if (checked!!) {
                    binding.textView.text = "${binding.textView.text} ${arrayIngredients[i]},"
                    Repository.listOfSelectedIngredientsForRecipe.add(arrayIngredients[i])
                }
            }
        }

        builder.setNegativeButton("Cancel",null)

        dialog = builder.create()
        dialog.show()
    }

    fun addRecipeToListAndDataBase(binding:FragmentAddRecipeBinding){
        var ingredientsSelectedString = ""
        for(ingredient in Repository.listOfSelectedIngredientsForRecipe){
            ingredientsSelectedString += "$ingredient,"
        }
        ingredientsSelectedString = ingredientsSelectedString.dropLast(1)
        var newRecipe = Recipes(
            recipeName = binding.addRecipeNameEditText.text?.toString()?:"",
            ingredientsToUse = ingredientsSelectedString,
            description = binding.addRecipeDescriptionEditText.text?.toString()?:"",
            cookingInstructions = binding.addRecipeInstructionsEditText.text?.toString()?:"",
            prepTime = binding.addRecipePrepTimeEditText.text?.toString()?:"",
            recipeCategory = binding.addRecipeCategoryLayout.editText?.text?.toString()?:"",
            recipeImage = imageUri.toString()
        )
        Log.e("WHAT CATEGORY NEWRECIPE","${newRecipe.recipeCategory}")
        Repository.recipesList.add(newRecipe)
        Repository.recipesListFilterForCategoryClick.add(newRecipe)
        var mainActivity = activity as MainActivity
        mainActivity.addRecipe(newRecipe)
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

                val addImage = dialog?.findViewById<ImageView>(R.id.add_image)
                addImage?.setImageURI(imageUri)
            }


        }
    }
}
