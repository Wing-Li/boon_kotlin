package com.lyl.boon.ui.base.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import com.lyl.boon.R
import com.lyl.boon.ui.base.BaseFragment
import com.lyl.boon.ui.base.apdter.MyBaseAdapter
import com.lyl.boon.view.recycler.OnRecycleViewScrollListener
import com.wang.avi.AVLoadingIndicatorView
import kotterknife.bindView
import rx.Observer

/**
 * Wing_Li
 * 2018/2/10.
 */
abstract class BaseRecyclerFragment<T> : BaseFragment() {

    val mRecyclerView: RecyclerView by bindView(R.id.mRecyclerView)
    val mSwipeRefreshLayout: SwipeRefreshLayout by bindView(R.id.mSwipeRefreshLayout)
    val mLoadingView: AVLoadingIndicatorView by bindView(R.id.loadingView)

    protected var mData: List<T>? = null
    protected var mAdapter: MyBaseAdapter<T>? = null
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    protected var mListType: Int = 0
    protected var page = 1
    protected var isLoading = false

    public override val layoutResource: Int
        get() = R.layout.fragment_list

    /**
     * 网络请求成功
     */
    private val observer = object : Observer<List<T>> {
        override fun onCompleted() {

        }

        override fun onError(e: Throwable) {
            getHolder().showToast(R.string.msg_net_erro)
            stopLoadingView()
            stopLoad()
        }

        override fun onNext(dataEntities: List<T>?) {
            stopLoadingView()

            if (dataEntities != null) {
                mAdapter!!.addAll(dataEntities)
            }

            stopLoad()
            page++
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initListType()
        initView()
        loadMore()
    }

    override fun onStop() {
        super.onStop()
        stopLoad()
        stopLoadingView()
    }

    /**
     * 设置列表的显示方式
     * 设置 滑动、刷新、点击 事件
     */
    private fun initView() {
        //必须在子页面new 出列表的Adapter
        if (mAdapter == null) {
            showToast(R.string.load_error)
            return
        }

        when (mListType) {
            TYPE_LIST//列表
            -> mLayoutManager = LinearLayoutManager(getHolder(), LinearLayoutManager.VERTICAL, false)
            TYPE_GRID//网格
            -> mLayoutManager = GridLayoutManager(getHolder(), GRID_COUNT)
            TYPE_STAG_V//竖向瀑布流
            -> mLayoutManager = StaggeredGridLayoutManager(GRID_COUNT, StaggeredGridLayoutManager.VERTICAL)
            TYPE_STAG_H//横向瀑布流
            -> mLayoutManager = StaggeredGridLayoutManager(GRID_COUNT, StaggeredGridLayoutManager.HORIZONTAL)

            else -> {
            }
        }

        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnScrollListener(object : OnRecycleViewScrollListener() {
            override fun onLoadMore() {
                if (!isLoading) {
                    loadMore()
                }
            }
        })
        mSwipeRefreshLayout.setOnRefreshListener {
            if (!isLoading) {
                page = 1
                mAdapter!!.clear()
                loadMore()
            }
        }
        mAdapter!!.setOnItemClickListener { itemView, viewType, position -> ItemClickListener(itemView, viewType, position) }
    }

    /**
     * 加载更多
     */
    protected fun loadMore() {
        isLoading = true
        setRefreshing(true)
        unsubscribe()
        setSubscribe(observer)
    }

    /**
     * 停止加载
     */
    private fun stopLoad() {
        if (mSwipeRefreshLayout.isRefreshing) {
            mSwipeRefreshLayout.isRefreshing = false
        }
        isLoading = false
    }

    /**
     * 停止加载动画
     */
    private fun stopLoadingView() {
        if (mLoadingView.isShown) {
            mLoadingView.visibility = View.GONE
            (mLoadingView.parent as ViewGroup).removeView(mLoadingView)
        }
    }

    /**
     * 显示刷新的进度圈
     */
    protected fun setRefreshing(b: Boolean) {
        mSwipeRefreshLayout.isRefreshing = b
    }

    /**
     * 设置订阅的 发布事件，observer在父类已经写好,如果需要做特殊处理，则重写一个。
     */
    protected abstract fun setSubscribe(observer: Observer<*>)

    /**
     * 设置列表的类型
     * 给 mListType 赋值以下类型
     *
     *
     * BaseRecyclerFragment.TYPE_LIST 列表；>
     * BaseRecyclerFragment.TYPE_GRID 网格；>
     * BaseRecyclerFragment.TYPE_STAG_V 竖向瀑布流；
     */
    protected abstract fun initListType()

    /**
     * 初始化 mData 和 mAdaper
     *
     *
     * mData 是 List<T>
     *
     *
     * mAdaper 必须继承 MyBaseAdapter
    </T> */
    protected abstract fun initData()

    /**
     * 点击事件
     *
     * @param itemView 当前的 Item
     * @param viewType 当有多种 Item 时，当前的类型
     * @param position 第几个
     */
    protected abstract fun ItemClickListener(itemView: View, viewType: Int, position: Int)

    companion object {

        val TYPE_LIST = 0
        val TYPE_GRID = 1
        val TYPE_STAG_V = 2
        val TYPE_STAG_H = 3

        /**
         * 网格列表的列数
         */
        val GRID_COUNT = 2
    }
}
