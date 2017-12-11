package ru.tohaman.rg3

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity
import ru.tohaman.rg3.listpager.ListPagerLab
import ru.tohaman.rg3.DebugTag.TAG
import ru.tohaman.rg3.activitys.SlidingTabsActivity
import ru.tohaman.rg3.fragments.FragmentTimer
import ru.tohaman.rg3.fragments.ListViewFragment

// Статические переменные (верхнего уровня). Котлин в действии стр.77-78
const val EXTRA_ID = "ru.tohaman.rubicsguide.PHASE_ID"
const val RUBIC_PHASE = "ru.tohaman.rubicsguide.PHASE"
lateinit var fragListView: ListViewFragment
lateinit var fragTimer: Fragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ListViewFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v (TAG, "MainActivity ListPagerLab init")
        val mListPagerLab = ListPagerLab.get(this)
        Log.v (TAG, "MainActivity CreateView")
        setContentView(R.layout.activity_main)
        setSupportActionBar(maintoolbar)

        fab.setOnClickListener { view ->
            drawer_layout.openDrawer(GravityCompat.START)
//            drawer_layout.closeDrawer(GravityCompat.START)
//            fragListView.changePhase("BASIC", this)
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, maintoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        fragTimer = FragmentTimer()

        fragListView = ListViewFragment.newInstance("BEGIN")
        val transaction : FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.replace(R.id.frame_container, fragListView)?.commit()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Handle navigation view item clicks here.
        val transaction : FragmentTransaction? = supportFragmentManager.beginTransaction()
        Log.v(DebugTag.TAG, "NavigationItemSelected $item.itemId")
        when (item.itemId) {
            R.id.begin2x2 -> {
                transaction?.replace(R.id.frame_container, fragListView)?.commit()
                fragListView.changePhase("BEGIN2X2", this)
            }
            R.id.adv2x2 -> {
                transaction?.replace(R.id.frame_container, fragListView)?.commit()
                fragListView.changePhase("ADV2X2", this)
            }
            R.id.begin -> {
                transaction?.replace(R.id.frame_container, fragListView)?.commit()
                fragListView.changePhase("BEGIN", this)
            }
            R.id.g2f -> {
                transaction?.replace(R.id.frame_container, fragListView)?.commit()
                fragListView.changePhase("G2F", this)
            }
            R.id.blind -> {

            }
            R.id.blind_acc -> {

            }
            R.id.begin4x4 -> {

            }
            R.id.timer -> {
                transaction?.replace(R.id.frame_container, fragTimer)?.commit()
            }
            R.id.scramble -> {

            }
            R.id.pll_game -> {

            }
            R.id.basic_move -> {

            }


        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(phase:String, id:Int) {
        //Обработка событий из ListViewFragment
        startActivity<SlidingTabsActivity>(RUBIC_PHASE to phase, EXTRA_ID to id)
    }
}
