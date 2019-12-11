package net.hafiznaufalr.submission4made.ui.activity.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.ui.activity.fav.FavoriteActivity
import net.hafiznaufalr.submission4made.ui.fragment.TabAdapter
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.movie.MovieFragment
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.tv.TvFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.elevation = 0.0f
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(MovieFragment(), getString(R.string.movies))
        adapter.addFragment(TvFragment(), getString(R.string.tvshow))
        vp_catalogue.adapter = adapter
        tab_layout_catalogue.setupWithViewPager(vp_catalogue)
        tab_layout_catalogue.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#FFFFFF"))
        tab_layout_catalogue.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"))

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_change_language) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings")
            startActivity(intent)
        }
        else if(item.itemId == R.id.action_to_favorite){
            val i = Intent(this, FavoriteActivity::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }
}