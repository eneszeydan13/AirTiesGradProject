package com.eneszeydan.airtiesgradproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneszeydan.airtiesgradproject.databinding.PastOrdersRowBinding

class PastOrdersViewHolder(binding: PastOrdersRowBinding): RecyclerView.ViewHolder(binding.root) {
    var binding : PastOrdersRowBinding

    init {
        this.binding = binding
    }
}