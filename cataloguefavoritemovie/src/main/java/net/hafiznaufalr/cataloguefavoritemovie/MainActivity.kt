package net.hafiznaufalr.cataloguefavoritemovie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.cataloguefavoritemovie.db.MovieContract
import net.hafiznaufalr.cataloguefavoritemovie.helper.MappingHelper
import net.hafiznaufalr.cataloguefavoritemovie.model.Movie

class MainActivity : AppCompatActivity() {

    lateinit var favoriteMovies: ArrayList<Movie>

    private var handlerThread: HandlerThread? = null
    private var myObserver: DataObserver? = null

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handlerThread = HandlerThread("DataObserver")
        handlerThread?.start()
        val handler = Handler(handlerThread!!.looper)
        myObserver = DataObserver(handler)
        contentResolver?.registerContentObserver(
            MovieContract.MovieColumns().CONTENT_URI,
            true, myObserver as ContentObserver
        )

        val list = contentResolver?.query(
            MovieContract.MovieColumns().CONTENT_URI, null,
            null, null, null
        )

        favoriteMovies = MappingHelper().mapCursorToArrayList(list as Cursor)

        rv_fav_movie.apply {
            adapter = FavAdapter(
                context,
                favoriteMovies
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    class DataObserver(handler: Handler) : ContentObserver(handler)
}