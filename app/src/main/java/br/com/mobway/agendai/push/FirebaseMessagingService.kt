package br.com.mobway.agendai.push

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        MessageNotification
                .notify(applicationContext, remoteMessage!!.notification!!.body!!, 1)

    }

    companion object {
        val TAG = "FCM-MESSAGE"
    }
}