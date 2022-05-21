package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.entity.Food
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class AddNewViewModel: ViewModel() {
    var foods = MutableLiveData<List<Food>>()
    val frepo = FoodsDaoRepository()

    init {
        loadFoods()
        foods = frepo.getFoods()
    }

    fun loadFoods(){
        frepo.getAllFoods()
    }
}