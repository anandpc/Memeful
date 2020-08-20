package io.github.anandpc.memeful.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ImgurService {

    @GET("gallery/top/viral")
    suspend fun fetchViralMemes(): ResponseBody
}