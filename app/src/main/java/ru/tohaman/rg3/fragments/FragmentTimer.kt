package ru.tohaman.rg3.fragments


import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.withArguments

import ru.tohaman.rg3.constraintLayout
import ru.tohaman.rg3.listpager.ListPager
import ru.tohaman.rg3.ui.FragmentPagerItemtUI
import ru.tohaman.rg3.ui.TimerUI


class FragmentTimer : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater!!.inflate(R.layout.fragment_timer, container, false)
        return TimerUI<Fragment>().createView(AnkoContext.create(context, this))
    }


    companion object {
        fun newInstance(lp: ListPager): FragmentPagerItem {
            return FragmentPagerItem().withArguments("title" to lp.title,
                    "topImage" to lp.icon,
                    "desc" to lp.description,
                    "url" to lp.url)
        }
    }
}

