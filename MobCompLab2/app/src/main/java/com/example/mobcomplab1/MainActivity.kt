package com.example.mobcomplab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabOpened = false

        fab.setOnClickListener {
            if (!fabOpened) {
                fabOpened = true
                floatingMap.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                floatingTimer.animate().translationY(-resources.getDimension(R.dimen.standard_116))
            } else {
                fabOpened = false
                floatingMap.animate().translationY(0f)
                floatingTimer.animate().translationY(0f)
            }
        }
        floatingTimer.setOnClickListener {
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }
        floatingMap.setOnClickListener {
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }
    }
}