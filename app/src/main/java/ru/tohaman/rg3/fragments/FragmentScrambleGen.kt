package ru.tohaman.rg3.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import ru.tohaman.rg3.R
import ru.tohaman.rg3.ui.ScrambleGenUI


class FragmentScrambleGen : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_scramble_gen, container, false)

        return view
    }

}

