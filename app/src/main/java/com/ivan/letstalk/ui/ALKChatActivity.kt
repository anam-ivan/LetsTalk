package com.ivan.letstalk.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.ivan.letstalk.R

class ALKChatActivity : AppCompatActivity() {
    private lateinit var cgExistingSideEffects: ChipGroup
    private lateinit var rrMyChats: RelativeLayout
    private var existingSideEffectsList = arrayOf(
        "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea", "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea"
    )
    private var existingSideEffectsChipItems = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alkchat)
        rrMyChats = findViewById(R.id.rr_my_chats)
        cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
        initExistingSideEffectsData()
        rrMyChats.setOnClickListener{
            navigateToMyChats()
        }
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
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

    private fun getChip(text: String): Chip {
        val chip = Chip(this)
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.my_chip))
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50f,
            resources.displayMetrics
        ).toInt()
        chip.setChipBackgroundColorResource(R.color.divider_color)
        chip.setTextColor(ContextCompat.getColor(this, R.color.black))
        chip.isCloseIconVisible = false
        chip.isCheckedIconVisible = false
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        chip.text = text
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = getColorStateList(R.color.chat_answer_select)
                }
                chip.setTextColor(ContextCompat.getColor(this, R.color.white))
                chip.isChecked = true
                existingSideEffectsChipItems.add(chip.text.toString())
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = getColorStateList(R.color.divider_color)
                }
                chip.setTextColor(ContextCompat.getColor(this, R.color.black))
                chip.isChecked = false
                existingSideEffectsChipItems.remove(chip.text.toString())
            }
        }
        return chip
    }

    private fun navigateToMyChats() {
        val intent = Intent(this, MyALKChatActivity::class.java)
        startActivity(intent)
    }
}