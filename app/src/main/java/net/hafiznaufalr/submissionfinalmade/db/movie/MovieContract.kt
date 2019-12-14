package net.hafiznaufalr.submissionfinalmade.db.movie

import android.net.Uri
import android.provider.BaseColumns

class MovieContract {
    var TABLE_MOVIE = "movie"

    val AUTHORITY = "net.hafiznaufalr.submissionfinalmade"
    val SCHEME = "content"


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

        val CONTENT_URI: Uri = Uri.Builder().scheme(MovieContract().SCHEME)
            .authority(MovieContract().AUTHORITY)
            .appendPath(MovieContract().TABLE_MOVIE)
            .build()
    }

}