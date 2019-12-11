package net.hafiznaufalr.submissionfinalmade.db.tv

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.FIRSTAIRDATE
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.ID
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.NAME
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.OVERVIEW
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.POPULARITY
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.POSTERPATH
import net.hafiznaufalr.submissionfinalmade.db.tv.TvContract.MovieColumns.Companion.VOTEAVERAGE
import net.hafiznaufalr.submissionfinalmade.model.Tv

class TvHelper(context: Context) {
    val DATABASE_TABLE = TvContract().TABLE_TV
    var databaseHelper = DBTvHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile private var INSTANCE: TvHelper? = null

        fun getInstance(context: Context): TvHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TvHelper(context)
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

    fun getAllTv(): ArrayList<Tv> {
        val arrayList = ArrayList<Tv>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            null, null
        )
        cursor.moveToFirst()
        var tv: Tv
        if (cursor.count > 0) {
            do {
                tv = Tv(
                    cursor.getString(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POSTERPATH)),
                    cursor.getString(cursor.getColumnIndexOrThrow(FIRSTAIRDATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(VOTEAVERAGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY))
                )

                arrayList.add(tv)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    @SuppressLint("Recycle")
    fun isTvFavorited(id: String): Boolean {
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

    fun insertTv(tv: Tv): Long {
        val args = ContentValues()
        args.put(ID, tv.id)
        args.put(OVERVIEW, tv.overview)
        args.put(POSTERPATH, tv.posterPath)
        args.put(FIRSTAIRDATE, tv.firstAirDate)
        args.put(NAME, tv.name)
        args.put(VOTEAVERAGE, tv.vote)
        args.put(POPULARITY, tv.popularity)


        return database.insert(DATABASE_TABLE, null, args)
    }

    fun deleteTv(id: String): Int {
        return database.delete(TvContract().TABLE_TV, "$ID = '$id'", null)
    }

}
