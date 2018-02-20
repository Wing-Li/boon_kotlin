package com.lyl.boon.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.Toast
import butterknife.ButterKnife
import com.lyl.boon.R
import rx.Subscription

/**
* Author: lyl
* Date Created : 2018/2/10.
*/
abstract class BaseFragment : Fragment() {

    private lateinit var rootView: View
    private var holder: BaseActivity? = null
    protected var subscription: Subscription? = null

    protected abstract val layoutResource: Int

    val name: String
        get() = BaseFragment::class.java.name

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            holder = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(layoutResource, container, false)
        ButterKnife.bind(this, rootView)

        return rootView
    }

    fun getHolder(): BaseActivity {
        if (holder == null) {
            throw IllegalArgumentException("the acticity must be extends BaseActivity")
        }
        return holder!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.unbind(this)
        unsubscribe()
    }

    protected fun unsubscribe() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription?.unsubscribe()
            subscription = null
        }
    }

    protected fun setTitle(title: String) {
        val view = getHolder().supportActionBar!!.customView
        val titleV = view.findViewById<View>(R.id.action_bar_title_txt) as TextSwitcher
        titleV.setText(title)
    }

    protected fun showToast(str: String) {
        Toast.makeText(getHolder().applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(res: Int) {
        Toast.makeText(getHolder().applicationContext, res, Toast.LENGTH_SHORT).show()
    }

}
