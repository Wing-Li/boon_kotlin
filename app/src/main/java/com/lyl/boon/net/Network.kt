package com.lyl.boon.net

import com.lyl.boon.BuildConfig
import com.lyl.boon.net.api.GankApi
import com.lyl.boon.net.api.TngouApi
import com.lyl.boon.net.api.ZhuangbiApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
object Network {

    /**
     * Gank请求的地址
     */
    private val URL_GANK = "http://gank.io/api/"
    /**
     * 360图片 请求地址
     */
    private val URL_TNGOU = "http://image.so.com/"
    /**
     * 装逼图片地址
     */
    private val URL_ZHUANG = "http://zhuangbi.info/"

    /**
     * 设置超时的时间
     */
    private val DEFAULT_TIMEOUT = 5

    private var httpClientBuilder: OkHttpClient.Builder? = null
    private var gankApi: GankApi? = null
    private var tngouApi: TngouApi? = null
    private var zhuangbiApi: ZhuangbiApi? = null

    /**
     * 获取Gank.io 请求的操作。
     */
    val gankMenuList: GankApi?
        get() {
            if (gankApi == null) {
                gankApi = getRetrofit(URL_GANK).create(GankApi::class.java)
            }
            return gankApi
        }

    /**
     * 获取360图片接口的 请求的操作。
     */
    val tngou: TngouApi?
        get() {
            if (tngouApi == null) {
                tngouApi = getRetrofit(URL_TNGOU).create(TngouApi::class.java)
            }
            return tngouApi
        }

    /**
     * 装逼图片的接口
     */
    val zhuangbi: ZhuangbiApi?
        get() {
            if (zhuangbiApi == null) {
                zhuangbiApi = getRetrofit(URL_ZHUANG).create(ZhuangbiApi::class.java)
            }
            return zhuangbiApi
        }

    /**
     * 初始化 OkHttp
     */
    private fun initOkHttp() {
        httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder!!.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        // devCompile 'com.squareup.okhttp3:logging-interceptor:3.8.0'
        // compile 'com.squareup.okhttp3:okhttp:3.8.0'
        if ("dev" == BuildConfig.ENVIRONMENT) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder!!.addInterceptor(logging)
        }
    }

    private fun getRetrofit(url: String): Retrofit {
        if (httpClientBuilder == null) {
            initOkHttp()
        }
        return Retrofit.Builder()//
                .client(httpClientBuilder!!.build())//
                .baseUrl(url)//
                .addConverterFactory(GsonConverterFactory.create())//
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//
                .build()
    }

}
