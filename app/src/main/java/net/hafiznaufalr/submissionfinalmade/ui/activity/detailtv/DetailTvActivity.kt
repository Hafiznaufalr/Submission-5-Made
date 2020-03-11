package net.hafiznaufalr.submissionfinalmade.ui.activity.detailtv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_tv.*
import net.hafiznaufalr.submissionfinalmade.BuildConfig.BASE_IMAGE_URL
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.db.tv.TvHelper
import net.hafiznaufalr.submissionfinalmade.model.Tv
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv.TvFragment


class DetailTvActivity : AppCompatActivity() {
    lateinit var tvHelper: TvHelper
    lateinit var tv: Tv
    var isFavorite = false
    var isLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.detail_movie)
        tvHelper = TvHelper.getInstance(applicationContext)
        tvHelper.open()
        showData()
    }



    private fun showData() {
        showLoading(true)
        val dataDetail = intent.getParcelableExtra<Tv>(TvFragment.EXTRA)
        tv_detail_title.text = dataDetail!!.name
        tv_detail_vote.text = dataDetail.vote
        tv_detail_release_date.text = dataDetail.firstAirDate
        tv_detail_description.text = dataDetail.overview
        tv_detail_popularity.text = dataDetail.popularity
        Glide.with(this).load(BASE_IMAGE_URL + dataDetail.posterPath).into(iv_poster)
        showLoading(false)
        tv = dataDetail
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
                    val result = tvHelper.deleteTv(tv.id)
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
                    val result = tvHelper.insertTv(tv)

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
        if (tvHelper.isTvFavorited(tv.id)) {
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
