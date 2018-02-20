package com.lyl.boon.ui.base

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.Toast
import com.lyl.boon.R
import com.lyl.boon.ui.AboutActivity

/**
 * Wing_Li
 * 2018/2/10.
 */
open class BaseActivity : AppCompatActivity() {

    // 界面顶部Bar
    lateinit var actionBar: ActionBar
    //整个顶部
    //标题
    lateinit var mActionTitle: TextSwitcher
    //标题图片
    lateinit var mActionRightImg: ImageView
    //侧边栏开关
    lateinit var mActionLeftImg: ImageView
    //返回按钮
    lateinit var mActionBack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * 初始化actionbar
     */
    protected fun initActionbar() {
        val lp = ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar
                .LayoutParams.MATCH_PARENT, Gravity.CENTER)
        val viewTitleBar = layoutInflater.inflate(R.layout.action_bar_title, null)

        actionBar = supportActionBar!!

        actionBar.setCustomView(viewTitleBar, lp)
        actionBar.setDisplayShowHomeEnabled(false)//去掉导航
        actionBar.setDisplayShowTitleEnabled(false)//去掉标题
        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setDisplayShowCustomEnabled(true)

        val actionView = supportActionBar?.customView ?: return

        //右边图标
        mActionRightImg = actionView.findViewById<View>(R.id.action_bar_right_img) as ImageView
        //标题文字
        mActionTitle = actionView.findViewById<View>(R.id.action_bar_title_txt) as TextSwitcher
        //左边图标
        mActionLeftImg = actionView.findViewById<View>(R.id.action_bar_left_img) as ImageView
        //左边文字
        mActionBack = actionView.findViewById<View>(R.id.action_bar_back_txt) as TextView
        setTitleAnims()
    }

    private fun setTitleAnims() {
        mActionTitle.setFactory {
            val textView = TextView(this@BaseActivity)
            textView.setSingleLine(true)
            textView.setTextAppearance(this@BaseActivity, R.style.action_title_style)
            textView.gravity = Gravity.CENTER
            textView.ellipsize = TextUtils.TruncateAt.MARQUEE
            textView.postDelayed({ textView.isSelected = true }, 1738)
            textView
        }
        mActionTitle.setInAnimation(this, android.R.anim.fade_in)
        mActionTitle.setOutAnimation(this, android.R.anim.fade_out)
    }

    fun setBackIcon() {
        mActionLeftImg.setImageResource(R.drawable.ic_back)
        mActionLeftImg.setColorFilter(Color.GRAY)
        mActionLeftImg.visibility = View.VISIBLE
        mActionLeftImg.setOnClickListener { finish() }
    }

    fun setAppAbout() {
        mActionBack.visibility = View.GONE
        mActionRightImg.visibility = View.VISIBLE
        mActionRightImg.setImageResource(R.drawable.ic_info_outline_black_24dp)
        mActionRightImg.setColorFilter(Color.GRAY)
        mActionRightImg.setOnClickListener { startActivity(Intent(this@BaseActivity, AboutActivity::class.java)) }
    }

    fun setShareIcon(shareContent: String) {
        mActionRightImg.visibility = View.VISIBLE
        mActionRightImg.setImageResource(R.drawable.ic_share_black_24dp)
        mActionRightImg.setColorFilter(Color.GRAY)
        mActionRightImg.setOnClickListener { shareContent(shareContent) }
    }

    fun shareContent(text: String) {
        if (TextUtils.isEmpty(text)) {
            showToast(R.string.share_err)
        } else {
            share(text)
        }
    }

    fun showToast(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    fun showToast(res: Int) {
        Toast.makeText(applicationContext, res, Toast.LENGTH_SHORT).show()
    }

    private fun share(str: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享")
        intent.putExtra(Intent.EXTRA_TEXT, str)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(intent, "分享"))
    }

}
