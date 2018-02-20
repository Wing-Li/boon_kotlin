package com.lyl.boon.utils

import android.util.Log

/**
 * @author LX
 * @ClassName LogUtil
 * @Description Debug调试工具类
 * @date 2015年1月9日 下午5:29:55
 */
class LogUtil private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        private val TAG = "Boon"

        /*
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
        val isDebug = true

        /*
     * 下面四个是默认tag的函数
	 */

        fun i(msg: String?) {
            if (isDebug && msg != null)
                Log.i(TAG, msg)
        }

        fun d(msg: String?) {
            if (isDebug && msg != null)
                Log.d(TAG, msg)
        }

        fun e(msg: String?) {
            if (isDebug && msg != null)
                Log.e(TAG, msg)
        }

        fun v(msg: String?) {
            if (isDebug && msg != null)
                Log.v(TAG, msg)
        }

        /*
     * 下面是传入自定义tag的函数
	 */

        fun i(tag: String, msg: String?) {
            if (isDebug && msg != null)
                Log.i(tag, msg)
        }

        fun d(tag: String, msg: String?) {
            if (isDebug && msg != null)
                Log.d(tag, msg)
        }

        fun e(tag: String, msg: String?) {
            if (isDebug && msg != null)
                Log.e(tag, msg)
        }

        fun v(tag: String, msg: String?) {
            if (isDebug && msg != null)
                Log.v(tag, msg)
        }
    }

}
