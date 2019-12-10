package net.hafiznaufalr.submission4made.ui.fragment.catalogue.tv

import net.hafiznaufalr.submission4made.model.TvResponse
import net.hafiznaufalr.submission4made.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvPresenter(private val tvView: TvView) {
    fun getDataTv(){
        RetrofitService.create()
            .getDataTv(RetrofitService.API_KEY)
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
}