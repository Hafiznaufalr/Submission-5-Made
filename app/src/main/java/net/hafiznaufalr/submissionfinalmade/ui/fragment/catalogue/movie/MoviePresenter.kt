package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.hafiznaufalr.submissionfinalmade.BuildConfig.API_KEY
import net.hafiznaufalr.submissionfinalmade.BuildConfig.LANGUAGE
import net.hafiznaufalr.submissionfinalmade.model.MovieResponse
import net.hafiznaufalr.submissionfinalmade.di.module.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val movieView: MovieView) {
    private val disposables: CompositeDisposable = CompositeDisposable()
    fun getDataMovie() {
        NetworkModule.create()
            .getDataMovie(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                movieView.onDataCompleteFromApi(response)
            },{error ->
                movieView.onDataErrorFromApi(error)
            }).addTo(disposables)
    }

    fun getDataSearchMovie(query: String) {
        NetworkModule.create()
            .getDataSearchMovie(API_KEY, LANGUAGE, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                movieView.onDataCompleteFromApi(response)
            },{error ->
                movieView.onDataErrorFromApi(error)
            }).addTo(disposables)
    }

    fun onDetach(){
        disposables.clear()
    }
}