package com.ivan.letstalk.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.ivan.letstalk.R

class AddHealthVitals : AppCompatActivity() {
    private lateinit var addNewVital: LinearLayoutCompat
    private lateinit var llOxygenSaturation: LinearLayoutCompat
    private lateinit var tvBodyWeight: AppCompatTextView
    private lateinit var tvBloodPressure: AppCompatTextView
    private lateinit var tvBloodSugar: AppCompatTextView
    private lateinit var tvBloodSugarFasting: AppCompatTextView

    private lateinit var ivBodyWeightDecrease: ImageView
    private lateinit var ivBodyWeightIncrease: ImageView

    private lateinit var ivBloodPressureDecrease: ImageView
    private lateinit var ivBloodPressureIncrease: ImageView

    private lateinit var ivBloodSugarDecrease: ImageView
    private lateinit var ivBloodSugarIncrease: ImageView

    private lateinit var ivBloodSugarFastingDecrease: ImageView
    private lateinit var ivBloodSugarFastingIncrease: ImageView

    var bodyWeight = 62
    var bloodPressure = 152
    var bloodSugar = 180
    var bloodSugarFasting = 108

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_health_vitals)

        ivBodyWeightDecrease = findViewById(R.id.iv_body_weight_decrease)
        ivBodyWeightIncrease = findViewById(R.id.iv_body_weight_increase)

        ivBloodPressureDecrease = findViewById(R.id.iv_blood_pressure_decrease)
        ivBloodPressureIncrease = findViewById(R.id.iv_blood_pressure_increase)

        ivBloodSugarDecrease = findViewById(R.id.iv_blood_sugar_decrease)
        ivBloodSugarIncrease = findViewById(R.id.iv_blood_sugar_increase)

        ivBloodSugarFastingDecrease = findViewById(R.id.iv_blood_sugar_fasting_decrease)
        ivBloodSugarFastingIncrease = findViewById(R.id.iv_blood_sugar_fasting_increase)

        tvBodyWeight = findViewById(R.id.tv_body_weight)
        tvBloodPressure = findViewById(R.id.tv_blood_pressure)
        tvBloodSugar = findViewById(R.id.tv_blood_sugar)
        tvBloodSugarFasting = findViewById(R.id.tv_blood_sugar_fasting)

        tvBodyWeight.text = bodyWeight.toString()
        tvBloodPressure.text = bloodPressure.toString()
        tvBloodSugar.text = bloodSugar.toString()
        tvBloodSugarFasting.text = bloodSugarFasting.toString()

        addNewVital = findViewById(R.id.ll_add_new_vital)
        llOxygenSaturation = findViewById(R.id.ll_oxygen_saturation)
        val isFromEdit = intent.getBooleanExtra("isFromEdit", false)
        // Toast.makeText(this@AddHealthVitals, isFromEdit.toString(), Toast.LENGTH_SHORT).show()
        if (isFromEdit) {
            llOxygenSaturation.visibility = View.VISIBLE
        }

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        ivBodyWeightIncrease.setOnClickListener {
            bodyWeight += 1
            tvBodyWeight.text = bodyWeight.toString()
        }

        ivBodyWeightDecrease.setOnClickListener {
            bodyWeight -= 1
            tvBodyWeight.text = bodyWeight.toString()
        }

        ivBloodPressureIncrease.setOnClickListener {
            bloodPressure += 1
            tvBloodPressure.text = bloodPressure.toString()
        }

        ivBloodPressureDecrease.setOnClickListener {
            bloodPressure -= 1
            tvBloodPressure.text = bloodPressure.toString()
        }

        ivBloodSugarIncrease.setOnClickListener {
            bloodSugar += 1
            tvBloodSugar.text = bloodSugar.toString()
        }

        ivBloodSugarDecrease.setOnClickListener {
            bloodSugar -= 1
            tvBloodSugar.text = bloodSugar.toString()
        }

        ivBloodSugarFastingIncrease.setOnClickListener {
            bloodSugarFasting += 1
            tvBloodSugarFasting.text = bloodSugarFasting.toString()
        }

        ivBloodSugarFastingDecrease.setOnClickListener {
            bloodSugarFasting -= 1
            tvBloodSugarFasting.text = bloodSugarFasting.toString()
        }

        addNewVital.setOnClickListener {
            val addNewVitalFragment =
                AddNewVitalFragment()
            addNewVitalFragment.show(supportFragmentManager, "ddd")
        }
    }
}