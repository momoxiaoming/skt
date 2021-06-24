package com.skt.lib.ui.list.state

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.skt.lib.ui.list.state.IStateView
import org.jetbrains.anko.backgroundColor

/**
 * DefaultLoadingView
 *
 * @author mmxm
 * @date 2021/6/23 14:51
 */
class DefaultLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleRes), IStateView {

    private lateinit var textView: TextView

    init {

        initView()
    }

    private fun initView() {
        layoutParams=LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        gravity = Gravity.CENTER
        textView = TextView(context)
        textView.text="加载中"
        addView(textView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
    }

    override fun setTipMsg(msg: String) {
        textView.text = msg
    }

    override fun addStateViewListener(listener: (String) -> Unit) {
        textView.setOnClickListener {listener.invoke("")}
    }



    override fun getStateView(): View {
        return this
    }
}