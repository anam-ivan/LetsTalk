package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivan.letstalk.R

class SetNewReminderActivity : AppCompatActivity() {
    var languages = arrayOf("Java", "PHP", "Kotlin", "Javascript", "Python", "Swift")
    val NEW_SPINNER_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_new_reminder)
    }
}