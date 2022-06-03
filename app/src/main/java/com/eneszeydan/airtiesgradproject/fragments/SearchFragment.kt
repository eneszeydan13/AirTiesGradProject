package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.adapter.FoodsAdapter
import com.eneszeydan.airtiesgradproject.databinding.FragmentSearchBinding
import com.eneszeydan.airtiesgradproject.viewmodels.SearchViewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: FoodsAdapter
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        viewModel.foods.observe(viewLifecycleOwner) {
            adapter = FoodsAdapter(it)
            binding.adapter = adapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }

            override fun onQueryTextChange(query: String): Boolean {

                if (query.isNotEmpty()) {
                    adapter.foodsList = viewModel.searchFood(query)
                    adapter.notifyDataSetChanged()
                }else{
                    viewModel.loadFoods()
                }
                return true
            }

        })

        return binding.root
    }

    /*
     * I used onPause() because when there's text on SearchView
     * , if we navigate to another fragment and come back, the app crashes,
     * so I set the query to empty string
     */
    override fun onPause() {
        super.onPause()
        binding.searchView.setQuery("", false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SearchViewModel by viewModels()
        viewModel = tempViewModel
    }

}