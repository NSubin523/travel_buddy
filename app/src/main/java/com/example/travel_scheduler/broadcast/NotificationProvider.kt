package com.example.travel_scheduler.broadcast

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.travel_scheduler.R

class NotificationProvider(private val context: Context, private val content: String,
                        private val notificationId: Int) {

    fun notifyUser() {
        val builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle("Travel Buddy")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val PROGRESS_MAX = 100
        val PROGRESS_CURRENT = 0
        NotificationManagerCompat.from(context).apply {
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            notify(notificationId, builder.build())
            builder.setContentText("Download complete")
                .setProgress(0, 0, false)
            notify(notificationId, builder.build())
        }
    }
}