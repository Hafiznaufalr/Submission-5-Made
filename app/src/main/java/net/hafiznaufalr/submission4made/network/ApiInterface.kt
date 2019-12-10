package net.hafiznaufalr.submission4made.network

import net.hafiznaufalr.submission4made.model.Movie
import net.hafiznaufalr.submission4made.model.MovieResponse
import net.hafiznaufalr.submission4made.model.Tv
import net.hafiznaufalr.submission4made.model.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface{

    @GET("discover/movie")
    fun getDataMovie(
        @Query("api_key")apiKey:String,
        @Query("language")language:String = "en-US")
    :Call<MovieResponse>

    @GET("discover/tv")
    fun getDataTv(
        @Query("api_key")apiKey:String,
        @Query("language")language:String = "en-US")
    :Call<TvResponse>

}
