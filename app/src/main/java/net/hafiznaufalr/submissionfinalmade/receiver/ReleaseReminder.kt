package net.hafiznaufalr.submissionfinalmade.receiver

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import net.hafiznaufalr.submissionfinalmade.BuildConfig
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.model.Movie
import net.hafiznaufalr.submissionfinalmade.model.MovieResponse
import net.hafiznaufalr.submissionfinalmade.di.module.NetworkModule
import net.hafiznaufalr.submissionfinalmade.ui.activity.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*






class ReleaseReminder: BroadcastReceiver() {

    private var notifId = 101
    private var listData: MutableList<Movie> = mutableListOf()

    private fun sendNotification(context: Context, title: String, desc: String, id: Int, movie: List<Movie>) {
        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        val inboxStyle = NotificationCompat.InboxStyle()
        for (i in movie){
            inboxStyle.addLine(i.title)
        }

        val uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_movie_24dp)
            .setContentTitle(title)
            .setContentText(desc)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(uriTone)
            .setStyle(inboxStyle)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "101",
                "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.YELLOW
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            builder.setChannelId("101")
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(id, builder.build())
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val i = Intent(context, ReleaseReminder::class.java)
        return PendingIntent.getBroadcast(context, 100, i, PendingIntent.FLAG_CANCEL_CURRENT)
    }

    fun setAlarm(context: Context) {
        val delay = 0
        cancelAlarm(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReleaseReminder::class.java)

        intent.putExtra("id", notifId)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            100, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis + delay,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis + delay, pendingIntent
            )
        }
        notifId += 1
        Toast.makeText(context, context.getString(R.string.release_reminder)+" "+context.getString(R.string.on), Toast.LENGTH_SHORT).show()
    }

     fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(getPendingIntent(context))
        Toast.makeText(context, context.getString(R.string.release_reminder)+" "+context.getString(R.string.off), Toast.LENGTH_SHORT).show()
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        getDataMovieRelease(context)

    }
    private fun getDataMovieRelease(context: Context?){

        NetworkModule.create().getReleaseMovie(BuildConfig.API_KEY, MainActivity.TODAY, MainActivity.TODAY)
            .enqueue(object : Callback<MovieResponse>{
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val data = response.body()!!.results
                    listData.addAll(data)

                    val title = listData.size.toString() + " " + context!!.getString(R.string.released)
                    val desc = context.getString(R.string.daily_desc)

                    sendNotification(context, title, desc, 100, data)
                }

            })
    }
}