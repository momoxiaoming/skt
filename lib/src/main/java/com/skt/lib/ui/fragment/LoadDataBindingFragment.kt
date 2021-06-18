package com.skt.lib.ui.fragment

import android.app.Dialog
import androidx.databinding.ViewDataBinding
import com.kit.base.fragment.DataBindingFragment
import com.mm.ext.IE
import com.skt.lib.ui.dialog.DefaultLoadDialog
import com.skt.lib.ui.dialog.ILoadDialog
import com.skt.lib.viewModel.AbsViewModel
import com.skt.lib.viewModel.LoadingItem

/**
 * 带加载状态的fragment
 * @author mmxm
 * @date 2021/6/18 12:13
 */
abstract class LoadDataBindingFragment<T : ViewDataBinding, VM : AbsViewModel> :
    DataBindingFragment<T, VM>() {

    private val loadDialog: ILoadDialog by lazy {
        createDialog()
    }

    /**
     * 创建加载dialog
     * @return Dialog
     */
    open fun createDialog(): ILoadDialog {
        return DefaultLoadDialog(requireActivity())
    }

    private fun updateDialog(loadingItem: LoadingItem) {
        loadingItem.isShow.IE({
            //设置图形
            loadDialog.setMessage(loadingItem.msg)
            loadDialog.iShow()
        }, { loadDialog.iDismiss() })

    }

    override fun initObserver() {
        mModel.loadingData.observe(this, {
            updateDialog(it)
        })
        mModel.pageFinishData.observe(this, {
            if (it) {
                requireActivity().finish()
            }
        })
    }

}