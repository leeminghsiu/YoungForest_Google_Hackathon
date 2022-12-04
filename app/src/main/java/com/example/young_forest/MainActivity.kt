package com.example.young_forest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import androidx.viewpager.widget.ViewPager
import com.example.young_forest.Adapter.MyAdapter
import com.example.young_forest.Fragments.OnDataPasser
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabView

class MainActivity : AppCompatActivity(), OnDataPasser {

    private lateinit var tabView: TabLayout
    private lateinit var viewPager: ViewPager
    lateinit var nums: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabView = findViewById(R.id.tabView)
        viewPager = findViewById(R.id.viewPager)

        tabView.addTab(tabView.newTab().setText("Eng To Mor"))
        tabView.addTab(tabView.newTab().setText("Mor To Eng"))
        tabView.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(this, supportFragmentManager, tabView.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabView))
        tabView.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onDataPasser(data: IntArray) {
        nums = data
    }
}