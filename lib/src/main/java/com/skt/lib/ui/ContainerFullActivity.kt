package com.skt.lib.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.mm.kit.common.util.SystemUiUtil
import com.skt.lib.ARouterPath

/**
 * ContainerActivity
 *
 * @author mmxm
 * @date 2021/6/17 11:52
 */
@Route(path = ARouterPath.ACTIVITY_FULL_CONTAINER)
open class ContainerFullActivity : ContainerActivity() {

    companion object {
        const val TAG = "ContainerFullActivity"
    }

    override fun onInternalCreate(savedInstanceState: Bundle?) {
        super.onInternalCreate(savedInstanceState)
        SystemUiUtil.hideSystemUI(window)
    }
}