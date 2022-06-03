package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.entity.FoodCart
import com.eneszeydan.airtiesgradproject.entity.Order
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class ConfirmOrderViewModel: ViewModel() {

    private val frepo = FoodsDaoRepository()
    var orderList = MutableLiveData<List<FoodCart>>()

    init {
        orderList = frepo.getOrders()
    }

    fun saveOrderToFirebase(order: Order){
        frepo.saveOrderToFirebase(order)
    }

    fun getCart(username: String){
        frepo.getCart(username)
    }

    fun deleteFromCart(cartId:String, userName: String){
        frepo.deleteFromCart(cartId, userName)
    }

}