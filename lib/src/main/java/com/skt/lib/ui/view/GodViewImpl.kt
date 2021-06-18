package com.skt.lib.ui.view

import android.content.Context
import android.content.res.ColorStateList

/**
 * GodViewImpl
 *
 * @author mmxm
 * @date 2021/6/18 17:44
 */
interface GodViewImpl {

    fun initialize(
        godView: GodView.GodViewDelegate, context: Context, backgroundColorPair: Pair<ColorStateList?,ColorStateList?>,
        radius: FloatArray, elevation: Float, maxElevation: Float
    )

    /**
     * 设置背景色
     * @param godView GodViewDelegate
     * @param color ColorStateList
     */
    fun setBackgroundColor(godView: GodView.GodViewDelegate, color: ColorStateList)



}