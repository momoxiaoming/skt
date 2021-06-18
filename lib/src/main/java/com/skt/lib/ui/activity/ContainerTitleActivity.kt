package com.skt.lib.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.skt.lib.ARouterPath
import com.skt.lib.R
import com.skt.lib.databinding.LibActivityTitleContainerBinding

/**
 * ContainerActivity
 *
 * @author mmxm
 * @date 2021/6/17 11:52
 */
@Route(path = ARouterPath.ACTIVITY_CONTAINER_TITLE)
open class ContainerTitleActivity : ContainerActivity() {

    companion object {
        const val TAG = "ContainerTitleActivity"
    }

    lateinit var mBinding :LibActivityTitleContainerBinding

    override fun onInternalCreate(savedInstanceState: Bundle?) {
        super.onInternalCreate(savedInstanceState)
//        SystemUiUtil.immersiveSystemUi(window) //沉浸式状态栏,
        mBinding=DataBindingUtil.setContentView(this,R.layout.lib_activity_title_container)

        mBinding.headerView.headerToolbar.apply {
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun getContainerId(): Int {
        return mBinding.container.id
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        mBinding.headerView.headerToolbar.title=title
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
        mBinding.headerView.headerToolbar.setTitle(titleId)

    }
}