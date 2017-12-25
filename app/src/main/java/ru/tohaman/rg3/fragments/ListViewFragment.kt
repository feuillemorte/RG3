package ru.tohaman.rg3.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import ru.tohaman.rg3.DebugTag

import ru.tohaman.rg3.R
import ru.tohaman.rg3.adapters.MyListAdapter
import ru.tohaman.rg3.data.ListPager
import ru.tohaman.rg3.data.ListPagerLab
import java.util.ArrayList
import android.graphics.drawable.ColorDrawable
import android.os.Build


/**
 * ListViewFragment - фрагмент в котором встроенный ListView
 * отображает лист этапов определенной фазы
 * Активити содержащая этот fragment должна имплементить
 * [ListViewFragment.OnListViewInteractionListener] interface
 * для получения ответов от этого фрагмента.
 * Для создания экземпляра фрагмента используйте [ListViewFragment.newInstance]
 */
class ListViewFragment : ListFragment() {
    private var mPhase: String = "BEGIN"

    private var mListener: OnListViewInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v (DebugTag.TAG, "ListViewFragment onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Берем layout listview_4_fragment, чтобы небольшие литсвью располагались по центру фрагмента, а не по верхнему краю
        return inflater!!.inflate(R.layout.listview_4_fragment, null)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.v (DebugTag.TAG, "ListViewFragment onActivityCreated $mPhase")
        super.onActivityCreated(savedInstanceState)
        val mListPagers : ArrayList<ListPager> = ListPagerLab.get(context!!).getPhaseList(mPhase)
        val mListAdapter = MyListAdapter(mListPagers,1.5f)
        listAdapter = mListAdapter
        //толщина разделителя между пунктами меню
        listView.dividerHeight = 1
        //цвет разделителя между пунктами меню
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            listView.divider = ColorDrawable(resources.getColor(R.color.transparent,null))
        } else {
            @Suppress("DEPRECATION")
            listView.divider = ColorDrawable(resources.getColor(R.color.transparent))
        }
    }

    fun changePhase (phase: String, context: Context?) {
        Log.v (DebugTag.TAG, "ListViewFragment changePhase $phase")
        mPhase = phase
        val mListPagers : ArrayList<ListPager> = ListPagerLab.get(context!!).getPhaseList(mPhase)
        val mListAdapter = MyListAdapter(mListPagers,1.5f)
        listAdapter = mListAdapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        if (mListener != null) {
            mListener!!.onListViewInteraction(mPhase,position)
        }
        //toast("Ваш выбор $mPhase, $position")
    }

    override fun onAttach(context: Context?) {
        Log.v (DebugTag.TAG, "ListViewFragment onAttach")
        super.onAttach(context)
        if (context is OnListViewInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListViewInteractionListener")
        }
    }

    override fun onDetach() {
        Log.v (DebugTag.TAG, "ListViewFragment onDetach")
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListViewInteractionListener {
        fun onListViewInteraction(phase: String, id : Int) {
            Log.v(DebugTag.TAG, "ListViewFragment onListViewInteraction")
        }
    }

    companion object {
        fun newInstance(param1: String): ListViewFragment {
            Log.v(DebugTag.TAG, "ListViewFragment newInstance")
            val fragment = ListViewFragment()
            fragment.mPhase = param1
            return fragment
        }
    }
}// Required empty public constructor
