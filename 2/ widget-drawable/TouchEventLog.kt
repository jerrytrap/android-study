package com.sample.doitandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_sample)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("touch event", "Activity dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("touch event", "Activity onTouch")
        return super.onTouchEvent(event)
    }
}

class SampleLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("touch event", "Layout dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("touch event", "Layout interceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("touch event", "Layout onTouch")
        return super.onTouchEvent(event)
    }
}

class SampleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d("touch event", "View dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("touch event", "View onTouch")
        return super.onTouchEvent(event)
    }
}