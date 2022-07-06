package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ivan.letstalk.R

class UpdatePhoneNumberActivity : AppCompatActivity() {
    private lateinit var tvCancel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_phone_number)
        tvCancel = findViewById(R.id.tv_cancel)
        tvCancel.paint?.isUnderlineText = true
        tvCancel.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}