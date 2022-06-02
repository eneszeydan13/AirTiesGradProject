package com.eneszeydan.airtiesgradproject.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Order(var username:String? = "", var amount:Int? = 0, var address:String? = "", var telNo:String? = "", var dateTime:String? = "")