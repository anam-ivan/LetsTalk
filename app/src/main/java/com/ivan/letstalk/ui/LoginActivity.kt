package com.ivan.letstalk.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R

class LoginActivity : AppCompatActivity() {
    private lateinit var tvHelp: TextView
    private lateinit var tvTermsConditions: TextView
    private lateinit var tvUpdateNumber: TextView
    private lateinit var btnLogin: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvHelp = findViewById(R.id.tv_help)
        tvTermsConditions = findViewById(R.id.tv_terms_conditions)
        tvUpdateNumber = findViewById(R.id.tv_update_number)
        btnLogin = findViewById(R.id.btn_login)
        tvHelp.paint?.isUnderlineText = true
        tvTermsConditions.paint?.isUnderlineText = true
        tvUpdateNumber.paint?.isUnderlineText = true

        tvUpdateNumber.setOnClickListener{
            val intent = Intent(this, VerifyOTPActivity::class.java)
            startActivity(intent)
        }

        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
        /*toast("PackageName = " + info.packageName + "\nVersionCode = "
                + info.versionCode + "\nVersionName = "
                + info.versionName + "\nPermissions = " + info.permissions)*/
    }
}