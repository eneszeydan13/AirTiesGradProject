package com.eneszeydan.airtiesgradproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.databinding.CartRowBinding
import com.eneszeydan.airtiesgradproject.entity.FoodCart
import com.eneszeydan.airtiesgradproject.viewmodels.HomepageViewModel

class CartAdapter(var cartList:List<FoodCart>, var viewModel:HomepageViewModel): RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CartRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.cart_row, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = cartList[position]
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${cart.foodImage}"
        val options = RequestOptions().placeholder(R.drawable.ic_loading).error(R.drawable.ic_error)
        holder.binding.apply {
            cartObject = cart

            deleteImage.setOnClickListener {
                viewModel.deleteFromCart(cart.cartId, cart.userName)
            }

            Glide.with(cartFoodImage).load(imageUrl).apply(options).into(cartFoodImage)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}