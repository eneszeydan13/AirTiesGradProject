package com.eneszeydan.airtiesgradproject.retrofit

import com.eneszeydan.airtiesgradproject.entity.CRUDResponse
import com.eneszeydan.airtiesgradproject.entity.FoodCartResponse
import com.eneszeydan.airtiesgradproject.entity.FoodResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDaoInterface {

    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFoods(): Call<FoodResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addToCart(
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi") foodImage: String,
        @Field("yemek_fiyat") foodPrice:String,
        @Field("yemek_siparis_adet") orderQuantity: String,
        @Field("kullanici_adi") userName: String
    ):Call<CRUDResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getCart(@Field("kullanici_adi") userName:String): Call<FoodCartResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFromCart(
        @Field("sepet_yemek_id") cartId:String,
        @Field("kullanici_adi") userName: String
    ) : Call<CRUDResponse>

}