package com.skt.lib.ui.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.skt.lib.ui.list.state.IStateView

/**
 * AbsAdapter
 *
 * @author mmxm
 * @date 2021/6/23 11:59
 */
abstract class AbsStateAdapter<T, VH : BaseViewHolder>(layoutResId: Int) : BaseQuickAdapter<T, VH>(
    layoutResId
) {
    /**
     * 定义三总状态视图
     */
    private var loading_View: IStateView? = null
    private var error_View: IStateView? = null
    private var empty_view: IStateView? = null

    /**
     * 设置状态视图
     */
    fun setStateView(
        loadView: IStateView? = null,
        errorView: IStateView? = null,
        emptyView: IStateView? = null
    ) {
        loading_View = loadView
        error_View = errorView
        empty_view = emptyView
    }

    fun getErrorView():IStateView?{
        return error_View
    }
    fun getEmptyView():IStateView?{
        return empty_view
    }
    fun getLoadingView():IStateView?{
        return loading_View
    }

    internal fun showErrorView() {
        error_View?.let {
            data.clear() //需将数据清除后方可显示空视图
            setEmptyView(it.getStateView());
        }
    }

    internal fun showLoadingView() {
        loading_View?.let {
            data.clear() //需将数据清除后方可显示空视图
            setEmptyView(it.getStateView());
        }
    }

    internal fun showEmptyView() {
        empty_view?.let {
            data.clear() //需将数据清除后方可显示空视图
            setEmptyView(it.getStateView());
        }
    }

}