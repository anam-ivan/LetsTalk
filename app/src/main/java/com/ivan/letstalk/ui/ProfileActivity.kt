package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat
import com.ivan.letstalk.R

class ProfileActivity : AppCompatActivity() {
    private lateinit var llHealthVitals : LinearLayoutCompat
    private lateinit var llSideEffects : LinearLayoutCompat
    private lateinit var llMedicine : LinearLayoutCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        llHealthVitals = findViewById(R.id.ll_health_vitals)
        llSideEffects = findViewById(R.id.ll_side_effects)
        llMedicine = findViewById(R.id.ll_medicine)

        llHealthVitals.setOnClickListener {
            navigateToMyHealthVitals()
        }

        llSideEffects.setOnClickListener {
            navigateToSideEffects()
        }

        llMedicine.setOnClickListener {
            navigateToMedicineReminder()
        }
    }
    private fun navigateToMyHealthVitals() {
        val intent = Intent(this, MyHealthVitals::class.java)
        startActivity(intent)
    }

    private fun navigateToSideEffects() {
        val intent = Intent(this, MySideEffectHistory::class.java)
        startActivity(intent)
    }

    private fun navigateToMedicineReminder() {
        val intent = Intent(this, MedicineReminderActivity::class.java)
        startActivity(intent)
    }
}