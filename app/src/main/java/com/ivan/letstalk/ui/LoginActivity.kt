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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
        tvHelp.paint?.isUnderlineText = true
        tvTermsConditions.paint?.isUnderlineText = true
        tvUpdateNumber.paint?.isUnderlineText = true
        updateNumber.paint?.isUnderlineText = true

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
}