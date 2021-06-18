package com.skt.lib.ui.view

import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * 透明圆角view
 *
 * @author mmxm
 * @date 2021/6/18 19:18
 */
class RoundRectDrawable(var colorStateList: ColorStateList, var radius: FloatArray) : Drawable() {

    private val mPaint: Paint by lazy {
        val paint=Paint()
        mPaint.color = colorStateList.getColorForState(state, colorStateList.defaultColor)
        paint
    }


    override fun draw(canvas: Canvas) {

    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}