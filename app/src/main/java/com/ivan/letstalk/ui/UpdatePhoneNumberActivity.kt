package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R

class UpdatePhoneNumberActivity : AppCompatActivity() {
    private lateinit var tvCancel: TextView
    private lateinit var btnUpdate: MaterialButton
    private lateinit var edtMobile: AppCompatEditText
    private lateinit var llMobile: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_phone_number)
        tvCancel = findViewById(R.id.tv_cancel)
        btnUpdate = findViewById(R.id.btn_update)
        edtMobile = findViewById(R.id.edt_mobile)
        llMobile = findViewById(R.id.ll_mobile)
        tvCancel.paint?.isUnderlineText = true
        tvCancel.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
            startActivity(intent)
        }
        btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateMobileConfirmationActivity::class.java)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
            startActivity(intent)
        }

        edtMobile.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                llMobile.setBackgroundResource(R.drawable.edit_text_border_focused)
            } else {
                llMobile.setBackgroundResource(R.drawable.edit_text_border)
            }

        }
    }
}