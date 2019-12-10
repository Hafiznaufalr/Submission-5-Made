package net.hafiznaufalr.submission4made.ui.fragment.favorite.favtv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_fav_tv.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.db.tv.TvHelper
import net.hafiznaufalr.submission4made.model.Tv
import net.hafiznaufalr.submission4made.ui.activity.detailtv.DetailTvActivity
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.tv.TvFragment

class FavTvFragment : Fragment() {
    lateinit var tvHelper: TvHelper
    lateinit var favoriteTv: ArrayList<Tv>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvHelper = TvHelper.getInstance(context!!)
        tvHelper.open()

        favoriteTv = tvHelper.getAllTv()
        rv_fav_tv.apply {
            adapter = FavTvAdapter(
                context,
                favoriteTv) {
                val i = Intent(context, DetailTvActivity::class.java)
                i.putExtra(TvFragment.EXTRA, it)
                startActivity(i)
            }
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteTv.clear()
        favoriteTv.addAll(tvHelper.getAllTv())
        favoriteTv.reverse()
        rv_fav_tv.adapter?.notifyDataSetChanged()
    }



}