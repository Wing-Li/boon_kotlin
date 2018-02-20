package com.lyl.boon.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class MainViewPageAdpater : FragmentPagerAdapter {

    private var fragments: List<Fragment>? = null
    private var titles: List<String>? = null

    constructor(fm: FragmentManager, fragments: List<Fragment>, titles: List<String>) : super(fm) {
        this.fragments = fragments
        this.titles = titles
    }

    constructor(fm: FragmentManager) : super(fm) {}

    fun setFragments(fragments: List<Fragment>, titles: List<String>) {
        this.fragments = fragments
        this.titles = titles
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (titles != null) {
            titles!![position]
        } else ""
    }

    override fun getCount(): Int {
        return if (fragments == null) 0 else fragments!!.size
    }

}
