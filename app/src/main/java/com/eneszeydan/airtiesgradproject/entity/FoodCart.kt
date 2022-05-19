package com.eneszeydan.airtiesgradproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodCart(
    @SerializedName("sepet_yemek_id") @Expose var cartId: String,
    @SerializedName("yemek_adi") @Expose var foodName: String,
    @SerializedName("yemek_resim_adi") @Expose var foodImage: String,
    @SerializedName("yemek_fiyat") @Expose var foodPrice: String,
    @SerializedName("yemek_siparis_adet") @Expose var orderQuantity: String,
    @SerializedName("kullanici_adi") @Expose var userName: String
) {
}