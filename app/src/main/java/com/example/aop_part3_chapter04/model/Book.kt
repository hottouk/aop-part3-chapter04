package com.example.aop_part3_chapter04.model

import com.google.gson.annotations.SerializedName

data class Book (
    @SerializedName("isbn") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("price") val price : String,
    @SerializedName("image") val coverImgUri : String,
    @SerializedName("link") val mobileLink : String
)