package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ivan.letstalk.R

class MyHealthVitals : AppCompatActivity() {
    private lateinit var fabIcon : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_health_vitals)
        fabIcon = findViewById(R.id.add_fab);
        fabIcon.setOnClickListener {

        }
    }
}