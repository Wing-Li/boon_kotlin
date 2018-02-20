package com.lyl.boon.ui.base.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.lyl.boon.R
import com.lyl.boon.ui.MainViewPageAdpater
import com.lyl.boon.ui.base.BaseFragment
import kotterknife.bindView
import java.util.*

/**
 * 带 ViewPager 的 Fragment 基类封装
 * 使用者只需要重写 setFragment() 方法填充数据，就可以显示内容。
 *
 * Wing_Li
 * 2018/2/10.
 */
abstract class BaseMenuFragment : BaseFragment() {

    val mTablayout: TabLayout by bindView(R.id.m_tablayout)
    val mViewpager: ViewPager by bindView(R.id.m_viewpager)

    var mTitles: ArrayList<String> = ArrayList<String>()
    var mFragments: ArrayList<Fragment> = ArrayList<Fragment>()
    var mViewPageAdpater: MainViewPageAdpater? = null


    override val layoutResource: Int
        get() = R.layout.fragment_base

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    /**
     * 初始化 标题 和 页面
     */
    private fun initData() {

        // 初始化 标题 和 页面
        // 因为 Java 的对象是引用传递，所以这里直接将列表的引用传过去，使用者只需要填充数据就可以显示内容
        setFragment(mTitles, mFragments)

        // 将标题和页面设置给 ViewPager
        mViewPageAdpater?.setFragments(mFragments, mTitles)
        setTabLayout()

        //默认标题是第一个fragment的标题
        setTitle(0)
    }

    /**
     * 将 ViewPage 和 Tablayout 绑定
     */
    protected fun initView() {
        mViewPageAdpater = MainViewPageAdpater(childFragmentManager)
        mViewpager.adapter = mViewPageAdpater
        mTablayout.setupWithViewPager(mViewpager)

        // 滑动 Viewpager 修改标题
        mViewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setTitle(position)
                onPageChanged(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    /**
     * 当 tab 大于 4 个，就可以滑动
     */
    protected fun setTabLayout() {
        if (mViewPageAdpater!!.count > 4) {
            mTablayout.tabMode = TabLayout.MODE_SCROLLABLE
        } else {
            mTablayout.tabMode = TabLayout.MODE_FIXED
        }
    }

    /**
     * 改变Action的标题
     */
    private fun setTitle(position: Int) {
        if (mTitles.size > 0) {
            setTitle(mTitles[position])
        }
    }

    /**
     * 滑动页面所改变的内容;给子类提供一个方法，如果有需要的话，可以继承
     */
    protected fun onPageChanged(position: Int) {

    }

    /**
     * 建立 Fragment List，设置页面
     *
     * @param titles 页面的标题，添加的数量，必须与 页面 对应
     * @param fragments Fragment 页面，添加的数量必须与 标题 对应
     */
    protected abstract fun setFragment(titles: ArrayList<String>, fragments: ArrayList<Fragment>)

}
