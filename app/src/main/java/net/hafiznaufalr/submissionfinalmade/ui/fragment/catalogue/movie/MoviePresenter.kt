package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie

import net.hafiznaufalr.submissionfinalmade.BuildConfig.API_KEY
import net.hafiznaufalr.submissionfinalmade.BuildConfig.LANGUAGE
import net.hafiznaufalr.submissionfinalmade.model.MovieResponse
import net.hafiznaufalr.submissionfinalmade.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val movieView: MovieView) {
    fun getDataMovie() {
        RetrofitService.create()
            .getDataMovie(API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    movieView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        movieView.onDataCompleteFromApi(response.body() as MovieResponse)
                    }
                }

            })
    }

    fun getDataSearchMovie(query: String) {
        RetrofitService.create()
            .getDataSearchMovie(API_KEY, LANGUAGE, query)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    movieView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        movieView.onDataCompleteFromApi(response.body() as MovieResponse)
                    }
                }

            })
    }
}