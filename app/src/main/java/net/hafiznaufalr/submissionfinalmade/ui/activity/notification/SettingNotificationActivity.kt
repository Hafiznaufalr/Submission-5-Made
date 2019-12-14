package net.hafiznaufalr.submissionfinalmade.ui.activity.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_setting_notification.*
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.receiver.DailyReminder
import net.hafiznaufalr.submissionfinalmade.receiver.ReleaseReminder
import kotlin.properties.Delegates

class SettingNotificationActivity : AppCompatActivity() {

    private var dailyReminder = DailyReminder()
    private var releaseReminder = ReleaseReminder()
    private var isDaily by Delegates.notNull<Boolean>()
    private var isRelease by Delegates.notNull<Boolean>()

    companion object{
        var DAILY = "daily"
        var RELEASE = "release"
        var SAVE = "save"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_notification)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.notif)
        val sharedPreferences = getSharedPreferences(SAVE, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (savedInstanceState != null) {
            isDaily = savedInstanceState.getBoolean(DAILY)
            isRelease = savedInstanceState.getBoolean(RELEASE)
        } else {
            isDaily = sharedPreferences.getBoolean(DAILY, false)
            isRelease = sharedPreferences.getBoolean(RELEASE, false)
        }
        switch_daily_reminder.isChecked = isDaily
        switch_release_reminder.isChecked = isRelease

        switch_daily_reminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dailyReminder.setAlarm(applicationContext)
            } else {
                dailyReminder.cancelAlarm(applicationContext)
            }
            isDaily = isChecked
            editor.putBoolean(DAILY, isDaily)
            editor.apply()
        }

        switch_release_reminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                releaseReminder.setAlarm(applicationContext)
            } else {
                releaseReminder.cancelAlarm(applicationContext)
            }
            isRelease = isChecked
            editor.putBoolean(RELEASE, isRelease)
            editor.apply()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(DAILY,isDaily)
        outState.putBoolean(RELEASE,isRelease)
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
