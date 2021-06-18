package com.mc.cpyr.kit.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.kit.base.app.BaseAppDelegate
import com.mc.cpyr.kit.util.Debuger
import com.mm.kit.common.http.OkApi
import com.mm.kit.common.log.VLog
import z.hol.gq.GsonQuick
import z.hol.gq.GsonQuickLogger
import java.io.Console

/**
 * 所有application代理都默认走DefaultAppDelegate
 *
 * @author mmxm
 * @date 2021/6/17 16:29
 */
open abstract class DefaultAppDelegate(app: Application) : BaseAppDelegate(app) {

    /**
     * 日志的前缀
     */
    open val logPrefix: String = "VI"

    override fun onCreate() {
        super.onCreate()

    }

    override fun onCreateEnv(processName: String, remoteProc: Boolean) {
        super.onCreateEnv(processName, remoteProc)
        if (remoteProc) return
        if (Debuger.debugOn) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(app)
        OkApi.init(app.applicationContext)
        OkApi.DEBUG = Debuger.debugOn

        GsonQuick.setLogger { p0, p1 ->
            VLog.w(p0 ?: "null json")
            VLog.printErrStackTrace(p1, "gson parse error")
        }
    }

}