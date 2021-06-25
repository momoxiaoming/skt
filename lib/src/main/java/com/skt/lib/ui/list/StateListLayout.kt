package com.skt.lib.ui.list

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * StateRecycleView
 * RecycleView 状态封装,支持上拉刷新以及下拉加载
 * @author mmxm
 * @date 2021/6/23 16:08
 */
interface IStateListView<T, VH : BaseViewHolder> {

    /**
     *
     * 初始化recycleview和adapter联系
     * @param layoutManager LayoutManager
     * @param adapter AbsStateAdapter<T, VH>
     * @param showLoadingView Boolean ture表示显示加载view ,默认不启用
     */
    fun initConfig(layoutManager: RecyclerView.LayoutManager, adapter: AbsStateAdapter<T, VH>,showLoadingView:Boolean=false)

    /**
     * 设置下拉刷新
     * @param onRefreshListener RefreshListener
     */
    fun setOnRefreshListener( onRefreshListener: RefreshListener)

    /**
     * 设置上拉加载
     * @param onLoadMoreListener LoadMoreListener
     */
    fun setOnLoadMoreListener( onLoadMoreListener: LoadMoreListener)

    /**
     * 控制下拉刷新
     */
    fun enableRefresh(enable: Boolean)

    /**
     * 控制上拉加载
     */
    fun enableLoadMore(enable: Boolean)

    /**
     * 设置数据方法,此方法可以无需关心页数问题 也可以使用[setListData]方法刷新
     * @param hasNext Boolean 是否还有下一页
     * @param dataList MutableList<T>? 当如果获取数据发生失败时,dataList可以传null,此时stateLayout内部会处理是否展示错误的占位图
     */
    fun setListData(hasNext: Boolean, dataList: MutableList<T>?)

    /**
     * setListData 衍生方法,需要指明当前页数
     * @param page Int  页数
     * @param hasNext Boolean 是否还有下一页
     * @param dataList MutableList<T> 当如果获取数据发生失败时,dataList可以传null,此时stateLayout内部会处理是否展示错误的占位图
     */
    fun setListData(page: Int = 1, hasNext: Boolean = false, dataList: MutableList<T>?)

    /**
     * 重置页数
     */
    fun resetPage()

}

interface LoadMoreListener {
    fun onLoadMoreListener()
}

interface RefreshListener {
    fun onRefreshListener()
}


class StateListLayout<T, VH : BaseViewHolder> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleRes), IStateListView<T, VH> {

    private lateinit var recycleView: RecyclerView
    private lateinit var smartLayout: SmartRefreshLayout
    var page: Int = 1
    private lateinit var adapter: AbsStateAdapter<T, VH>
    private var enableRefresh = false  //启动下拉刷新,默认不启用
    private var enableLoadMore = false //启用上拉加载,默认不启用


    init {
        initView()
    }

    private fun initView() {
        smartLayout = SmartRefreshLayout(context)
        recycleView = RecyclerView(context)
        smartLayout.setRefreshHeader(ClassicsHeader(context)) //设置经典刷新头,
        smartLayout.setRefreshFooter(ClassicsFooter(context)) //设置经典上拉,这里我们使用baseQuickAdapter自带的上拉加载
        enableRefresh(enableRefresh)
        enableLoadMore(enableLoadMore)
        recycleView.overScrollMode = OVER_SCROLL_NEVER
        smartLayout.addView(
            recycleView,
            ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )  //设置全屏,否者状态view无法填充满
        addView(
            smartLayout,
            ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )

    }

    /**
     * 重置页数
     */
    override fun resetPage() {
        page = 1
    }

    override fun setOnRefreshListener(onRefreshListener: RefreshListener) {
        enableRefresh(true)  //默认隐藏下来刷新
        smartLayout.setOnRefreshListener {
            page = 1
            onRefreshListener.onRefreshListener()
        }
    }

    /**
     * 设置上拉加载监听
     * @param onLoadMoreListener LoadMoreListener
     */
    override fun setOnLoadMoreListener(onLoadMoreListener: LoadMoreListener) {
        enableLoadMore(true) //默认隐藏上拉加载
        smartLayout.setOnLoadMoreListener {
            page += 1
            onLoadMoreListener.onLoadMoreListener()
        }
    }

    override fun enableRefresh(enable: Boolean) {
        this.enableRefresh = enable
        smartLayout.setEnableRefresh(enableRefresh)  //默认隐藏下来刷新
    }

    override fun enableLoadMore(enable: Boolean) {
        this.enableLoadMore = enable
        smartLayout.setEnableLoadMore(enableLoadMore) //默认隐藏上拉加载
    }

    override fun initConfig(
        layoutManager: RecyclerView.LayoutManager,
        adapter: AbsStateAdapter<T, VH>,
        showLoadingView:Boolean
    ) {
        recycleView.layoutManager = layoutManager
        recycleView.adapter = adapter
        this.adapter = adapter
        if(showLoadingView){
            adapter.showLoadingView()
        }
    }

    override fun setListData(hasNext: Boolean, dataList: MutableList<T>?) {
        setListData(page, hasNext, dataList)
    }

    /**
     * 更新当前数据,此方法将会伴随状态视图切换
     * @param page Int 当前页数
     * @param hasNext Boolean 是否还有更多数据
     * @param dataList List<T> data数据
     */
    override fun setListData(page: Int, hasNext: Boolean, dataList: MutableList<T>?) {
        checkNotNull(adapter, { "adapter 不能为空" })
        if (page == 1) {
            when{
                dataList==null ->{
                    finishRefresh(false)
                    if(adapter.data.isEmpty()){
                        //显示错误视图
                        setEnableRefresh(false)
                        setEnableLoadMore(false)
                        adapter.showErrorView()
                    }
                }
                dataList.size==0->{
                    finishRefresh(false)
                    if (adapter.data.isEmpty()) {
                        //如果adapter中本身无数据,就直接显示空视图
                        setEnableRefresh(false)
                        setEnableLoadMore(false)
                        adapter.showEmptyView()
                    }
                }
                else ->{
                    finishRefresh(true)
                    setEnableLoadMore(hasNext)
                    setEnableRefresh(true)
                    adapter.setList(dataList)
                }
            }
        } else {
            //请求第二页以后数据,此时默认adapter中已有数据了
            if(dataList==null||dataList.isEmpty()){
                finishLoadMore(false)
            }else{
                if (hasNext) {
                    finishLoadMore(true)
                    setEnableLoadMore(true)
                } else {
                    //无下一页
                    finishLoadMoreWithNoMoreData()
                    setEnableLoadMore(false)
                }
                adapter.addData(dataList)
            }
        }
    }

    private fun finishRefresh(success: Boolean) {
        if (enableRefresh) {
            smartLayout.finishRefresh(success)
        }
    }

    private fun finishLoadMore(success: Boolean) {
        if (enableLoadMore) {
            smartLayout.finishLoadMore(success)
        }
    }

    private fun finishLoadMoreWithNoMoreData() {
        if (enableLoadMore) {
            smartLayout.finishLoadMoreWithNoMoreData()
        }
    }

    private fun setEnableLoadMore(enable: Boolean) {
        if (enableLoadMore) {
            smartLayout.setEnableLoadMore(enable)
        }
    }

    private fun setEnableRefresh(enable: Boolean) {
        if (enableRefresh) {
            smartLayout.setEnableRefresh(enable)
        }
    }
}

