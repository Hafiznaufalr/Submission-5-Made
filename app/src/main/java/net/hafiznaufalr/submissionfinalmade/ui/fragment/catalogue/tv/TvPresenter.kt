package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv

import net.hafiznaufalr.submissionfinalmade.BuildConfig.API_KEY
import net.hafiznaufalr.submissionfinalmade.BuildConfig.LANGUAGE
import net.hafiznaufalr.submissionfinalmade.model.TvResponse
import net.hafiznaufalr.submissionfinalmade.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvPresenter(private val tvView: TvView) {
    fun getDataTv() {
        RetrofitService.create()
            .getDataTv(API_KEY)
            .enqueue(object : Callback<TvResponse> {
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    tvView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<TvResponse>,
                    response: Response<TvResponse>
                ) {
                    if (response.isSuccessful) {
                        tvView.onDataCompleteFromApi(response.body() as TvResponse)
                    }
                }

            })
    }

    fun getDataSearchTv(query: String) {
        RetrofitService.create()
            .getDataSearchTv(API_KEY, LANGUAGE, query)
            .enqueue(object : Callback<TvResponse> {
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    tvView.onDataErrorFromApi(t)
                }

                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    if (response.isSuccessful) {
                        tvView.onDataCompleteFromApi(response.body() as TvResponse)
                    }
                }

            })
    }
}