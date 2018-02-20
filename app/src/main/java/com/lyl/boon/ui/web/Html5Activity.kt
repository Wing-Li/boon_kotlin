package com.lyl.boon.ui.web

import android.os.Bundle
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.lyl.boon.R
import com.lyl.boon.ui.base.BaseActivity
import com.lyl.boon.utils.LogUtil
import com.lyl.boon.view.loading.LoadingView


class Html5Activity : BaseActivity() {

    private var mUrl: String? = null
    private var mDesc: String? = null

    private var mLayout: RelativeLayout? = null
    private var mLoadingView: LoadingView? = null
    private var mWebView: WebView? = null

    internal var webViewClient: WebViewClient = object : WebViewClient() {

        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            stopLoading()
        }
    }

    internal var webChromeClient: WebChromeClient = object : WebChromeClient() {

        //=========多窗口的问题==========================================================
        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message): Boolean {
            val result = view.hitTestResult
            val data = result.extra
            mWebView!!.loadUrl(data)
            return true
        }
        //=========多窗口的问题==========================================================

        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress > 80) {
                stopLoading()
            }
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            mActionTitle.setText(title)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val bundle = intent.getBundleExtra("bundle")
        if (bundle != null) {
            mUrl = bundle.getString("url")
            mDesc = bundle.getString("desc")
            LogUtil.d("Web--Url:", mUrl)
        } else {
            showToast(getString(R.string.param_error))
            return
        }

        initActionbar()
        setBackIcon()
        // 设置分享
        val share = mDesc + " 链接地址:" + mUrl
        setShareIcon(share)

        initWebView()
    }

    private fun initWebView() {
        mLayout = findViewById<View>(R.id.web_layout) as RelativeLayout
        mLoadingView = findViewById<View>(R.id.loadingView) as LoadingView

        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        mWebView = WebView(applicationContext)
        mWebView!!.layoutParams = params
        mLayout!!.addView(mWebView)
        mLoadingView!!.bringToFront()

        val mWebSettings = mWebView!!.settings
        mWebSettings.setSupportZoom(true)
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.useWideViewPort = true
        mWebSettings.defaultTextEncodingName = "utf-8"
        mWebSettings.loadsImagesAutomatically = true
        mWebSettings.setSupportMultipleWindows(true)

        saveData(mWebSettings)
        newWin(mWebSettings)

        mWebView!!.webChromeClient = webChromeClient
        mWebView!!.webViewClient = webViewClient

        mWebView!!.loadUrl(mUrl)
    }

    /**
     * 多窗口的问题
     */
    private fun newWin(mWebSettings: WebSettings) {
        //html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
        //然后 复写 WebChromeClient的onCreateWindow方法
        mWebSettings.setSupportMultipleWindows(true)
        mWebSettings.javaScriptCanOpenWindowsAutomatically = true
    }

    /**
     * HTML5数据存储
     */
    private fun saveData(mWebSettings: WebSettings) {
        //有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
        val cacheDir = applicationContext.cacheDir
        if (cacheDir != null) {
            val appCachePath = cacheDir.absolutePath
            mWebSettings.domStorageEnabled = true
            mWebSettings.databaseEnabled = true
            mWebSettings.setAppCacheEnabled(true)
            mWebSettings.setAppCachePath(appCachePath)
        }
    }

    private fun stopLoading(): Boolean {
        if (mLoadingView != null && mLoadingView!!.isShown) {
            mLoadingView!!.visibility = View.GONE
            mLayout!!.removeView(mLoadingView)
            return true
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (stopLoading()) {
                return true
            }
            if (mWebView!!.canGoBack()) {
                mWebView!!.goBack()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mWebView != null) {
            mWebView!!.clearHistory()
            (mWebView!!.parent as ViewGroup).removeView(mWebView)
            mWebView!!.loadUrl("about:blank")
            mWebView!!.stopLoading()
            mWebView!!.webChromeClient = null
            mWebView!!.webViewClient = null
            mWebView!!.destroy()
            mWebView = null
        }
        System.exit(0)
    }

}
