package net.hafiznaufalr.submission4made.ui.fragment.catalogue.tv

import net.hafiznaufalr.submission4made.model.TvResponse

interface TvView {
    fun onDataCompleteFromApi(data: TvResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
