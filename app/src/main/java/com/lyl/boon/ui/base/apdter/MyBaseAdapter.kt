package com.lyl.boon.ui.base.apdter

import android.content.Context

import org.byteam.superadapter.SuperAdapter
import org.byteam.superadapter.internal.SuperViewHolder

/**
 * 本着二次封装的原则，把使用的这个库在封装一遍，如果这个库出现重大问题，使用新库时将其封装为相同的用法。
 * Wing_Li
 * 2018/2/10.
 */
open class MyBaseAdapter<T>(
        var mContext: Context,
        items: List<T>,
        layoutResId: Int
) : SuperAdapter<T>(mContext, items, layoutResId) {

    override fun onBind(holder: SuperViewHolder, viewType: Int, position: Int, item: T) {

    }

}
