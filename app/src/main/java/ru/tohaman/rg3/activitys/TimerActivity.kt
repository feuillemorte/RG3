package ru.tohaman.rg3.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import ru.tohaman.rg3.DebugTag.TAG
import ru.tohaman.rg3.R
import ru.tohaman.rg3.fragments.FragmentTimer

class TimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.v (TAG, "TimerActivity Start")
        val transaction : FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.replace(R.id.fragment_container, FragmentTimer())?.commit()
    }
}
