package com.lyl.boon.ui.superboon

import android.os.Bundle
import android.support.v4.app.Fragment
import com.lyl.boon.ui.base.fragment.BaseMenuFragment
import java.util.*

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class SuperBoonFragment : BaseMenuFragment() {

    override fun setFragment(titles: ArrayList<String>, fragments: ArrayList<Fragment>) {
        addFragment(titles, fragments, "萌妹子", 595)
        addFragment(titles, fragments, "少女", 625)
        addFragment(titles, fragments, "婚纱", 596)
        addFragment(titles, fragments, "车模", 600)
        addFragment(titles, fragments, "明星", 599)
        addFragment(titles, fragments, "街拍", 596)
        addFragment(titles, fragments, "时装秀", 2006)
        addFragment(titles, fragments, "女孩们", 2007)
        addFragment(titles, fragments, "cosplay", 598)
    }

    private fun addFragment(titles: MutableList<String>, fragments: MutableList<Fragment>, title: String, id: Int) {
        titles.add(title)

        val fragment = SuperBoonListFragment()
        val bundle = Bundle()
        bundle.putInt(SUPER_TYPE, id)
        fragment.arguments = bundle
        fragments.add(fragment)
    }

    companion object {

        val SUPER_TYPE = "type"
    }
}
