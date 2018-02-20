package com.lyl.boon.utils

import com.lyl.boon.R
import java.util.*

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
object MyUtils {

    var mTextColors = intArrayOf(R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color
            .color6, R.color.color7, R.color.color8, R.color.color9, R.color.color10)

    val colors: Int
        get() = mTextColors[Random().nextInt(6)]

}
