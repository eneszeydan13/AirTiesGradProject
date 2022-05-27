package com.eneszeydan.airtiesgradproject.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var name:String? = "", var email:String? = "", var uid:String? = "") {
}