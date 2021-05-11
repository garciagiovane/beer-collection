package com.garciagiovane.beercollection.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer")
data class BeerEntity(
    @PrimaryKey val uId: String,
    @ColumnInfo val name: String,
    @ColumnInfo val purchaseDate: String?,
    @ColumnInfo val comments: String?
)
