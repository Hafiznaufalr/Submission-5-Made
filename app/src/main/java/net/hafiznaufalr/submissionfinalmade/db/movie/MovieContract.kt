package net.hafiznaufalr.submissionfinalmade.db.movie

import android.provider.BaseColumns

class MovieContract {
    var TABLE_MOVIE = "movie"

    internal class MovieColumns : BaseColumns {
        companion object {
            var ID = "id"
            var OVERVIEW = "overview"
            var POSTERPATH = "posterPath"
            var RELEASEDATE = "releaseDate"
            var TITLE = "title"
            var VOTEAVERAGE = "voteAverage"
            var POPULARITY = "popularity"
        }
    }

}