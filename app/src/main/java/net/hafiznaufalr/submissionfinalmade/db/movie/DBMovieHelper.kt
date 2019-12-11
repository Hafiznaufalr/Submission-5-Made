package net.hafiznaufalr.submissionfinalmade.db.movie

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBMovieHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        var DATABASE_NAME = "movie"
        private val DATABASE_VERSION = 1
    }
    private val SQL_CREATE_TABLE_MOVIE = String.format(
        "CREATE TABLE  ${MovieContract().TABLE_MOVIE}"
                + " (${MovieContract.MovieColumns.ID} TEXT PRIMARY KEY NOT NULL," +
                " ${MovieContract.MovieColumns.OVERVIEW} TEXT NOT NULL," +
                " ${MovieContract.MovieColumns.POSTERPATH} TEXT NOT NULL," +
                " ${MovieContract.MovieColumns.RELEASEDATE} TEXT NOT NULL," +
                " ${MovieContract.MovieColumns.TITLE} TEXT NOT NULL," +
                " ${MovieContract.MovieColumns.VOTEAVERAGE} TEXT NOT NULL," +
                " ${MovieContract.MovieColumns.POPULARITY} TEXT NOT NULL)"

        )


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + MovieContract().TABLE_MOVIE)

    }
}