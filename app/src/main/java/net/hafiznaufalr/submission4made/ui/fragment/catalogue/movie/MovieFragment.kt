package net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.model.Movie
import net.hafiznaufalr.submission4made.model.MovieResponse
import net.hafiznaufalr.submission4made.ui.activity.detail.DetailActivity

class MovieFragment : Fragment(), MovieView {

    private lateinit var presenter: MoviePresenter
    private var listMovie: ArrayList<Movie> = arrayListOf()
    private lateinit var adapter: MovieAdapter
    private lateinit var layoutManager: LinearLayoutManager

    companion object {
        val EXTRA = "data"
        val ITEM_SAVED = "items"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponents(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ITEM_SAVED, listMovie)

    }

    private fun initComponents(savedInstanceState: Bundle?) {
        adapter = MovieAdapter(context, listMovie) {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra(EXTRA, it)
            startActivity(i)
        }
        layoutManager = LinearLayoutManager(context)
        rv_movie.layoutManager = layoutManager
        rv_movie.adapter = adapter
        presenter = MoviePresenter(this)
        if(savedInstanceState == null) {
            loadData()
        }else{
            listMovie.clear()
            savedInstanceState.getParcelableArrayList<Movie>(ITEM_SAVED)!!.forEach{
                movie ->
                listMovie.add(movie)
            }
        }
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            pb_movie.visibility = View.VISIBLE
        } else {
            pb_movie.visibility = View.GONE

        }
    }

    private fun loadData() {
        showLoading(true)
        presenter.getDataMovie()
    }

    override fun onDataCompleteFromApi(data: MovieResponse) {

        listMovie.clear()
        listMovie.addAll(data.results)
        adapter.notifyDataSetChanged()
        showLoading(false)
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, getString(R.string.fail), Toast.LENGTH_SHORT).show()
    }


}



