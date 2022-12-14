package com.example.young_forest.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.young_forest.Fragments.EngToMor
import com.example.young_forest.Fragments.MorToEng

internal class MyAdapter (var context:Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                EngToMor()
            }

            1 -> {
                MorToEng()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}