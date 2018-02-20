package com.lyl.boon.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.MotionEvent

/**
 * 解决 PhotoView 缩小的时候 程序会挂掉。
 * Wing_Li
 * 2018/2/10.
 */
class HackyViewPager(context: Context?) : ViewPager(context) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onInterceptTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
        } catch (e: ArrayIndexOutOfBoundsException) {
        }

        return false
    }

}
