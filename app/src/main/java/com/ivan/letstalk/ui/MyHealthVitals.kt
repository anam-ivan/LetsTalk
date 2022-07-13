package com.ivan.letstalk.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ivan.letstalk.R

class MyHealthVitals : AppCompatActivity() {
    private lateinit var fabIcon : FloatingActionButton
    private lateinit var btnEdit : TextView
    private lateinit var btnDelete : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_health_vitals)
        btnEdit = findViewById(R.id.btn_edit)
        btnDelete = findViewById(R.id.btn_delete)
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }
        fabIcon = findViewById(R.id.add_fab);
        fabIcon.setOnClickListener {
            navigateToAddHealthVitals()
        }
        btnEdit.setOnClickListener {
            navigateToEditHeathVitals()
        }
        btnDelete.setOnClickListener {
            showHealthVitalDialog()
        }
    }
    private fun navigateToAddHealthVitals() {
        val intent = Intent(this, AddHealthVitals::class.java)
        startActivity(intent)
    }

    private fun navigateToEditHeathVitals() {
        val intent = Intent(this, AddHealthVitals::class.java)
        intent.putExtra("isFromEdit",true)
        startActivity(intent)
    }

    private fun showHealthVitalDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.my_health_vital_dialog)
        val btnCancel = dialog.findViewById(R.id.btnCancel) as TextView
        btnCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
        // val tvSecondPasswordTips = dialog.findViewById<TextView>(R.id.tv_second_password_tips)
    }
}