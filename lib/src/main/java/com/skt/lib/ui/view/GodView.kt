package com.skt.lib.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mm.kit.common.R

/**
 *
 * cardView进阶版,支持单独圆角,背景,阴影等设置
 *
 * @author mmxm
 * @date 2021/5/31 10:43
 */
@SuppressLint("CustomViewStyleable", "Recycle", "ResourceAsColor")
class GodView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    companion object {
        private const val TAG = "GodView"
        private val COLOR_BACKGROUND_ATTR = intArrayOf(android.R.attr.colorBackground)
        private val IMPL: GodViewImpl = GodViewBaseImpl()
        fun log(msg: String) {
            Log.d(TAG, msg)
        }
    }

    private val godViewDelegate = GodViewDelegate()
    private var selBgDrawable: Drawable?=null
    private var norBgDrawable: Drawable?=null

    /**
     * 圆角,分别是上左,上右,下左,下右
     *
     */
    private var cornerRadiusArray: FloatArray = FloatArray(4)


    init {
        //获取属性
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.GodView) //名称必须和类名相同

        // There isn't one set, so we'll compute one based on the theme
        var backgroundColorPair: Pair<ColorStateList?, ColorStateList?>?

        if (typedArray.hasValue(R.styleable.GodView_godCornerRadius)) {
            //全部圆角
            val radius = typedArray.getDimension(R.styleable.GodView_godCornerRadius, 0f)
            cornerRadiusArray[0] = radius
            cornerRadiusArray[1] = radius
            cornerRadiusArray[2] = radius
            cornerRadiusArray[3] = radius
        } else {
            cornerRadiusArray[0] =
                typedArray.getDimension(R.styleable.GodView_godTopAndLeftCornerRadius, 0f)
            cornerRadiusArray[1] =
                typedArray.getDimension(R.styleable.GodView_godTopAndRightCornerRadius, 0f)
            cornerRadiusArray[2] =
                typedArray.getDimension(R.styleable.GodView_godBottomAndLeftCornerRadius, 0f)
            cornerRadiusArray[3] =
                typedArray.getDimension(R.styleable.GodView_godBottomAndRightCornerRadius, 0f)
        }

        if (typedArray.hasValue(R.styleable.GodView_godBackgroundColor)) {
            val colorList = typedArray.getColorStateList(R.styleable.GodView_godBackgroundColor)
            backgroundColorPair = Pair(colorList, colorList)
        } else {


            val aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR)
            val themeColorBackground = aa.getColor(0, 0)
            aa.recycle() //回收系统BackgroundColor
            val selBgColor = typedArray.getColorStateList(
                R.styleable.GodView_godSelBackgroundColor,
            )
            val norBgColor = typedArray.getColorStateList(
                R.styleable.GodView_godNorBackgroundColor,
            )
            backgroundColorPair = Pair(selBgColor, norBgColor)
        }

        IMPL.initialize(godViewDelegate, context,backgroundColorPair,cornerRadiusArray,0f,0f)


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    @SuppressLint("ClickableViewAccessibility")
    fun initListener() {
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    background = selBgDrawable
                }
                MotionEvent.ACTION_UP -> {
                    background = norBgDrawable
                }
            }
            false
        }
    }

    /**
     * godView代理
     */
    inner class GodViewDelegate {

        fun setBackgroundDrawable(drawablePair: Pair<Drawable,Drawable>){
            norBgDrawable=drawablePair.first
            selBgDrawable=drawablePair.second
            initListener()
        }

    }

}