package com.eneszeydan.airtiesgradproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eneszeydan.airtiesgradproject.databinding.PastOrdersRowBinding
import com.eneszeydan.airtiesgradproject.entity.Order

class PastOrdersAdapter(var pastOrdersList: List<Order>): RecyclerView.Adapter<PastOrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastOrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PastOrdersRowBinding.inflate(layoutInflater, parent, false)
        return PastOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PastOrdersViewHolder, position: Int) {
        val order = pastOrdersList[position]
        holder.binding.order = order
    }

    override fun getItemCount(): Int {
        return pastOrdersList.size
    }
}