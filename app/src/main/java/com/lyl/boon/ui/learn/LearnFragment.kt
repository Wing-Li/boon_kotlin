package com.lyl.boon.ui.learn

import android.os.Bundle
import android.support.v4.app.Fragment
import com.lyl.boon.app.Constant
import com.lyl.boon.ui.base.fragment.BaseMenuFragment
import java.util.*

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class LearnFragment : BaseMenuFragment() {

    override fun setFragment(titles: ArrayList<String>, fragments: ArrayList<Fragment>) {
        addFragment(titles, fragments, Constant.GANK_TYPE_ANDROID, Constant.GANK_TYPE_ANDROID)
        addFragment(titles, fragments, Constant.GANK_TYPE_IOS, Constant.GANK_TYPE_IOS)
        addFragment(titles, fragments, Constant.GANK_TYPE_WEB, Constant.GANK_TYPE_WEB)
    }

    private fun addFragment(titles: MutableList<String>, fragments: MutableList<Fragment>, title: String, type: String) {
        titles.add(title)

        val fragment = LearnListFragment()
        val bundle = Bundle()
        bundle.putString(LEARN_TYPE, type)
        fragment.arguments = bundle
        fragments.add(fragment)
    }

    companion object {
        val LEARN_TYPE = "type"
    }
}
