package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.entity.Order
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class HistoryViewModel : ViewModel() {
    val frepo = FoodsDaoRepository()
    var pastOrders = MutableLiveData<List<Order>>()

    init {
        pastOrders = frepo.loadPastOrders()
    }

    fun loadPastOrders(userName: String) {
        frepo.getPastOrders(userName)
    }
}