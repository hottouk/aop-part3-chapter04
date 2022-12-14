package com.example.aop_part3_chapter04.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "review") val review : String?
)