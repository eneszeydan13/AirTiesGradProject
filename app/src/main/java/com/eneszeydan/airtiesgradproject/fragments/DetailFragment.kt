package com.eneszeydan.airtiesgradproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.databinding.FragmentDetailBinding
import com.eneszeydan.airtiesgradproject.entity.FoodCart
import com.eneszeydan.airtiesgradproject.viewmodels.DetailViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var orders : List<FoodCart>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.detailFragment = this

        val bundle: DetailFragmentArgs by navArgs()
        val food = bundle.food
        loadImage(food.foodImageName)
        binding.foodObject = food

        val uid: String = Firebase.auth.uid.toString()
        binding.userName = uid


        viewModel.getCart(uid)
        viewModel.orders.observe(viewLifecycleOwner) {
            orders = it
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun loadImage(imageName:String){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${imageName}"
        val options = RequestOptions().placeholder(R.drawable.ic_loading).error(R.drawable.ic_error)
        Glide.with(binding.detailImageView).load(url).apply(options).into(binding.detailImageView)
    }

    fun plusClicked(tv: TextView) {
        if (tv.text.isEmpty()) {
            tv.text = "1"
        } else {
            var num = tv.text.toString().toInt()
            num += 1
            tv.text = "$num"
        }
    }

    fun minusClicked(tv: TextView) {
        if (tv.text.isEmpty()) {
            Toast.makeText(requireContext(),getString(R.string.wrong_amount),Toast.LENGTH_SHORT).show()
        } else if (tv.text.toString() == "1") {
            tv.text = ""
        } else {
            var num = tv.text.toString().toInt()
            num -= 1
            tv.text = "$num"
        }
    }

    fun addToCart(
        foodName: String,
        foodImage: String,
        foodPrice: String,
        orderQuantity: String,
        userName: String
    ) {




        if(orderQuantity.isNotEmpty()){
            var sum = orderQuantity.toInt()

            for (o in orders){
                if(o.foodName == foodName){
                    sum = Integer.parseInt(o.orderQuantity) + Integer.parseInt(orderQuantity)
                    viewModel.deleteFromCart(o.cartId, o.userName)
                    break
                }
            }
            viewModel.addToCart(foodName, foodImage, foodPrice, sum.toString(), userName)
            Toast.makeText(requireContext(),"$foodName sepete eklendi",Toast.LENGTH_SHORT).show()
        }else Toast.makeText(requireContext(),R.string.empty_amount,Toast.LENGTH_SHORT).show()

    }
}