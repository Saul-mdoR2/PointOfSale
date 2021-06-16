package com.example.pointofsale.presentation.listProducts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class ViewPagerAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    var fragmentList:ArrayList<Fragment>? = null
    var fragmentTitlesList:ArrayList<String>? = null

    init {
        fragmentList = ArrayList()
        fragmentTitlesList = ArrayList()
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList?.get(position)!!
    }

    override fun getCount(): Int {
        return fragmentList?.size!!
    }

    fun addFragment(fragment:Fragment, titleFragment:String){
        fragmentList?.add(fragment)
        fragmentTitlesList?.add(titleFragment)
    }

    override fun getPageTitle(position: Int): String? {
        return fragmentTitlesList?.get(position)
    }
}