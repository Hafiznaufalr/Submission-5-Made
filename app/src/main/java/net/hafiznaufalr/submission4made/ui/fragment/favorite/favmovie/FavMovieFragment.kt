package net.hafiznaufalr.submission4made.ui.fragment.favorite.favmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.db.movie.MovieHelper
import net.hafiznaufalr.submission4made.model.Movie
import net.hafiznaufalr.submission4made.ui.activity.detail.DetailActivity
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie.MovieFragment

class FavMovieFragment : Fragment() {
    lateinit var movieHelper: MovieHelper
    lateinit var favoriteMovies: ArrayList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieHelper = MovieHelper.getInstance(context!!)
        movieHelper.open()

        favoriteMovies = movieHelper.getAllMovie()
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

}


