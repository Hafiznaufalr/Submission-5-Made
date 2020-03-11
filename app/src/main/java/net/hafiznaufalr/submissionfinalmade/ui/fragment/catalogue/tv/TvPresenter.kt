package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.hafiznaufalr.submissionfinalmade.BuildConfig.API_KEY
import net.hafiznaufalr.submissionfinalmade.BuildConfig.LANGUAGE
import net.hafiznaufalr.submissionfinalmade.model.TvResponse
import net.hafiznaufalr.submissionfinalmade.di.module.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvPresenter(private val tvView: TvView) {
    private val disposables: CompositeDisposable = CompositeDisposable()
    fun getDataTv() {
        NetworkModule.create()
            .getDataTv(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                tvView.onDataCompleteFromApi(response)
            },{error ->
                tvView.onDataErrorFromApi(error)
            }).addTo(disposables)
    }

    fun getDataSearchTv(query: String) {
        NetworkModule.create()
            .getDataSearchTv(API_KEY, LANGUAGE, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                tvView.onDataCompleteFromApi(response)
            },{error ->
                tvView.onDataErrorFromApi(error)
            }).addTo(disposables)
    }

    fun onDetach(){
        disposables.clear()
    }
}