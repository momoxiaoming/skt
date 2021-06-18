package com.skt.lib.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color

/**
 * GodViewBaseImpl
 *
 * @author mmxm
 * @date 2021/6/18 17:44
 */
class GodViewBaseImpl : GodViewImpl {


    override fun initialize(
        godView: GodView.GodViewDelegate,
        context: Context,
        backgroundColorPair: Pair<ColorStateList?, ColorStateList?>,
        radius: FloatArray,
        elevation: Float,
        maxElevation: Float
    ) {

        val first = RoundRectDrawable(backgroundColorPair.first?:ColorStateList.valueOf(Color.TRANSPARENT), radius)
        val second = RoundRectDrawable(backgroundColorPair.second?:ColorStateList.valueOf(Color.TRANSPARENT), radius)
        //设置背景view
        godView.setBackgroundDrawable(Pair(first, second))
    }

    override fun setBackgroundColor(godView: GodView.GodViewDelegate, color: ColorStateList) {

    }
}