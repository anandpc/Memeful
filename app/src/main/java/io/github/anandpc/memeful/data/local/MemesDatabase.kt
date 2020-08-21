package io.github.anandpc.memeful.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.anandpc.memeful.data.local.dao.MemesDao
import io.github.anandpc.memeful.data.model.Converters

import io.github.anandpc.memeful.data.model.Data
import io.github.anandpc.memeful.data.model.Images

@Database(entities = [Data::class, Images::class], version = 1)
@TypeConverters(Converters::class)
abstract class MemesDatabase : RoomDatabase() {

    abstract fun getMemesDao(): MemesDao

    companion object {
        const val DB_NAME = "memes_db"
    }
}
