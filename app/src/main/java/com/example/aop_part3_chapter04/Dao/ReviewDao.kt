package com.example.aop_part3_chapter04.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aop_part3_chapter04.model.Review

@Dao
interface ReviewDao {

    @Query("SELECT*FROM Review")
    fun getAll(): List<Review>

    @Query("SELECT * FROM Review WHERE id == :id")
    fun getOneReview(id: Long): Review

    @Insert(onConflict = OnConflictStrategy.REPLACE) //충돌 처리방식 중 하나; 덮어쓰기
    fun saveReview(review: Review)
}