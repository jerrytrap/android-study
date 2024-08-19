package ch2_widget_drawable.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.challenge03)

        val topImage: ImageView = findViewById(R.id.imageView_top)
        val bottomImage: ImageView = findViewById(R.id.imageView_bottom)
        val upButton: Button = findViewById(R.id.button_up)
        val downButton: Button = findViewById(R.id.button_down)

        upButton.setOnClickListener {
            topImage.setImageResource(R.drawable.beach)
            bottomImage.setImageDrawable(null)
        }

        downButton.setOnClickListener {
            topImage.setImageDrawable(null)
            bottomImage.setImageResource(R.drawable.beach)
        }
    }
}