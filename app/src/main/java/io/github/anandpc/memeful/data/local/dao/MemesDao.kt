package io.github.anandpc.memeful.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.anandpc.memeful.data.model.Data


@Dao
interface MemesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemes(memesList: List<Data>?)

    @Query("DELETE FROM ${Data.TABLE_NAME}")
    fun deleteAllMemes()

    @Query("SELECT * FROM ${Data.TABLE_NAME}")
    fun getAllMemes(): List<Data>?
}
