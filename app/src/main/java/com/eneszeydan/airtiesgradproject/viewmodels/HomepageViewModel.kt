package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.entity.FoodCart
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class HomepageViewModel: ViewModel() {

    var orders = MutableLiveData<List<FoodCart>>()
    val frepo = FoodsDaoRepository()
    var name = MutableLiveData<String>()

    init {
        orders = frepo.getOrders()
    }

    fun getCart(username: String){
        frepo.getCart(username)
    }

    fun deleteFromCart(cartId:String, username: String){
        frepo.deleteFromCart(cartId, username)
    }



}