package net.hafiznaufalr.submissionfinalmade.db.tv

import android.provider.BaseColumns

class TvContract {
    var TABLE_TV = "tv"

    internal class MovieColumns : BaseColumns {
        companion object {
            var ID = "id"
            var OVERVIEW = "overview"
            var POSTERPATH = "posterPath"
            var FIRSTAIRDATE = "firstAirDate"
            var NAME = "name"
            var VOTEAVERAGE = "voteAverage"
            var POPULARITY = "popularity"
        }
    }

}