package com.eneszeydan.airtiesgradproject.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodsInterface(): FoodsDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(FoodsDaoInterface::class.java)
        }
    }
}