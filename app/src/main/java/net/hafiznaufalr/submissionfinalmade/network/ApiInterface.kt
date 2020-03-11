package net.hafiznaufalr.submissionfinalmade.network

import io.reactivex.Single
import net.hafiznaufalr.submissionfinalmade.BuildConfig
import net.hafiznaufalr.submissionfinalmade.model.Movie
import net.hafiznaufalr.submissionfinalmade.model.MovieResponse
import net.hafiznaufalr.submissionfinalmade.model.TvResponse
import net.hafiznaufalr.submissionfinalmade.ui.activity.main.MainActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("discover/movie")
    fun getDataMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Single<MovieResponse>

    @GET("discover/tv")
    fun getDataTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Single<TvResponse>

    @GET("search/movie")
    fun getDataSearchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Single<MovieResponse>

    @GET("search/tv")
    fun getDataSearchTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Single<TvResponse>

    @GET("discover/movie")
    fun getReleaseMovie(
        @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") releaseDateGte: String,
        @Query("primary_release_date.lte") releaseDateLte: String
    ): Single<MovieResponse>
}
