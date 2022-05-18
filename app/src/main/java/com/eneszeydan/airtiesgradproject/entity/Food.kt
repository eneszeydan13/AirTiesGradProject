package com.eneszeydan.airtiesgradproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Food(
    @SerializedName("yemek_adi") @Expose var foodName: String,
    @SerializedName("yemek_fiyat") @Expose var foodPrice: String,
    @SerializedName("yemek_id") @Expose var foodId: String,
    @SerializedName("yemek_resim_adi") @Expose var foodImageName: String
):Serializable{}