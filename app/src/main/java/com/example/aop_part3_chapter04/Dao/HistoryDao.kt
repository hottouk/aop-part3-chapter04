package com.example.aop_part3_chapter04.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aop_part3_chapter04.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HISTORY")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM History WHERE keyword == :keyword")
    fun delete(keyword: String) {
    }
}