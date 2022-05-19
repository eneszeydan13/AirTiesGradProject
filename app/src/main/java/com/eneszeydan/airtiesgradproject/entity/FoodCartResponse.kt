package com.eneszeydan.airtiesgradproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodCartResponse(
    @SerializedName("success") @Expose var success: Int,
    @SerializedName("sepet_yemekler") @Expose var cart: List<FoodCart>
) {
}