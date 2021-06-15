package com.example.foodmanchu

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.example.foodmanchu.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSearchBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val filtersAvailable = Repository.filtersList
            val adapterFilters =
                    ArrayAdapter(requireContext(),R.layout.ingredients_listing,filtersAvailable)
            (searchFilterLayout.editText as? AutoCompleteTextView)?.setAdapter(adapterFilters)

            searchButton.setOnClickListener {
                var searchWord = searchEditText.text?.toString()?:""
                var searchFilter = searchFilterLayout.editText?.text?.toString()?:""

                val mainActivity = activity as MainActivity
                mainActivity.searchBy(searchFilter,searchWord)
            }
        }
    }
}