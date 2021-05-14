package com.example.mainprojecte


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_ranking.view.*

/**
 * A simple [Fragment] subclass.
 */
class RankingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)

        view.tabLayout.setupWithViewPager(view.childviewager)


        val childs= generateChilds()
        val adapter = PageViewAdapter(childFragmentManager, childs)

        view.childviewager.adapter = adapter
        return view
    }
    private fun generateChilds(): List<Fragment> {
        val list = listOf<Fragment>(
            GlobalFill("Global")
            ,LocalFill("Local"))
        return list
    }

}

