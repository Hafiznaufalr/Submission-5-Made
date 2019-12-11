package net.hafiznaufalr.submissionfinalmade.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieHelper
import net.hafiznaufalr.submissionfinalmade.model.Movie
import net.hafiznaufalr.submissionfinalmade.network.RetrofitService
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieFragment

class DetailActivity : AppCompatActivity() {
    lateinit var movieHelper: MovieHelper
    lateinit var movie: Movie
    var isFavorite = false
    var isLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.detail_movie)
        movieHelper = MovieHelper.getInstance(applicationContext)
        movieHelper.open()
        showData()

    }



    private fun showData() {
        showLoading(true)
        val dataDetail = intent.getParcelableExtra<Movie>(MovieFragment.EXTRA)
        tv_detail_title.text = dataDetail!!.title
        tv_detail_vote.text = dataDetail.vote
        tv_detail_release_date.text = dataDetail.releaseDate
        tv_detail_description.text = dataDetail.overview
        tv_detail_popularity.text = dataDetail.popularity
        Glide.with(this).load(RetrofitService.BASE_IMAGE_URL + dataDetail.posterPath).into(iv_poster)
        showLoading(false)
        movie = dataDetail
        isLoaded = true
        isFavorite = isFavorited()
        invalidateOptionsMenu()
    }



    private fun showLoading(state: Boolean) {
        if (state) {
            pb_detail.visibility = View.VISIBLE
        } else {
            pb_detail.visibility = View.GONE

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_add_to_favorite -> {
                if (isFavorite) {
                    item.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_24dp)
                    val result = movieHelper.deleteMovie(movie.id)
                    if (result > 0) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.remove_item_from_favorite_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFavorite = isFavorited()

                    }

                } else {
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_24dp)
                    val result = movieHelper.insertMovie(movie)

                    if (result > 0) {
                        Toast.makeText(
                            applicationContext, getString(R.string.add_item_to_favorite_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFavorite = isFavorited()

                    }
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isFavorited(): Boolean {
        if (movieHelper.isMovieFavorited(movie.id)) {
            return true
        }

        return false
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (isLoaded) {
            menu?.getItem(0)?.isVisible = true

        }
        if (isFavorite) {
            menu?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_24dp)
        } else {
            menu?.getItem(0)?.icon = ContextCompat
                .getDrawable(this, R.drawable.ic_favorite_border_24dp)

        }
        return super.onPrepareOptionsMenu(menu)
    }

}
