package com.skt.lib.ui.list.state

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.skt.lib.ui.list.state.IStateView

/**
 * DefaultLoadingView
 *
 * @author mmxm
 * @date 2021/6/23 14:51
 */
class DefaultErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleRes), IStateView {

    lateinit var textView: TextView
    lateinit var errorBtn: TextView

    init {
        initView()
    }


    private fun initView() {
        orientation= VERTICAL
        gravity=Gravity.CENTER
        textView = TextView(context)
        textView.text="网络错误"
        errorBtn=Button(context)
        errorBtn.text="点击重试"
        addView(textView,LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT))
        addView(errorBtn,LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT))

    }




    override fun setTipMsg(msg: String) {
        textView.text = msg
    }


    override fun addStateViewListener(listener: (String) -> Unit) {
        errorBtn.setOnClickListener {listener.invoke("")}
    }
    override fun getStateView(): View {
        return this
    }
}