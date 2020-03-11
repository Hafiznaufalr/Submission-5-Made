package net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tv.*
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.model.Tv
import net.hafiznaufalr.submissionfinalmade.model.TvResponse
import net.hafiznaufalr.submissionfinalmade.ui.activity.detailtv.DetailTvActivity
import javax.inject.Inject

class TvFragment : DaggerFragment(), TvView {

    @Inject
    lateinit var presenter: TvPresenter

    private var listTv: ArrayList<Tv> = arrayListOf()
    lateinit var adapter: TvAdapter
    lateinit var layoutManager: LinearLayoutManager

    companion object {
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
        adapter = TvAdapter(context, listTv) {
            val i = Intent(context, DetailTvActivity::class.java)
            i.putExtra(EXTRA, it)
            startActivity(i)
        }
        layoutManager = LinearLayoutManager(context)
        rv_tv.layoutManager = layoutManager
        rv_tv.adapter = adapter
        swipe_tv.setOnRefreshListener {
            doRefresh()
        }
        searchTv()
        if (savedInstanceState == null) {
            showLoading(true)
            loadData()
        } else {
            listTv.clear()
            savedInstanceState.getParcelableArrayList<Tv>(ITEM_SAVED)!!.forEach { tv ->
                listTv.add(tv)
            }
        }

    }

    private fun searchTv() {
        search_tv.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    showLoading(true)
                    val searchText = search_tv.text.toString().trim()
                    doSearch(searchText)
                    return true
                }
                return false
            }

        })

        search_tv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(search_tv.text.trim().toString() == ""){
                    listTv.clear()
                    loadData()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun doSearch(searchText: String) {
        if (searchText.isEmpty()) {
            loadData()
        } else {
            listTv.clear()
            loadDataSearch(searchText)
        }
    }

    private fun loadDataSearch(searchText: String) {
        presenter.getDataSearchTv(searchText)
    }

    private fun doRefresh() {
        if (search_tv.text.trim().toString() == "") {
            presenter.getDataTv()
        } else {
            val s = search_tv.text.trim().toString()
            loadDataSearch(s)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ITEM_SAVED, listTv)

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_tv.visibility = View.VISIBLE
        } else {
            pb_tv.visibility = View.GONE

        }
    }

    private fun loadData() {
        presenter.getDataTv()
    }

    override fun onDataCompleteFromApi(data: TvResponse) {
        swipe_tv.isRefreshing = false
        listTv.clear()
        listTv.addAll(data.results)
        adapter.notifyDataSetChanged()
        showLoading(false)
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        Toast.makeText(context, getString(R.string.fail), Toast.LENGTH_SHORT).show()
    }


}




