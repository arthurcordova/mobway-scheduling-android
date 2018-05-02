package br.com.mobway.agendai.preference

import android.content.Context



class UserPreferences(val context: Context) {

    companion object {
        val PREFERENCE_NAME : String = "user_preferences"

        val AUTH_TOKEN : String = "user_auth_token"
        val FCM_TOKEN : String = "user_fcm_token"

    }

    private val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = preference.edit()!!

    fun setValue(key : String, value: Any?) {
        when (value) {
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Int -> editor.putInt(key, value)
        }
        editor.apply()
    }

    fun getString(key: String): String? {
       return preference.getString(key, null)
    }

    fun getBoolean(key: String): Boolean? {
        return preference.getBoolean(key, false)
    }

    fun getFloat(key: String): Float? {
        return preference.getFloat(key, 0f)
    }

    fun getInt(key: String): Int? {
        return preference.getInt(key, 0)
    }

}