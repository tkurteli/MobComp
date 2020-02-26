package com.example.mobcomplab1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabOpened = false

        fab.setOnClickListener {
            if (!fabOpened) {
                fabOpened = true
                floatingMap.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                floatingTimer.animate().translationY(-resources.getDimension(R.dimen.standard_116))
            } else {
                fabOpened = false
                floatingMap.animate().translationY(0f)
                floatingTimer.animate().translationY(0f)
            }
        }
        floatingTimer.setOnClickListener {
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }
        floatingMap.setOnClickListener {
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        doAsync {
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "reminders")
                .build()
            val reminders = db.reminderDao().getReminder()
            db.close()

            uiThread {

                if (reminders.isNotEmpty()) {
                    val adapter = ReminderAdaptor(applicationContext, reminders)
                    list.adapter = adapter

                } else {
                    toast("No reminders yet")
                }

            }

        }
    }

    companion object{
        val CHANNEL_ID="REMINDER_CHANNEL_ID"
        var NotificationID=1567
        fun showNotification(context: Context, message:String){
            var notificationBuilder=NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_24px)
                .setContentTitle("Reminder").setContentText(message)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            var notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                val channel=NotificationChannel(CHANNEL_ID,context.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT).apply{description = context.getString(R.string.app_name)}
                notificationManager.createNotificationChannel(channel)
            }
            val notification=NotificationID + Random(NotificationID).nextInt(1, 30)
            notificationManager.notify(notification, notificationBuilder.build())
        }
    }
}