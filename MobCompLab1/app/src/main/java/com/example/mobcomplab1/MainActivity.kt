package com.example.mobcomplab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClick(view: View) {
            Toast.makeText(this@MainActivity, "Well hello there :)", Toast.LENGTH_SHORT).show()
    }
}
