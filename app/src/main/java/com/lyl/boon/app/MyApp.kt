package com.lyl.boon.app

import android.app.Application
import android.os.Environment
import android.text.TextUtils

import com.lyl.boon.BuildConfig
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport

import java.io.File

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initBugly()
    }

    /**
     * 初始化BUG分析 和 应用升级检测
     */
    private fun initBugly() {
        val buglyAppId = BuildConfig.BUGLYAPPID
        if (!TextUtils.isEmpty(buglyAppId)) {
            val strategy = CrashReport.UserStrategy(applicationContext)
            strategy.appReportDelay = 60000
            Bugly.init(applicationContext, buglyAppId, true, strategy)
        }
    }

    companion object {

        /** App 存放文件的路径 */
        private var mAppPath: String = ""

        /**
         * 获取存放文件的路径
         */
        val appPath: String
            get() {
                if (!TextUtils.isEmpty(mAppPath)) {
                    return mAppPath
                }
                val sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absoluteFile
                val file = File(sdCard, "boon")
                if (!file.exists()) {
                    file.mkdirs()
                }
                mAppPath = file.absolutePath
                return mAppPath
            }
    }

}
