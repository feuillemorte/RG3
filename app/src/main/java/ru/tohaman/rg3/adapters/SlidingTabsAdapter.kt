package ru.tohaman.rg3.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import ru.tohaman.rg3.fragments.FragmentPagerItem
import ru.tohaman.rg3.listpager.ListPager

class SlidingTabsAdapter(fm: FragmentManager, lp: List<ListPager>) : FragmentPagerAdapter(fm) {

    private var titles  = lp

    override fun getPageTitle(position: Int): CharSequence {

        return titles[position].title
    }

    override fun getCount(): Int {

        return titles.size
    }

    override fun getItem(position: Int): Fragment {
        return FragmentPagerItem.newInstance(titles[position].title)
    }

}
