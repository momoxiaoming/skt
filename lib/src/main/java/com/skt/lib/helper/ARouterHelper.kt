package com.skt.lib.helper

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.mm.ext.arouter
import com.skt.lib.ARouterPath

/**
 * ARouterHelper
 *
 * @author mmxm
 * @date 2021/6/17 20:32
 */
fun Context.startFragment(fragmentPath: String) {
    startFragment(ARouterPath.ACTIVITY_CONTAINER, fragmentPath)
}

fun Context.startFragment(fragmentPath: String, block: (Postcard) -> Unit) {
    startFragment(ARouterPath.ACTIVITY_CONTAINER, fragmentPath, block)
}

fun Context.startTitleFragment(fragmentPath: String) {
    startFragment(ARouterPath.ACTIVITY_CONTAINER_TITLE, fragmentPath)
}

fun Context.startTitleFragment(fragmentPath: String, block: (Postcard) -> Unit) {
    startFragment(ARouterPath.ACTIVITY_CONTAINER_TITLE, fragmentPath, block)
}

fun Context.startFullFragment(fragmentPath: String) {
    startFragment(ARouterPath.ACTIVITY_FULL_CONTAINER, fragmentPath)
}

fun Context.startFullFragment(fragmentPath: String, block: (Postcard) -> Unit) {
    startFragment(ARouterPath.ACTIVITY_FULL_CONTAINER, fragmentPath, block)
}

/**
 * 通过Activity容器，跳转Activity
 *
 * @param activityPath activity路径
 * @param fragmentPath fragment路径
 */
fun Context.startFragment(activityPath: String, fragmentPath: String) {
    arouter().build(activityPath)
        .withString(ARouterPath.EXTRA_FRAGMENT_PATH, fragmentPath)
        .navigation(this)
}

fun Context.startFragment(activityPath: String, fragmentPath: String, block: (Postcard) -> Unit) {
    val postcard = arouter().build(activityPath)
        .withString(ARouterPath.EXTRA_FRAGMENT_PATH, fragmentPath)
    block(postcard)
    postcard.navigation(this)
}