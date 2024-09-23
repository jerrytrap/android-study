package ch2_widget_drawable.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.challenge04)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val editText: EditText = findViewById(R.id.editText)
        val wordCount: TextView = findViewById(R.id.textView_word)
        val sendButton: Button = findViewById(R.id.button_send)

        editText.addTextChangedListener {
            val length = it?.length ?: 0
            wordCount.text = "$length / 80 바이트"
        }

        sendButton.setOnClickListener {
            val text = editText.text
            if (text.isNotEmpty()) {
                showToast(editText.text.toString())
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}