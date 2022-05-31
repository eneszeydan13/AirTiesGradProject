package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class LogInViewModel: ViewModel() {

    var name = MutableLiveData<String>()
    val frepo = FoodsDaoRepository()


}