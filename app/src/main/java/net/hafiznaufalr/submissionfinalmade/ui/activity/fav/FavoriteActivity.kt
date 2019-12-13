package net.hafiznaufalr.submissionfinalmade.ui.activity.fav

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.ui.TabAdapter
import net.hafiznaufalr.submissionfinalmade.ui.fragment.favorite.favmovie.FavMovieFragment
import net.hafiznaufalr.submissionfinalmade.ui.fragment.favorite.favtv.FavTvFragment

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        supportActionBar!!.elevation = 0.0f
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.fav)
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(FavMovieFragment(), getString(R.string.movies))
        adapter.addFragment(FavTvFragment(), getString(R.string.tvshow))
        vp_favorite.adapter = adapter
        tab_layout_favorite.setupWithViewPager(vp_favorite)
        tab_layout_favorite.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#FFFFFF"))
        tab_layout_favorite.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"))


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}
