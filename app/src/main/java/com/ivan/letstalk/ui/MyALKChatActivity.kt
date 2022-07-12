package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.ivan.letstalk.R

class MyALKChatActivity : AppCompatActivity() {
    private lateinit var tvArchived : AppCompatTextView
    private lateinit var rrFirst : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_alkchat)
        tvArchived = findViewById(R.id.tv_archived)
        rrFirst = findViewById(R.id.rr_first)
        rrFirst.setOnClickListener{

        }
    }
}