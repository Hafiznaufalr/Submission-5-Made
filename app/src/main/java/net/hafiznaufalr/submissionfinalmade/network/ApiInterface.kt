package net.hafiznaufalr.submissionfinalmade.network

import net.hafiznaufalr.submissionfinalmade.model.MovieResponse
import net.hafiznaufalr.submissionfinalmade.model.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("discover/movie")
    fun getDataMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    )
            : Call<MovieResponse>

    @GET("discover/tv")
    fun getDataTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    )
            : Call<TvResponse>

    @GET("search/movie")
    fun getDataSearchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    )
            : Call<MovieResponse>

    @GET("search/tv")
    fun getDataSearchTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    )
            : Call<TvResponse>
}
