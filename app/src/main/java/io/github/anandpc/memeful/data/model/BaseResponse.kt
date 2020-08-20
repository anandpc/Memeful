package io.github.anandpc.memeful.data.model

data class BaseResponse(
    val data: List<Data>?,
    val success: Boolean?,
    val status: Int?
)