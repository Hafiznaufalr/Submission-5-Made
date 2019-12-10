package net.hafiznaufalr.submission4made.ui.fragment.catalogue.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.model.Tv
import net.hafiznaufalr.submission4made.model.TvResponse
import net.hafiznaufalr.submission4made.ui.activity.detailtv.DetailTvActivity
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie.MovieFragment

class TvFragment : Fragment(), TvView {

    lateinit var presenter: TvPresenter
    private var listMovie: ArrayList<Tv> = arrayListOf()
    lateinit var adapter: TvAdapter
    lateinit var layoutManager: LinearLayoutManager

    companion object{
        val EXTRA = "data"
        val ITEM_SAVED = "items"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponents(savedInstanceState)
    }

    private fun initComponents(savedInstanceState: Bundle?) {
        adapter = TvAdapter(context, listMovie){
            val i = Intent(context, DetailTvActivity::class.java)
            i.putExtra(EXTRA, it)
            startActivity(i)
        }
        layoutManager = LinearLayoutManager(context)
        rv_tv.layoutManager = layoutManager
        rv_tv.adapter = adapter
        presenter = TvPresenter(this)
        if(savedInstanceState == null) {
            loadData()
        }else{
            listMovie.clear()
            savedInstanceState.getParcelableArrayList<Tv>(ITEM_SAVED)!!.forEach{
                    tv ->
                listMovie.add(tv)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(MovieFragment.ITEM_SAVED, listMovie)

    }

    private fun showLoading(state: Boolean){
        if (state){
            pb_tv.visibility = View.VISIBLE
        }else{
            pb_tv.visibility = View.GONE

        }
    }

    private fun loadData() {
        showLoading(true)
        presenter.getDataTv()
    }

    override fun onDataCompleteFromApi(data: TvResponse) {
        listMovie.clear()
        listMovie.addAll(data.results)
        adapter.notifyDataSetChanged()
        showLoading(false)
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, getString(R.string.fail), Toast.LENGTH_SHORT).show()
    }



}




