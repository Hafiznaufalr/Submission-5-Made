package net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie

import net.hafiznaufalr.submission4made.model.MovieResponse

interface MovieView {
    fun onDataCompleteFromApi(data: MovieResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
