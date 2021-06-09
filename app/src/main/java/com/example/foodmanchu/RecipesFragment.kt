package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodmanchu.databinding.RecipesFragmentBinding

class RecipesFragment: Fragment(R.layout.recipes_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RecipesFragmentBinding.bind(view)

    }

}