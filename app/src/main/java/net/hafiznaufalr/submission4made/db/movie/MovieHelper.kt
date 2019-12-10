package net.hafiznaufalr.submission4made.db.movie

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.ID
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.OVERVIEW
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.POPULARITY
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.POSTERPATH
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.RELEASEDATE
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.TITLE
import net.hafiznaufalr.submission4made.db.movie.MovieContract.MovieColumns.Companion.VOTEAVERAGE
import net.hafiznaufalr.submission4made.model.Movie

class MovieHelper(context: Context) {
    val DATABASE_TABLE = MovieContract().TABLE_MOVIE
    var databaseHelper = DBMovieHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile private var INSTANCE: MovieHelper? = null

        fun getInstance(context: Context): MovieHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieHelper(context)
            }

    }


    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun getAllMovie(): ArrayList<Movie> {
        val arrayList = ArrayList<Movie>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            null, null
        )
        cursor.moveToFirst()
        var movie: Movie
        if (cursor.count > 0) {
            do {
                movie = Movie(
                    cursor.getString(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POSTERPATH)),
                    cursor.getString(cursor.getColumnIndexOrThrow(RELEASEDATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(VOTEAVERAGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY))
                )

                arrayList.add(movie)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun isMovieFavorited(id: String): Boolean {
        val cursor = database.query(
            DATABASE_TABLE,
            null, "$ID = '$id'", null, null, null,
            null, null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            return true
        }
        return false

    }

    fun insertMovie(movie: Movie): Long {
        val args = ContentValues()
        args.put(ID, movie.id)
        args.put(OVERVIEW, movie.overview)
        args.put(POSTERPATH, movie.posterPath)
        args.put(RELEASEDATE, movie.releaseDate)
        args.put(TITLE, movie.title)
        args.put(VOTEAVERAGE, movie.vote)
        args.put(POPULARITY, movie.popularity)


        return database.insert(DATABASE_TABLE, null, args)
    }

    fun deleteMovie(id: String): Int {
        return database.delete(MovieContract().TABLE_MOVIE, "$ID = '$id'", null)
    }

}
