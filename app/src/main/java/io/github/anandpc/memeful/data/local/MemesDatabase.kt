package io.github.anandpc.memeful.data.local
/*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.anandpc.memeful.data.local.dao.MemesDao
import io.github.anandpc.memeful.data.model.Data

@Database(entities = [Data::class], version = 1)
abstract class MemesDatabase : RoomDatabase() {

    abstract fun getMemesDao(): MemesDao

    companion object {
        const val DB_NAME = "memes_database"

        @Volatile
        private var INSTANCE: MemesDatabase? = null

        fun getInstance(context: Context): MemesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemesDatabase::class.java,
                    DB_NAME
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }
}*/
