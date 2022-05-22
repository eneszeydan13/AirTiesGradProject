package com.eneszeydan.airtiesgradproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eneszeydan.airtiesgradproject.R
import com.eneszeydan.airtiesgradproject.databinding.FoodItemCardBinding
import com.eneszeydan.airtiesgradproject.entity.Food
import com.eneszeydan.airtiesgradproject.fragments.AddNewFragmentDirections

class FoodsAdapter(var foodsList: List<Food>) : RecyclerView.Adapter<FoodsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FoodItemCardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.food_item_card, parent, false)
        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {

        val food = foodsList[position]
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.foodImageName}"
        val options = RequestOptions().placeholder(R.drawable.ic_loading).error(R.drawable.ic_error)
        holder.binding.apply {
            foodObject = food

            Glide.with(this.root).load(imageUrl).apply(options).into(imageView)

            rowCard.setOnClickListener {
                val nav = AddNewFragmentDirections.toDetail(food)
                Navigation.findNavController(it).navigate(nav)
            }
        }

    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}