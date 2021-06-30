package com.mc.cpyr.kit.ext

import com.mm.kit.common.log.VLog
import com.mm.kit.common.log.considerLog

/**
 * Vlog
 *
 * @author mmxm
 * @date 2021/6/3 15:54
 */
inline fun <reified T> T.logI(message: () -> Any?) {
    if (considerLog(VLog.LEVEL_DEBUG)) {
        VLog.scoped(T::class.java.simpleName).i(message()?.toString())
    }
}

/**
 * Vlog
 *
 * @author mmxm
 * @date 2021/6/3 15:54
 */

inline fun <reified T> T.logE(message: () -> Any?) {
    if (considerLog(VLog.LEVEL_DEBUG)) {
        VLog.scoped(T::class.java.simpleName).e( message()?.toString())
    }
}

