package com.lyl.boon.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lyl.boon.R
import com.lyl.boon.ui.base.BaseActivity
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.crashreport.CrashReport
import kotterknife.bindView

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class AboutActivity : BaseActivity() {

    val checkUpdate: TextView by bindView(R.id.check_update)
    val linkText: TextView by bindView(R.id.link_text)
    val wingLi: TextView by bindView(R.id.wing_li)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        ButterKnife.bind(this)

        initActionbar()

        mActionTitle.setText(getString(R.string.about_title))
    }

    @OnClick(R.id.check_update, R.id.link_text, R.id.wing_li)
    fun onClick(view: View) {

        when (view.id) {

            R.id.check_update ->
                /**
                 * @param isManual  用户手动点击检查，非用户点击操作请传false
                 * @param isSilence 是否显示弹窗等交互，[true:没有弹窗和toast] [false:有弹窗或toast]
                 */
                Beta.checkUpgrade(true, false)

            R.id.link_text -> openUri("https://www.coolapk.com/apk/com.lyl.boon")

            R.id.wing_li -> openUri("https://wing-li.github.io/")
        }
    }

    private fun openUri(uri: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            CrashReport.postCatchedException(e)
        }
    }
}
