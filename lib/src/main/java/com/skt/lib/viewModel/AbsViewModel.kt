package com.skt.lib.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.base.viewmodel.BaseViewModel

/**
 * 抽象的功能viewModel
 * 1。支持页面自动关闭
 * 2. 支持加载窗的显示与关闭
 *
 * @author mmxm
 * @date 2021/4/14 15:57
 */
data class LoadingItem(var isShow: Boolean, var msg: String? = null)

abstract class AbsViewModel : BaseViewModel() {

    /**
     * 是否结束，true时，界面退出
     */
    val pageFinishData = MutableLiveData<Boolean>()

    /**
     * 是否显示加载窗
     */
    val loadingData = MutableLiveData<LoadingItem>()

    val scope by lazy {
        viewModelScope
    }

    /**
     * 显示加载窗
     * @param msg String
     */
    fun showLoading(msg: String? = null) {
        loadingData.value = LoadingItem(true, msg)
    }

    /**
     * 关闭加载窗
     */
    fun hideLoading() {
        loadingData.value = LoadingItem(false)
    }
}