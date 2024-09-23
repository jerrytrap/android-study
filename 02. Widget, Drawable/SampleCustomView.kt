package com.sample.doitandroid

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class SampleCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    private var onClickListener: OnClickListener? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> return false
            MotionEvent.ACTION_UP -> {
                performClick()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        if (onClickListener != null) {
            onClickListener?.onClick(this)
            return true
        }
        return super.performClick()
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener;
    }

    interface OnClickListener {
        fun onClick(v: View);
    }
}