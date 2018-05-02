package br.com.mobway.agendai.push

import android.util.Log
import br.com.mobway.agendai.preference.UserPreferences
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, token)
        UserPreferences(applicationContext).setValue(UserPreferences.FCM_TOKEN, token)
    }

    companion object {
        val TAG = "FCM-TOKEN"
    }

}