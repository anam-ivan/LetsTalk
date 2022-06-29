package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R
import com.ivan.letstalk.databinding.ActivityVerifyOtpactivityBinding

class VerifyOTPActivity : AppCompatActivity() {
    lateinit var binding: ActivityVerifyOtpactivityBinding
    private lateinit var btnVerifyOTP: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_verify_otpactivity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otpactivity)
        btnVerifyOTP = findViewById(R.id.btn_verify_otp)
        binding.otpBox.otpValue.observe(this) {
            it?.let {
                if (it.length == 6) {
                    // Toast.makeText(applicationContext,it.length.toString(),Toast.LENGTH_SHORT).show()
                    Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnVerifyOTP.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}