package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.ivan.letstalk.R

class MenuActivity : AppCompatActivity() {
    private lateinit var  bottomNavHome : LinearLayoutCompat
    private lateinit var  bottomNavChat : LinearLayoutCompat
    private lateinit var  bottomNavPerson : LinearLayoutCompat
    private lateinit var  bottomNavHamburger : LinearLayoutCompat

    private lateinit var  bottomIvHome : AppCompatImageView
    private lateinit var  bottomIvChat : AppCompatImageView
    private lateinit var  bottomIvPerson : AppCompatImageView
    private lateinit var  bottomIvHamburger : AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        bottomNavHome =  findViewById(R.id.bottom_nav_home)
        bottomNavChat =  findViewById(R.id.bottom_nav_chat)
        bottomNavPerson =  findViewById(R.id.bottom_nav_person)
        bottomNavHamburger =  findViewById(R.id.bottom_nav_hamburger)

        bottomIvHome = findViewById(R.id.bottom_dot_home)
        bottomIvChat = findViewById(R.id.bottom_dot_chat)
        bottomIvPerson = findViewById(R.id.bottom_dot_person)
        bottomIvHamburger = findViewById(R.id.bottom_dot_hamburger)

        bottomIvHamburger.visibility = View.VISIBLE

        bottomNavHome.setOnClickListener{
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvHome.visibility =  View.VISIBLE
            navigateToDashboard()
        }

        bottomNavChat.setOnClickListener{
            bottomIvHome.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.VISIBLE
            navigateToChat()
        }

        bottomNavPerson.setOnClickListener{
            bottomIvHome.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.VISIBLE
            navigateToProfile()
        }

        bottomNavHamburger.setOnClickListener{
            bottomIvHome.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.VISIBLE
            navigateToMenu()
        }
    }

    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToChat() {
        val intent = Intent(this, ALKChatActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}