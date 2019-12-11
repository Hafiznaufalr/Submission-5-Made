package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie

import net.hafiznaufalr.submissionfinalmade.model.MovieResponse

interface MovieView {
    fun onDataCompleteFromApi(data: MovieResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
