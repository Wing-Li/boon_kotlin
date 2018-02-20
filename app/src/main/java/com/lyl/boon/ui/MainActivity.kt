package com.lyl.boon.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.Window
import android.widget.Toast
import butterknife.ButterKnife
import com.lyl.boon.R
import com.lyl.boon.ui.base.BaseActivity
import com.lyl.boon.ui.joke.JokeFragment
import com.lyl.boon.ui.learn.LearnFragment
import com.lyl.boon.ui.superboon.SuperBoonFragment
import com.lyl.boon.ui.young.YoungFragment
import com.lyl.boon.utils.NetStatusUtil
import com.roughike.bottombar.BottomBar
import kotterknife.bindView
import me.drakeet.materialdialog.MaterialDialog

class MainActivity : BaseActivity() {

    private var learnFragment: LearnFragment? = null
    private var youngFragment: YoungFragment? = null
    private var jokeFragment: JokeFragment? = null
    private var superFragment: SuperBoonFragment? = null

    private var oldFragment: Fragment? = null

    val mBottomBar: BottomBar by bindView(R.id.bottomBar)


    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        initActionbar()
        setAppAbout()
        initBottom()
        initFragmentContent()
    }

    /**
     * 设置中间内容页
     */
    private fun initFragmentContent() {
        learnFragment = LearnFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_content, learnFragment).commit()
        oldFragment = learnFragment
    }

    /**
     * 设置底部按钮
     */
    private fun initBottom() {
        mBottomBar!!.setOnTabSelectListener { tabId ->
            when (tabId) {

                R.id.menu_learn //学习
                -> {
                    setActTitle(R.string.menu_learn_msg)
                    toFragment(learnFragment)
                }

                R.id.menu_joke //开心
                -> checkNet(1)

                R.id.menu_young //美女
                -> checkNet(2)

                R.id.menu_super //超级福利
                -> checkNet(3)

                else -> {
                    setActTitle(R.string.menu_learn_msg)
                    toFragment(learnFragment)
                }
            }
        }
    }

    /**
     * 检查网络，并跳转
     */
    private fun checkNet(position: Int) {
        if (NetStatusUtil.isWifi(this@MainActivity)) {
            goFragment(position)
        } else {
            val dialog = MaterialDialog(this)
            dialog.setTitle("提示")
            dialog.setMessage("您当前不是WIFI状态，访问会消耗大量的流量，您确定要访问吗？")
            dialog.setPositiveButton("没事儿拼了") {
                goFragment(position)
                dialog.dismiss()
            }
            dialog.setNegativeButton("还是不看了") {
                dialog.dismiss()
                showToast("(*^__^*) 没事去读书学习吧")
                mBottomBar!!.selectTabWithId(R.id.menu_learn)
            }

            dialog.show()
        }
    }

    private fun goFragment(position: Int) {

        when (position) {

            1//开心
            -> {
                setActTitle(R.string.menu_joke_msg)
                if (jokeFragment == null) {
                    jokeFragment = JokeFragment()
                }
                toFragment(jokeFragment)
            }

            2 //美女
            -> {
                setActTitle(R.string.menu_young_msg)
                if (youngFragment == null) {
                    youngFragment = YoungFragment()
                }
                toFragment(youngFragment)
            }

            3 //超级福利
            -> {
                setActTitle(R.string.menu_super_msg)
                if (superFragment == null) {
                    superFragment = SuperBoonFragment()
                }
                toFragment(superFragment)
            }
        }
    }

    /**
     * 切换Fragment
     *
     * @param to 下一个Fragment页面
     */
    private fun toFragment(to: Fragment?) {
        if (to === oldFragment) return

        val transaction = supportFragmentManager.beginTransaction().setCustomAnimations(android
                .R.anim.fade_in, android.R.anim.fade_out)
        if (!to!!.isAdded) {    // 先判断是否被add过
            transaction.hide(oldFragment).add(R.id.fragment_content, to).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(oldFragment).show(to).commit() // 隐藏当前的fragment，显示下一个
        }
        oldFragment = to
    }

    /**
     * 设置导航栏的标题
     */
    private fun setActTitle(res: Int) {
        mActionTitle.setText(getString(res))
    }

    //***************************
    // 双击返回退出
    //***************************

    private var time: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time <= 2000) {
                finish()
            } else {
                time = System.currentTimeMillis()
                Toast.makeText(applicationContext, R.string.exit_app, Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
