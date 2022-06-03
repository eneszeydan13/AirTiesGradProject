package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.adapter.FoodsAdapter
import com.eneszeydan.airtiesgradproject.databinding.FragmentAddNewBinding
import com.eneszeydan.airtiesgradproject.viewmodels.AddNewViewModel

class AddNewFragment : Fragment() {
    private lateinit var binding: FragmentAddNewBinding
    private lateinit var viewModel: AddNewViewModel
    private lateinit var adapter: FoodsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new, container, false)

        viewModel.foods.observe(viewLifecycleOwner) {
            adapter = FoodsAdapter(it)
            binding.adapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AddNewViewModel by viewModels()
        viewModel = tempViewModel
    }
}