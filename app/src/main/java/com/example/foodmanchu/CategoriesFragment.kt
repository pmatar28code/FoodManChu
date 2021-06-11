package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmanchu.databinding.CategoriesFragmentBinding

class CategoriesFragment: Fragment(R.layout.categories_fragment) {

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CategoriesFragmentBinding.bind(view)

        var mainActivity = activity as MainActivity

        categoriesAdapter = CategoriesAdapter(onClick = {category ->
            Repository.filterRecipesByCategoryForCategoryClick(requireContext(),category,mainActivity)
        })

        binding.apply {
            categoriesRecyclerView.apply{
                adapter = categoriesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                categoriesAdapter.submitList(Repository.categoryList)
            }
        }
    }
}