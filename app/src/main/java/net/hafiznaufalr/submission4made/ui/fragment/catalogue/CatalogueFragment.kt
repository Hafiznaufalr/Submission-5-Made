package net.hafiznaufalr.submission4made.ui.fragment.catalogue

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_catalogue.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.ui.fragment.TabAdapter
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie.MovieFragment
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.tv.TvFragment

class CatalogueFragment : Fragment() {

    companion object {
        fun newInstance() = CatalogueFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = TabAdapter(fragmentManager!!)
        adapter.addFragment(MovieFragment(), getString(R.string.movies))
        adapter.addFragment(TvFragment(), getString(R.string.tvshow))
        vp_catalogue.adapter = adapter
        tab_layout_catalogue.setupWithViewPager(vp_catalogue)
        tab_layout_catalogue.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#FFFFFF"))
        tab_layout_catalogue.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"))
    }


}