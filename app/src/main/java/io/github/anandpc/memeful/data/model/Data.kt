package io.github.anandpc.memeful.data.model

data class Data(
    val title: String?,
    val points: Int?,
    val images: List<Images>?
) {
    companion object {
        const val TABLE_NAME = "memes_table"
    }
}