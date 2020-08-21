package io.github.anandpc.memeful.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Data.TABLE_NAME)
data class Data(
    val title: String?,
    val points: Int?,
    val images: List<Images>?
) {
    @PrimaryKey(autoGenerate = true)
    var localId: Int = 0

    companion object {
        const val TABLE_NAME = "memes_table"
    }
}