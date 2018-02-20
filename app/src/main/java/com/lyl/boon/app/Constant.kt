package com.lyl.boon.app

import com.lyl.boon.BuildConfig

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
object Constant {

    /**
     * 这里是从 Gradle 设置的
     */
    val ENVIRONMENT = BuildConfig.ENVIRONMENT

    /**
     * Gank.io API 接口的类型
     */
    val GANK_TYPE_ANDROID = "Android"
    val GANK_TYPE_IOS = "iOS"
    val GANK_TYPE_WEB = "前端"

    /**
     * 启动HTML页面的名称
     */
    val WEB_NAME = "com.lyl.boon.ui.activity.htmlactivity"

}
