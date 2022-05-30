package com.eneszeydan.airtiesgradproject.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


class AppPreferences(var context: Context) {
    private val Context.ds : DataStore<Preferences> by preferencesDataStore("info")

    companion object {
        val NAME_KEY = stringPreferencesKey("NAME")
    }

    suspend fun saveName(name: String){
        context.ds.edit{
            it[NAME_KEY] = name
        }
    }

    suspend fun getName():String{
        val p = context.ds.data.first()
        return p[NAME_KEY] ?: "no_name"
    }
}