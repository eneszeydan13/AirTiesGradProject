package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.eneszeydan.airtiesgradproject.MainActivity
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.adapter.CartAdapter
import com.eneszeydan.airtiesgradproject.databinding.FragmentHomepageBinding
import com.eneszeydan.airtiesgradproject.viewmodels.HomepageViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomepageFragment : Fragment() {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var viewModel: HomepageViewModel
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false)

        val uid:String = Firebase.auth.uid.toString()

        viewModel.getCart(uid)

        viewModel.orders.observe(viewLifecycleOwner) {
            adapter = CartAdapter(it, viewModel)
            binding.cartAdapter = adapter

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomepageViewModel by viewModels()
        viewModel = tempViewModel
    }

}