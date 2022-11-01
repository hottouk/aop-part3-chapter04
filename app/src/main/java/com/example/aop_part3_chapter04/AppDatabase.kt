package com.example.aop_part3_chapter04

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aop_part3_chapter04.Dao.HistoryDao
import com.example.aop_part3_chapter04.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}