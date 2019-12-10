package net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie

import net.hafiznaufalr.submission4made.model.MovieResponse
import net.hafiznaufalr.submission4made.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val movieView: MovieView) {
    fun getDataMovie(){
        RetrofitService.create()
            .getDataMovie(RetrofitService.API_KEY)
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