package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.adapter.PastOrdersAdapter
import com.eneszeydan.airtiesgradproject.databinding.FragmentHistoryBinding
import com.eneszeydan.airtiesgradproject.viewmodels.HistoryViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HistoryFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var viewModel : HistoryViewModel
    private lateinit var adapter : PastOrdersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        val uid:String = Firebase.auth.uid.toString()
        viewModel.loadPastOrders(uid)
        viewModel.pastOrders.observe(viewLifecycleOwner) {
            adapter = PastOrdersAdapter(it)
            binding.adapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HistoryViewModel by viewModels()
        viewModel = tempViewModel
    }

}