package com.eneszeydan.airtiesgradproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneszeydan.airtiesgradproject.databinding.CartRowBinding

class CartViewHolder(binding: CartRowBinding): RecyclerView.ViewHolder(binding.root) {
    var binding: CartRowBinding
    init {
        this.binding = binding
    }
}