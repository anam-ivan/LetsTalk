package com.ivan.letstalk.ui

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.ivan.letstalk.R

class MySideEffectHistory : AppCompatActivity() {
    private lateinit var tvExistingSideEffects: AppCompatTextView
    private lateinit var tvPreviousSideEffects: AppCompatTextView
    private lateinit var tvSideEffect: AppCompatTextView

    private lateinit var cgExistingSideEffects: ChipGroup
    private lateinit var cgPreviousSideEffects: ChipGroup

    private var existingSideEffectsList = arrayOf(
        "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea", "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea"
    )

    var previousSideEffectsList = arrayOf(
        "Dyspepsia", "Nausea", "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea"
    )
    // get selected language list
    private var existingSideEffectsChipItems = ArrayList<String>()
    private var previousSideEffectsChipItems = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_side_effect_history)
        tvSideEffect = findViewById(R.id.tv_side_effect)
        cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
        cgPreviousSideEffects = findViewById(R.id.chip_previous_side_effects)

        tvExistingSideEffects=  findViewById(R.id.tv_existing_side_effects)
        tvPreviousSideEffects=  findViewById(R.id.tv_preview_side_effects)

        cgExistingSideEffects.visibility = View.VISIBLE
        tvExistingSideEffects.paint?.isUnderlineText = true
        tvExistingSideEffects.setTextColor(ContextCompat.getColor(this, R.color.blue))

        initExistingSideEffectsData()

        initPreviousSideEffectsData()

        tvExistingSideEffects.setOnClickListener {
            tvExistingSideEffects.paint?.isUnderlineText = true
            tvExistingSideEffects.setTextColor(ContextCompat.getColor(this, R.color.blue))
            cgExistingSideEffects.visibility = View.VISIBLE
            cgPreviousSideEffects.visibility = View.GONE

            tvPreviousSideEffects.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            tvPreviousSideEffects.paint?.isUnderlineText = false
        }

        tvPreviousSideEffects.setOnClickListener {
            tvExistingSideEffects.paint?.isUnderlineText = false
            tvExistingSideEffects.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            cgExistingSideEffects.visibility = View.GONE
            cgPreviousSideEffects.visibility = View.VISIBLE

            tvPreviousSideEffects.setTextColor(ContextCompat.getColor(this, R.color.blue))
            tvPreviousSideEffects.paint?.isUnderlineText = true
        }
    }

    private fun initExistingSideEffectsData() {
        for (i in existingSideEffectsList.indices) {
            cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
            val entryChip2: Chip = getChip(existingSideEffectsList[i])
            entryChip2.id = i

            //set default selected language
            //entryChip2.setChecked(true);
            cgExistingSideEffects.addView(entryChip2)
        }
    }

    private fun initPreviousSideEffectsData() {
        for (i in previousSideEffectsList.indices) {
            cgPreviousSideEffects = findViewById(R.id.chip_previous_side_effects)
            val entryChip2: Chip = getChip(previousSideEffectsList[i])
            entryChip2.id = i

            //set default selected language
            //entryChip2.setChecked(true);
            cgPreviousSideEffects.addView(entryChip2)
        }
    }

    private fun getChip(text: String): Chip {
        val chip = Chip(this)
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.my_chip))
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50f,
            resources.displayMetrics
        ).toInt()
        chip.setChipBackgroundColorResource(R.color.white)
        chip.setTextColor(ContextCompat.getColor(this, R.color.black))
        chip.isCloseIconVisible = false
        chip.isCheckedIconVisible = false
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        chip.text = text
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = getColorStateList(R.color.blue)
                    tvSideEffect.text = buttonView.text.toString()
                    // Toast.makeText(this@MySideEffectHistory, buttonView.text.toString(), Toast.LENGTH_SHORT).show()
                }
                chip.setTextColor(ContextCompat.getColor(this, R.color.white))
                chip.isChecked = true
                existingSideEffectsChipItems.add(chip.text.toString())
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = getColorStateList(R.color.white)
                }
                chip.setTextColor(ContextCompat.getColor(this, R.color.black))
                chip.isChecked = false
                existingSideEffectsChipItems.remove(chip.text.toString())
            }
        }
        return chip
    }
}