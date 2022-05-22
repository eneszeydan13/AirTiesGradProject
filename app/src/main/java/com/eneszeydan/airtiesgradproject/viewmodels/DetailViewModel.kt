package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class DetailViewModel: ViewModel() {
    val frepo = FoodsDaoRepository()

    fun addToCart(foodName: String, foodImage: String, foodPrice:String, quantity: String, userName: String){
        frepo.addToCart(foodName, foodImage, foodPrice, quantity, userName)
    }
}