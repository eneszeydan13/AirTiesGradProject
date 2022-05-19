package com.eneszeydan.airtiesgradproject.retrofit

import retrofit2.create

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodsInterface(): FoodsDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(FoodsDaoInterface::class.java)
        }
    }
}