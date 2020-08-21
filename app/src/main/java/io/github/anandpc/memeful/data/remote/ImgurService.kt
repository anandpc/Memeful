package io.github.anandpc.memeful.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurService {

    // gallery/{{section}}/{{sort}}/{{window}}/{{page}}

    @GET("gallery/{section}/{sort}/{window}/{page}")
    suspend fun fetchViralMemes(
        @Path("section") section: String = "top",
        @Path("sort") sort: String = "viral",
        @Path("window") window: String = "day",
        @Path("page") page: Int = 0
    ): ResponseBody
}