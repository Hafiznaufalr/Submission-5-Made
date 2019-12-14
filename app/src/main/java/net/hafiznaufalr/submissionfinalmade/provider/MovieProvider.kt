package net.hafiznaufalr.submissionfinalmade.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieContract
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieContract.MovieColumns.Companion.ID
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieHelper
import net.hafiznaufalr.submissionfinalmade.ui.fragment.favorite.favmovie.FavMovieProviderFragment

class MovieProvider : ContentProvider() {

    private val MOVIE = 1
    private val MOVIE_ID = 2
    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    lateinit var movieHelper: MovieHelper


    override fun onCreate(): Boolean {
        uriMatching()
        movieHelper = MovieHelper.getInstance(context!!)

        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        movieHelper.open()
        val added: Long = when (sUriMatcher.match(uri)) {
            MOVIE -> movieHelper.insertProvider(values as ContentValues)
            else -> 0
        }
        context?.contentResolver?.notifyChange(MovieContract.MovieColumns().CONTENT_URI,
            FavMovieProviderFragment.DataObserver(Handler(), context!!))
        return Uri.parse(MovieContract.MovieColumns().CONTENT_URI.toString()+ "/" + added)
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        movieHelper.open()
        return when (sUriMatcher.match(uri)) {
            MOVIE -> movieHelper.queryProvider()
            MOVIE_ID -> movieHelper.queryByIdProvider(uri.lastPathSegment as String)
            else -> null
        }
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        movieHelper.open()
        val updated: Int = when (sUriMatcher.match(uri)) {
            MOVIE_ID -> movieHelper.updateProvider(uri.lastPathSegment as String, values as ContentValues)
            else -> 0
        }
        context!!.contentResolver.notifyChange(MovieContract.MovieColumns().CONTENT_URI,
            FavMovieProviderFragment.DataObserver(Handler(), context!!))
        return updated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        movieHelper.open()
        val deleted: Int = when (sUriMatcher.match(uri)) {
            MOVIE_ID -> movieHelper.deleteProvider(uri.lastPathSegment!!)
            else -> 0
        }
        context!!.contentResolver.notifyChange(MovieContract.MovieColumns().CONTENT_URI,
            FavMovieProviderFragment.DataObserver(Handler(), context!!))
        return deleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    private fun uriMatching(){
        sUriMatcher.addURI(MovieContract().AUTHORITY, MovieContract().TABLE_MOVIE, MOVIE)
        sUriMatcher.addURI(MovieContract().AUTHORITY, MovieContract().TABLE_MOVIE + "/$ID", MOVIE_ID)

    }
}