package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv

import net.hafiznaufalr.submissionfinalmade.model.TvResponse

interface TvView {
    fun onDataCompleteFromApi(data: TvResponse)
    fun onDataErrorFromApi(throwable: Throwable)
}
