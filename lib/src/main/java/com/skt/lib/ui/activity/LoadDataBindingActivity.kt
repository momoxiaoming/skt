package com.skt.lib.ui.activity

import androidx.databinding.ViewDataBinding
import com.kit.base.activity.DataBindingActivity
import com.mm.ext.IE
import com.skt.lib.ui.dialog.DefaultLoadDialog
import com.skt.lib.ui.dialog.ILoadDialog
import com.skt.lib.viewModel.AbsViewModel
import com.skt.lib.viewModel.LoadingItem

/**
 * LoadDataBindingActivity
 *
 * @author mmxm
 * @date 2021/6/18 16:38
 */
abstract class LoadDataBindingActivity<T : ViewDataBinding, VM : AbsViewModel> :
    DataBindingActivity<T, VM>() {
    private val loadDialog: ILoadDialog by lazy {
        createDialog()
    }
    /**
     * 创建加载dialog
     * @return Dialog
     */
    open fun createDialog(): ILoadDialog {
        return DefaultLoadDialog(this)
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
               finish()
            }
        })
    }

}