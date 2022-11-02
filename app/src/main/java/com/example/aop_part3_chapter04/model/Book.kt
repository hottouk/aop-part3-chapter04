package com.example.aop_part3_chapter04.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book (
    @SerializedName("isbn") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("discount") val price : String,
    @SerializedName("image") val coverImgUri : String,
    @SerializedName("link") val mobileLink : String
):Parcelable