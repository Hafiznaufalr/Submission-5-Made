package net.hafiznaufalr.submissionfinalmade.receiver

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.ui.activity.main.MainActivity
import java.util.*

class DailyReminder : BroadcastReceiver() {
    private fun sendNotification(context: Context, title: String, desc: String, id: Int) {
        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        val i = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context, id, i,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_movie_24dp)
            .setContentTitle(title)
            .setContentText(desc)
            .setContentIntent(pendingIntent)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(uriTone)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "100", "NOTIFICATION_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.YELLOW
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            builder.setChannelId("100")
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(id, builder.build())

    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, DailyReminder::class.java)
        return PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun setAlarm(context: Context) {
        cancelAlarm(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                getPendingIntent(context)
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                getPendingIntent(context)
            )
        }

        Toast.makeText(
            context,
            context.getString(R.string.daily_reminder) + " " + context.getString(R.string.on),
            Toast.LENGTH_SHORT
        ).show()
    }

     fun cancelAlarm(context: Context) {
        val mAlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mAlarmManager.cancel(getPendingIntent(context))

        Toast.makeText(
            context,
            context.getString(R.string.daily_reminder) + " " + context.getString(R.string.off),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            sendNotification(
                context, context.getString(R.string.daily_title),
                context.getString(R.string.daily_desc), 100
            )
        }
    }
}