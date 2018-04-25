package br.com.mobway.agendai

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private val RC_SIGN_IN: Int = 9001
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mFirebaseAuth: FirebaseAuth? = null
    private var mFirebaseAuthListner: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitle()
        startFirebaseAuth()

        buttonGoogle.setOnClickListener(this)
        buttonAlreadyAccount.setOnClickListener(this)
        buttonSignInOneTap.setOnClickListener(this)

        show(buttonFacebook)
        show(buttonGoogle)
    }

    private fun startFirebaseAuth() {
        val gsio = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gsio)
                .build()

        mFirebaseAuth = FirebaseAuth.getInstance()

        mFirebaseAuthListner = FirebaseAuth.AuthStateListener {
            if (it.currentUser != null) {
                Picasso.get().load(it.currentUser?.photoUrl).into(imageAccount)
            }
        }
    }

    private fun show(target: View) {
        target.visibility = View.VISIBLE
    }

    private fun hide(target: View) {
        target.visibility = View.GONE
    }

    private fun screenBehavior(isOnTap: Boolean) {
        if (isOnTap) {
            hide(containerSignInWithEmail)
            show(buttonAlreadyAccount)
            hide(buttonSignInOneTap)
            show(buttonFacebook)
            show(buttonGoogle)
        } else {
            hide(buttonFacebook)
            hide(buttonGoogle)
            hide(buttonAlreadyAccount)
            show(buttonSignInOneTap)
            show(containerSignInWithEmail)
        }
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuthListner?.let { mFirebaseAuth?.addAuthStateListener(it) }
    }

    fun signIn() {
        val intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(intent, RC_SIGN_IN)
    }

    fun firebaseSignInGoogle(googleSignInAccount: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        mFirebaseAuth?.signInWithCredential(credentials)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        updateUI(user = it.result.user, isSuccessful = true)
                    } else {
                        updateUI(user = null)
                    }
                }

    }

    fun updateUI(user: FirebaseUser?, isSuccessful: Boolean = false) {
        if (isSuccessful) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount

                Picasso.get().load(account?.photoUrl).into(imageAccount)

                firebaseSignInGoogle(account!!)
            } else {

            }
        }
    }

    private fun setTitle() {
        val sAppName = SpannableString(getString(R.string.app_name))
        val RED = ForegroundColorSpan(Color.rgb(250, 73, 110))
        sAppName.setSpan(RED, 0, 5,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        labelAppName.text = sAppName
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonGoogle -> {
                signIn()
            }
            R.id.buttonAlreadyAccount -> {
                screenBehavior(false)
            }
            R.id.buttonSignInOneTap -> {
                screenBehavior(true)
            }

        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

}

