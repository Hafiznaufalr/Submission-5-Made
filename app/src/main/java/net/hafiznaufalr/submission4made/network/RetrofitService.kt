package net.hafiznaufalr.submission4made.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    var BASE_URL: String = "https://api.themoviedb.org/3/"
    var API_KEY: String = "ebe8b65ae09a2a517ffd680266f90891"
    var BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/original"


    fun getInterceptor(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okhttp = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        return okhttp
    }

    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

}