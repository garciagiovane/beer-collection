package com.garciagiovane.beercollection.database

import android.content.Context
import androidx.room.Room

class DatabaseSingleton {
    companion object {
        private var appDatabase: AppDatabase? = null
        fun database(context: Context): AppDatabase {
            if (appDatabase == null)
                appDatabase = Room
                    .databaseBuilder(context, AppDatabase::class.java, "beer-collection")
                    .allowMainThreadQueries()
                    .build()
            return appDatabase as AppDatabase
        }
    }
}