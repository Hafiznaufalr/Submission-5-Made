package net.hafiznaufalr.submissionfinalmade.ui.activity.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.ui.activity.fav.FavoriteActivity
import net.hafiznaufalr.submissionfinalmade.ui.TabAdapter
import net.hafiznaufalr.submissionfinalmade.ui.activity.notification.SettingNotificationActivity
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieFragment
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv.TvFragment
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    companion object{
        var TODAY = "today"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.elevation = 0.0f
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        TODAY= dateFormat.format(date).toString()
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
        when {
            item!!.itemId == R.id.action_change_language -> {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings")
                startActivity(intent)
            }
            item.itemId == R.id.action_change_notification -> {
                val i = Intent(this, SettingNotificationActivity::class.java)
                startActivity(i)
            }
            item.itemId == R.id.action_to_favorite -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}