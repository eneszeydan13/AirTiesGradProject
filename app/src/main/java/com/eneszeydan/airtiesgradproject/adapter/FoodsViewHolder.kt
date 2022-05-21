package com.eneszeydan.airtiesgradproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneszeydan.airtiesgradproject.databinding.FoodItemCardBinding

class FoodsViewHolder(binding: FoodItemCardBinding): RecyclerView.ViewHolder(binding.root) {

    var binding: FoodItemCardBinding

    init {
        this.binding = binding
    }

}