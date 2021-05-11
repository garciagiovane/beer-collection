package com.garciagiovane.beercollection.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.garciagiovane.beercollection.database.entities.BeerEntity

@Dao
interface BeerDao {
    @Query("SELECT * FROM beer")
    fun findAll(): List<BeerEntity>?

    @Insert
    fun save(beerEntity: BeerEntity)
}