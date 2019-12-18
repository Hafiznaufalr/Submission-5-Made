package net.hafiznaufalr.cataloguefavoritemovie.helper

import android.database.Cursor
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.ID
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.OVERVIEW
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.POPULARITY
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.POSTERPATH
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.RELEASEDATE
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.TITLE
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract.MovieColumns.Companion.VOTEAVERAGE
import net.hafiznaufalr.cataloguefavoritemovie.model.Movie

class MappingHelper {

    fun mapCursorToArrayList(moviesCursor: Cursor): ArrayList<Movie> {
        val moviesList = ArrayList<Movie>()
        while (moviesCursor.moveToNext()) {
            val id = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(ID))
            val overview = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(OVERVIEW))
            val posterPath = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(POSTERPATH))
            val releaseDate = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(RELEASEDATE))
            val title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(TITLE))
            val vote = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(VOTEAVERAGE))
            val popularity = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(POPULARITY))

            moviesList.add(Movie(id, overview, posterPath, releaseDate, title, vote, popularity))
        }
        return moviesList
    }
}