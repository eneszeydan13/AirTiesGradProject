package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.adapter.CartAdapter
import com.eneszeydan.airtiesgradproject.databinding.FragmentHomepageBinding
import com.eneszeydan.airtiesgradproject.entity.FoodCart
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
        binding.homepageFragment = this
        binding.cartEmptyAnimation.visibility = View.GONE
        val uid:String = Firebase.auth.uid.toString()

        viewModel.getCart(uid)

        viewModel.orders.observe(viewLifecycleOwner) {
            adapter = CartAdapter(it, viewModel)
            binding.cartAdapter = adapter
            if(it.isEmpty()){
                binding.extFab.visibility = View.GONE
                binding.cartEmptyAnimation.visibility = View.VISIBLE
                binding.cartEmptyTextView.visibility = View.VISIBLE
            }else{
                binding.extFab.visibility = View.VISIBLE
                binding.cartEmptyAnimation.visibility = View.GONE
                binding.cartEmptyTextView.visibility = View.GONE
                calculatePrice(it)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomepageViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun fabClicked(){
            val nav = HomepageFragmentDirections.toConfirm(binding.orderPrice as Int)
            Navigation.findNavController(binding.extFab).navigate(nav)
    }

    private fun calculatePrice(orderList: List<FoodCart>){
        var sum = 0
        for (c in orderList){
            sum+=c.foodPrice.toInt() * c.orderQuantity.toInt()
        }
        binding.orderPrice = sum
    }

}