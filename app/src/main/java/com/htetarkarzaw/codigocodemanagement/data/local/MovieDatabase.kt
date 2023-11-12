package com.htetarkarzaw.codigocodemanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.htetarkarzaw.codigocodemanagement.data.local.dao.MovieDao
import com.htetarkarzaw.codigocodemanagement.data.local.entities.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}