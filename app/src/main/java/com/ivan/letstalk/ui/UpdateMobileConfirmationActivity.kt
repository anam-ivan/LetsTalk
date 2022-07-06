package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ivan.letstalk.R

class UpdateMobileConfirmationActivity : AppCompatActivity() {
    private lateinit var tvContactHelpline: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mobile_confirmation)
        tvContactHelpline = findViewById(R.id.tv_contact_helpline)
        tvContactHelpline.paint?.isUnderlineText = true
    }
}