package br.com.mobway.agendai.push

import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import br.com.mobway.agendai.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val notificationBuilder = NotificationCompat.Builder(this)
        notificationBuilder.setContentTitle("Pele Medical Center")
        notificationBuilder.setContentText(remoteMessage!!.notification!!.body)
        notificationBuilder.setSmallIcon(R.drawable.ic_android)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setVibrate(longArrayOf(500, 500, 500))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {
        val TAG = "FCM-MESSAGE"
    }
}