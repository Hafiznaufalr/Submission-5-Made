package net.hafiznaufalr.submissionfinalmade.ui.fragment.favorite.favmovie

import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieContract
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieHelper
import net.hafiznaufalr.submissionfinalmade.model.Movie
import net.hafiznaufalr.submissionfinalmade.ui.activity.detail.DetailActivity
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieAdapter
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieFragment
import net.hafiznaufalr.submissionfinalmade.util.MappingHelper

class FavMovieProviderFragment : Fragment() {

    lateinit var movieHelper: MovieHelper
    lateinit var favoriteMovies: ArrayList<Movie>

    private var handlerThread: HandlerThread? = null
    private var myObserver: DataObserver? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_fav_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieHelper = MovieHelper.getInstance(context!!)
        movieHelper.open()

        handlerThread = HandlerThread("DataObserver")
        handlerThread?.start()
        val handler = Handler(handlerThread!!.looper)
        myObserver = DataObserver(handler, context as Context)
        context?.contentResolver?.registerContentObserver(MovieContract.MovieColumns().CONTENT_URI,
            true, myObserver as ContentObserver
        )

        val list = context?.contentResolver?.query(MovieContract.MovieColumns().CONTENT_URI, null,
            null, null, null)

        favoriteMovies = MappingHelper().mapCursorToArrayList(list as Cursor)

        rv_fav_movie.apply {
            adapter = FavMovieAdapter(
                context,
                favoriteMovies) {
                val i = Intent(context, DetailActivity::class.java)
                i.putExtra(MovieFragment.EXTRA, it)
                startActivity(i)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteMovies.clear()
        favoriteMovies.addAll(movieHelper.getAllMovie())
        favoriteMovies.reverse()
        rv_fav_movie.adapter?.notifyDataSetChanged()
    }

    class DataObserver(handler: Handler, internal val context: Context) : ContentObserver(handler)
}
