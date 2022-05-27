package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.entity.FoodCart
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class DetailViewModel: ViewModel() {
    val frepo = FoodsDaoRepository()
    var orders = MutableLiveData<List<FoodCart>>()

    init{
        orders = frepo.getOrders()
    }

    fun addToCart(foodName: String, foodImage: String, foodPrice:String, quantity: String, userName: String){
        frepo.addToCart(foodName, foodImage, foodPrice, quantity, userName)
    }

    fun deleteFromCart(cartId: String, userName: String){
        frepo.deleteFromCart(cartId, userName)
    }

    fun getCart(username: String){
        frepo.getCart(username)
    }
}