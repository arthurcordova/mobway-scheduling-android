package br.com.mobway.agendai

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient

import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_intro.view.*
import com.google.android.gms.common.ConnectionResult
import com.google.firebase.auth.*
import com.squareup.picasso.Picasso


class IntroActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {


    val RC_SIGN_IN: Int = 9001
//    var googleApiClient: GoogleApiClient? = null
//    var firebaseAuth : FirebaseAuth? = null
//    var firebaseAuthListner : FirebaseAuth.AuthStateListener? = null

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var googleApiClient: GoogleApiClient? = null
    var firebaseAuth : FirebaseAuth? = null
    var firebaseAuthListner : FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        fab.setOnClickListener { view ->
            //            Crashlytics.getInstance().crash()
//            Crashlytics.log("Teste crash")
//            signIn(password = "hardcore87NY", email = "arthur.stapassoli@gmail.com")
            signIn()
        }


        val gsio = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build()



        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gsio)
                .build()

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuthListner = FirebaseAuth.AuthStateListener {
            if (it.currentUser != null) {
                Picasso.get().load(it.currentUser?.photoUrl).into(imageAccount)
            }
        }



    }

    override fun onResume() {
        super.onResume()
        firebaseAuthListner?.let { firebaseAuth?.addAuthStateListener(it) }
    }


    fun signIn() {
        val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(intent, RC_SIGN_IN)
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

    fun firebaseSignInGoogle(googleSignInAccount: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        firebaseAuth?.signInWithCredential(credentials)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        updateUI(user = it.result.user, isSuccessful = true)
                    } else {
                        updateUI(user = null)
                    }
                }

    }

//    fun firebaseSignInEmail(email: String, pwd:String) {
//        val credentials = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
//        val firebaseAuth = FirebaseAuth.getInstance()
//        firebaseAuth.signInWithCredential(credentials)
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        updateUI(user = it.result.user, isSuccessful = true)
//                    } else {
//                        updateUI(user = null)
//                    }
//                }
//
//    }

    fun updateUI(user: FirebaseUser?, isSuccessful: Boolean = false) {
        if (isSuccessful) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_intro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_intro, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))

            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
