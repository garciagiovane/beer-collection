package com.garciagiovane.beercollection.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.garciagiovane.beercollection.database.dao.BeerDao
import com.garciagiovane.beercollection.database.entities.BeerEntity

@Database(entities = [BeerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}