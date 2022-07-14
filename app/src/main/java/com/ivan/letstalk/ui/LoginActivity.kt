package com.ivan.letstalk.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R


class LoginActivity : AppCompatActivity() {
    private lateinit var tvHelp: TextView
    private lateinit var tvTermsConditions: TextView
    private lateinit var tvUpdateNumber: TextView
    private lateinit var btnLogin: MaterialButton
    private lateinit var etPhone: AppCompatEditText
    private lateinit var llPhone: LinearLayoutCompat
    private lateinit var rrPhone: RelativeLayout
    private lateinit var ivLoginError: ImageView
    private lateinit var updateNumber: TextView
    private lateinit var rrErrorLayout: RelativeLayout
    private lateinit var ivDropdown: ImageView
    private lateinit var rrOthers: RelativeLayout
    private lateinit var tvCountryCode: TextView
    private lateinit var tvOthers: TextView
    private lateinit var tvEnterYourEmail: TextView
    private lateinit var etEmail: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvEnterYourEmail = findViewById(R.id.tv_enter_your_email)
        etEmail = findViewById(R.id.et_email)
        tvCountryCode = findViewById(R.id.tv_country_code)
        tvOthers = findViewById(R.id.tv_others)
        rrOthers = findViewById(R.id.rr_others)
        updateNumber = findViewById(R.id.tv_error_third_text)
        rrErrorLayout = findViewById(R.id.rr_error_layout)
        llPhone = findViewById(R.id.llPhone)
        etPhone = findViewById(R.id.et_phone)
        rrPhone = findViewById(R.id.rr_phone)
        tvHelp = findViewById(R.id.tv_help)
        ivLoginError = findViewById(R.id.iv_login_error)
        tvTermsConditions = findViewById(R.id.tv_terms_conditions)
        tvUpdateNumber = findViewById(R.id.tv_update_number)
        btnLogin = findViewById(R.id.btn_login)
        ivDropdown = findViewById(R.id.iv_dropdown)
        tvHelp.paint?.isUnderlineText = true
        tvTermsConditions.paint?.isUnderlineText = true
        tvUpdateNumber.paint?.isUnderlineText = true
        updateNumber.paint?.isUnderlineText = true

        ivDropdown.setOnClickListener {
            if (tvCountryCode.text.toString() == "Others") {
                ivDropdown.rotation = 180f
                tvCountryCode.text = "+91"
                tvCountryCode.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.greyy
                    )
                )
                rrPhone.setBackgroundResource((R.drawable.others_back))
                rrOthers.visibility = View.VISIBLE
                /*etPhone.hint = "Please enter mobile number along with country code"
                etPhone.letterSpacing = 0.2F*/
                tvEnterYourEmail.visibility = View.GONE
                etEmail.visibility = View.GONE
                // ivDropdown.isClickable = false
                ivDropdown.setColorFilter(ContextCompat.getColor(this, R.color.greyy), android.graphics.PorterDuff.Mode.SRC_IN)
            } else {
                ivDropdown.rotation = 180f
                rrPhone.setBackgroundResource((R.drawable.others_back))
                tvCountryCode.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.greyy
                    )
                )
                ivDropdown.setColorFilter(
                    ContextCompat.getColor(this, R.color.greyy),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                rrOthers.visibility = View.VISIBLE
            }
        }

        tvCountryCode.setOnClickListener {
            rrOthers.visibility = View.GONE
            ivDropdown.rotation = 360f
        }

        /*ivDropdown.setOnClickListener {
            rrOthers.visibility = View.GONE
            ivDropdown.rotation = 180f
        }*/

        rrOthers.setOnClickListener {
            // ivDropdown.isClickable = true
            rrOthers.visibility = View.GONE
            tvCountryCode.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            tvCountryCode.text = "Others"
            ivDropdown.rotation = 360f
            etPhone.hint = "Please enter mobile number along with country code"
            etPhone.letterSpacing = 0.1F
            rrPhone.setBackgroundResource((R.drawable.country_code_back))
            tvEnterYourEmail.visibility = View.VISIBLE
            etEmail.visibility = View.VISIBLE
            ivDropdown.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        }

        tvUpdateNumber.setOnClickListener{
            val intent = Intent(this, UpdatePhoneNumberActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            if (etPhone.text.toString() == "8888888888") {
                llPhone.setBackgroundResource(R.drawable.edit_text_error_border)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    rrPhone.setBackgroundResource(R.drawable.country_code_error_back)
                }
                rrErrorLayout.visibility = View.VISIBLE
                ivLoginError.visibility = View.VISIBLE
            } else {
                navigateToVerifyOTP()
            }
        }
        etPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                llPhone.setBackgroundResource(R.drawable.edit_text_border_focused)
            } else {
                llPhone.setBackgroundResource(R.drawable.edit_text_border)
            }

        }
        updateNumber.setOnClickListener {
            val intent = Intent(this, UpdatePhoneNumberActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
        }

        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val strText: String = s.toString()
                if (strText.isEmpty()) {
                    llPhone.setBackgroundResource(R.drawable.edit_text_border)
                    rrPhone.setBackgroundResource(R.drawable.country_code_back)
                    if (ivLoginError.visibility == View.VISIBLE) {
                        ivLoginError.visibility = View.GONE
                    }
                    rrErrorLayout.visibility = View.GONE
                }
            }
        })

        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
        /*toast("PackageName = " + info.packageName + "\nVersionCode = "
                + info.versionCode + "\nVersionName = "
                + info.versionName + "\nPermissions = " + info.permissions)*/
    }

    private fun navigateToVerifyOTP() {
        val intent = Intent(this, VerifyOTPActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }
}