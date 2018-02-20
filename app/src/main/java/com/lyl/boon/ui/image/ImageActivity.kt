package com.lyl.boon.ui.image

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import butterknife.ButterKnife
import com.lyl.boon.R
import com.lyl.boon.ui.base.BaseActivity
import com.lyl.boon.view.HackyViewPager
import kotterknife.bindView
import java.util.*

/**
 * 全屏显示图片
 */
class ImageActivity : BaseActivity() {

    val imageViewpager: HackyViewPager by bindView(R.id.image_viewpager)

    // 图片列表 及 当前位置
    private var imgs: List<String>? = null
    private var position: Int = 0

    private lateinit var mImageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏,隐藏状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_image)
        ButterKnife.bind(this)

        supportActionBar!!.hide()
        initData()
        initView()
    }

    private fun initData() {
        val bundle = intent.getBundleExtra("bundle")
        if (bundle != null) {
            imgs = bundle.getStringArrayList("imgs")
            position = bundle.getInt("position")
        } else {
            showToast(R.string.msg_net_erro)
            return
        }
    }

    private fun initView() {
        mImageAdapter = ImageAdapter(this, imgs)
        imageViewpager.adapter = mImageAdapter
        imageViewpager.offscreenPageLimit = 2
        imageViewpager.currentItem = position
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    companion object {

        fun getIntent(activity: Activity, imgs: ArrayList<String>, position: Int): Intent {
            val intent = Intent(activity, ImageActivity::class.java)
            val bundle = Bundle()
            bundle.putStringArrayList("imgs", imgs)
            bundle.putInt("position", position)
            intent.putExtra("bundle", bundle)
            return intent
        }
    }

}
