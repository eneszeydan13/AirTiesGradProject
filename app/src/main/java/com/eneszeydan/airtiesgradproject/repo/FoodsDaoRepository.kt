package com.eneszeydan.airtiesgradproject.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.eneszeydan.airtiesgradproject.entity.*
import com.eneszeydan.airtiesgradproject.retrofit.ApiUtils
import com.eneszeydan.airtiesgradproject.retrofit.FoodsDaoInterface
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsDaoRepository {
    private lateinit var ref: DatabaseReference
    var foodsList: MutableLiveData<List<Food>>
    var orderList: MutableLiveData<List<FoodCart>>
    var name:MutableLiveData<String>
    var fdao : FoodsDaoInterface

    init {
        foodsList = MutableLiveData()
        orderList = MutableLiveData()
        name = MutableLiveData()
        fdao = ApiUtils.getFoodsInterface()
    }

    fun getFoods(): MutableLiveData<List<Food>>{
        return foodsList
    }

    fun getOrders(): MutableLiveData<List<FoodCart>>{
        return orderList
    }

    fun getAllFoods(){
        fdao.getAllFoods().enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                val list = response.body()?.foods
                foodsList.value = list
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.e("Getting foods", t.localizedMessage)
            }

        })
    }

    fun addToCart(foodName: String,
                  foodImage: String,
                  foodPrice:String,
                  orderQuantity: String,
                  userName: String){
        val orders = orderList.value

            Log.e("Orders", orders.toString())

        fdao.addToCart(foodName, foodImage, foodPrice, orderQuantity, userName).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {

            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {}

        })
    }

    fun getCart(userName:String){
        fdao.getCart(userName).enqueue(object : Callback<FoodCartResponse>{
            override fun onResponse(
                call: Call<FoodCartResponse>,
                response: Response<FoodCartResponse>
            ) {
                if (response.body() != null){
                    val list = response.body()?.cart
                    orderList.value = list
                }
            }

            override fun onFailure(call: Call<FoodCartResponse>, t: Throwable) {
                Log.w("Getting Cart", t.localizedMessage)
                orderList.value = emptyList()
            }

        })
    }

    fun deleteFromCart(cartId:String, userName: String){
        fdao.deleteFromCart(cartId, userName).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                getCart(userName)
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                Log.w("Deletion", t.message.toString())
            }

        })
    }

    fun getUsername(uid: String): MutableLiveData<String> {

        val db = FirebaseDatabase.getInstance()
        ref = db.getReference("users/${uid}")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nameList = ArrayList<User>()
                for (d in snapshot.children){
                    val user = d.getValue(User::class.java)

                    if (user != null){
                        user.name = d.child("name").value.toString()
                        nameList.add(user)
                    }
                }
                name.value = nameList[0].name
            }

            override fun onCancelled(error: DatabaseError) {}

        })
        name.value?.let { Log.i("Name", it) }
        return name
    }

}