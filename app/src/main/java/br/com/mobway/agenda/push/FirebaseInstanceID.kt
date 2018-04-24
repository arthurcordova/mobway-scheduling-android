package br.com.mobway.agenda.push

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, token)

//        if (context != null) {
//            val notificationController = NotificationController()
//            notificationController.setToken(context, token)
//        }
    }

    companion object {
        val TAG = "FCM-TOKEN"
    }

}