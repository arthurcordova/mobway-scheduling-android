package br.com.mobway.agendai

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import kotlinx.android.synthetic.main.fragment_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sAppName = SpannableString(getString(R.string.app_name))


        val RED = ForegroundColorSpan(Color.rgb(250, 73, 110))
        sAppName.setSpan(RED, 0, 5,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        labelAppName.text = sAppName
    }

}
