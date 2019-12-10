package net.hafiznaufalr.submission4made.ui.fragment.favorite

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.ui.fragment.TabAdapter
import net.hafiznaufalr.submission4made.ui.fragment.favorite.favmovie.FavMovieFragment
import net.hafiznaufalr.submission4made.ui.fragment.favorite.favtv.FavTvFragment

class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = TabAdapter(fragmentManager!!)
        adapter.addFragment(FavMovieFragment(), getString(R.string.movies))
        adapter.addFragment(FavTvFragment(), getString(R.string.tvshow))
        vp_favorite.adapter = adapter
        tab_layout_favorite.setupWithViewPager(vp_favorite)
        tab_layout_favorite.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#FFFFFF"))
        tab_layout_favorite.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"))
    }


}