package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.ivan.letstalk.R

class MyALKChatActivity : AppCompatActivity() {
    private lateinit var tvArchived : AppCompatTextView
    private lateinit var tvCompleted : AppCompatTextView
    private lateinit var rrFirst : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_alkchat)
        tvArchived = findViewById(R.id.tv_archived)
        tvCompleted = findViewById(R.id.tv_completed)
        tvCompleted.setTextColor(ContextCompat.getColor(this, R.color.blue))
        tvCompleted.paint?.isUnderlineText = true
        rrFirst = findViewById(R.id.rr_first)
        rrFirst.setOnClickListener{
            val singleChatFragment =
                SingleChatFragment()
            singleChatFragment.show(supportFragmentManager, "ddd")
        }
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }
    }
}