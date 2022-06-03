package com.eneszeydan.airtiesgradproject.viewmodels

import androidx.lifecycle.ViewModel
import com.eneszeydan.airtiesgradproject.repo.FoodsDaoRepository

class SettingsViewModel: ViewModel() {
    val frepo = FoodsDaoRepository()

    fun signOut(){
        frepo.signOut()
    }
}