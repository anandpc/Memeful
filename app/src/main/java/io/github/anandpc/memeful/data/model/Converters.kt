package io.github.anandpc.memeful.data.model

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromImageList(images: List<Images>?): String? {
        return images?.get(0)?.link
    }

    @TypeConverter
    fun fromImage(imageLink: String?): List<Images?> {
        val list: MutableList<Images> = mutableListOf()
        list.add(0, Images(imageLink))
        return list
    }

}