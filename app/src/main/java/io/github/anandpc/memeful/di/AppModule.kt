package io.github.anandpc.memeful.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.anandpc.memeful.data.remote.ImgurService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = Interceptor { chain ->
            val newRequest: Request =
                chain.request().newBuilder()
                    .addHeader("Authorization", "Client-ID 287b64e566368ab")
                    .addHeader("Content-Type", "application/json")
                    .build()
            chain.proceed(newRequest)
        }

        val builder = OkHttpClient.Builder()
        builder.interceptors().add(interceptor)
        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideImgurService(retrofit: Retrofit): ImgurService =
        retrofit.create(ImgurService::class.java)

    /*@Provides
    fun provideMemesDao(db: MemesDatabase): MemesDao = db.getMemesDao()*/

}