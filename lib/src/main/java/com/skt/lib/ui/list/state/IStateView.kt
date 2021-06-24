package com.skt.lib.ui.list.state

import android.view.View

/**
 *  状态视图接口,所有涉及stateAdapter的emptyView errorView, loadingView 都必须继承
 *
 * @author mmxm
 * @date 2021/6/23 14:46
 */
interface IStateView {

    /**
     * 设置提示语
     */
    fun setTipMsg(msg:String)

    /**
     * 添加元件点击事件
     */
    fun addStateViewListener( listener: (String)->Unit)

    /**
     * 获取当前状态view,一般是本身
     * @return View
     */
    fun getStateView():View
}

