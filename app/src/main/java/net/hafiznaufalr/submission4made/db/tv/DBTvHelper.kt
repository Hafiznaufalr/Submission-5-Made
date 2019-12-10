package net.hafiznaufalr.submission4made.db.tv

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBTvHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        var DATABASE_NAME = "tv"
        private val DATABASE_VERSION = 1
    }
    private val SQL_CREATE_TABLE_TV = String.format(
        "CREATE TABLE  ${TvContract().TABLE_TV}"
                + " (${TvContract.MovieColumns.ID} TEXT PRIMARY KEY NOT NULL," +
                " ${TvContract.MovieColumns.OVERVIEW} TEXT NOT NULL," +
                " ${TvContract.MovieColumns.POSTERPATH} TEXT NOT NULL," +
                " ${TvContract.MovieColumns.FIRSTAIRDATE} TEXT NOT NULL," +
                " ${TvContract.MovieColumns.NAME} TEXT NOT NULL," +
                " ${TvContract.MovieColumns.VOTEAVERAGE} TEXT NOT NULL," +
                " ${TvContract.MovieColumns.POPULARITY} TEXT NOT NULL)"

    )


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_TV)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TvContract().TABLE_TV)

    }
}