package net.hafiznaufalr.submission4made.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.ui.fragment.catalogue.CatalogueFragment
import net.hafiznaufalr.submission4made.ui.fragment.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {
    private  var savedInstanceState:Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = CatalogueFragment.newInstance()
        addFragment(fragment)
        supportActionBar!!.elevation = 0.0f

        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState
        }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.catalogue -> {
                val fragment = CatalogueFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
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
        return super.onOptionsItemSelected(item)
    }
}
