package com.mrmobo.splitwisedemo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        private var INSTANCE: AppDatabase?=null

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "expense_database")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}