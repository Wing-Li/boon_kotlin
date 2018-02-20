package com.lyl.boon.ui.superboon

import android.os.Bundle

import com.lyl.boon.R
import com.lyl.boon.ui.base.BaseActivity

class SuperGalleryActivity : BaseActivity() {

    private var galleryFragment: SuperGalleryFragment? = null
    private var mId: String? = null
    private var mTitle: String? = null
    private var mMenu: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_gallery)

        val budele = intent.getBundleExtra("budele")
        if (budele != null) {
            mId = budele.getString("id")
            mTitle = budele.getString("title")
            mMenu = budele.getInt("menu")
        } else {
            showToast(R.string.msg_net_erro)
            return
        }

        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {
        initActionbar()

        if (galleryFragment == null) {
            galleryFragment = SuperGalleryFragment()
            val bundle = Bundle()
            bundle.putString("id", mId)
            bundle.putString("title", mTitle)
            bundle.putInt("menu", mMenu)
            galleryFragment!!.arguments = bundle
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.activity_super_gallery, galleryFragment).commit()
        }
    }

}
