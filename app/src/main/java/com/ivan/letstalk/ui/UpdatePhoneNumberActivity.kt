package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R

class UpdatePhoneNumberActivity : AppCompatActivity() {
    private lateinit var tvCancel: TextView
    private lateinit var btnUpdate: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_phone_number)
        tvCancel = findViewById(R.id.tv_cancel)
        btnUpdate = findViewById(R.id.btn_update)
        tvCancel.paint?.isUnderlineText = true
        tvCancel.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateMobileConfirmationActivity::class.java)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
            startActivity(intent)
        }
    }
}