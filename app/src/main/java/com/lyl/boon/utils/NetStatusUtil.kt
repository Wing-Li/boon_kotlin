package com.lyl.boon.utils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class NetStatusUtil {

    /**
     * 枚举网络状态 NET_NO：没有网络 NET_2G:2g网络 NET_3G：3g网络 NET_4G：4g网络 NET_WIFI：wifi
     * NET_UNKNOWN：未知网络
     */
    enum class NetState {
        NET_NO, NET_2G, NET_3G, NET_4G, NET_WIFI, NET_UNKNOWN
    }

    /**
     * 判断当前是否网络连接
     *
     * @param context 上下文
     * @return 状态码
     */
    fun isConnected(context: Context): NetState {
        var stateCode = NetState.NET_NO
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        if (ni != null && ni.isConnectedOrConnecting) {
            when (ni.type) {
                ConnectivityManager.TYPE_WIFI -> stateCode = NetState.NET_WIFI
                ConnectivityManager.TYPE_MOBILE -> when (ni.subtype) {
                    TelephonyManager.NETWORK_TYPE_GPRS // 联通2g
                        , TelephonyManager.NETWORK_TYPE_CDMA // 电信2g
                        , TelephonyManager.NETWORK_TYPE_EDGE // 移动2g
                        , TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> stateCode = NetState.NET_2G
                    TelephonyManager.NETWORK_TYPE_EVDO_A // 电信3g
                        , TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> stateCode = NetState.NET_3G
                    TelephonyManager.NETWORK_TYPE_LTE -> stateCode = NetState.NET_4G
                    else -> stateCode = NetState.NET_UNKNOWN
                }
                else -> stateCode = NetState.NET_UNKNOWN
            }
        }
        return stateCode
    }

    companion object {

        /**
         * 检测当前打开的网络类型是否WIFI
         *
         * @param context 上下文
         * @return 是否是Wifi上网
         */
        fun isWifi(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }

}
