package com.eneszeydan.airtiesgradproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("success") @Expose var success: Int,
    @SerializedName("yemekler") @Expose var foods: List<Food>
)