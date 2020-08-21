package io.github.anandpc.memeful.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Images.TABLE_NAME)
data class Images(
    val link: String?
) {
    @PrimaryKey(autoGenerate = true)
    var localId: Int = 0

    companion object {
        const val TABLE_NAME = "images_table"
    }
}