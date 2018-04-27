package br.com.mobway.agendai

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v7.content.res.AppCompatResources
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_intro_one.view.*
import android.view.WindowManager

class IntroActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter
        tabs.setupWithViewPager(container)
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        buttonStartNow.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
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
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val index = arguments?.get(ARG_SECTION_NUMBER)

            val rootView = inflater.inflate(R.layout.fragment_intro_one, container, false)
            when (index) {
                1 -> {
                    rootView.imageBg.setImageDrawable(context?.let { AppCompatResources.getDrawable(it, R.drawable.bg_intro_one) })
                    rootView.labelTitle.text = getText(R.string.label_title_intro_one)
                    rootView.labelSubtitle.text = getText(R.string.label_subtitle_intro_one)
                    rootView.imageCenter.setImageDrawable(context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_scheduling) })

                }
                2 -> {
                    rootView.imageBg.setImageDrawable(context?.let { AppCompatResources.getDrawable(it, R.drawable.bg_intro_two) })
                    rootView.labelTitle.text = getText(R.string.label_title_intro_two)
                    rootView.labelSubtitle.text = getText(R.string.label_subtitle_intro_two)
                    rootView.imageCenter.setImageDrawable(context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_notifications) })

                }
                3 -> {
                    rootView.imageBg.setImageDrawable(context?.let { AppCompatResources.getDrawable(it, R.drawable.bg_intro_three) })
                    rootView.labelTitle.text = getText(R.string.label_title_intro_three)
                    rootView.labelSubtitle.text = getText(R.string.label_subtitle_intro_three)
                    rootView.imageCenter.setImageDrawable(context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_wallet) })

                }

            }
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
