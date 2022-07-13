package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import com.ivan.letstalk.R

class ProfileActivity : AppCompatActivity() {
    private lateinit var llHealthVitals : LinearLayoutCompat
    private lateinit var llSideEffects : LinearLayoutCompat
    private lateinit var llMedicine : LinearLayoutCompat
    private lateinit var llDocument : LinearLayoutCompat
    private lateinit var rlHealthCard : RelativeLayout

    private lateinit var  bottomNavHome : LinearLayoutCompat
    private lateinit var  bottomNavChat : LinearLayoutCompat
    private lateinit var  bottomNavPerson : LinearLayoutCompat
    private lateinit var  bottomNavHamburger : LinearLayoutCompat

    private lateinit var  bottomIvHome : AppCompatImageView
    private lateinit var  bottomIvChat : AppCompatImageView
    private lateinit var  bottomIvPerson : AppCompatImageView
    private lateinit var  bottomIvHamburger : AppCompatImageView

    private lateinit var ivPerson: AppCompatImageView
    private lateinit var ivPersonFill: AppCompatImageView
    private lateinit var ivHome: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        llHealthVitals = findViewById(R.id.ll_health_vitals)
        llSideEffects = findViewById(R.id.ll_side_effects)
        llMedicine = findViewById(R.id.ll_medicine)
        llDocument = findViewById(R.id.ll_document)
        rlHealthCard = findViewById(R.id.rl_health_card)

        llHealthVitals.setOnClickListener {
            navigateToMyHealthVitals()
        }

        llSideEffects.setOnClickListener {
            navigateToSideEffects()
        }

        llMedicine.setOnClickListener {
            navigateToMedicineReminder()
        }

        llDocument.setOnClickListener {
            navigateToMyDocuments()
        }

        rlHealthCard.setOnClickListener {
            navigateToMyHealthCard()
        }

        bottomNavHome =  findViewById(R.id.bottom_nav_home)
        bottomNavChat =  findViewById(R.id.bottom_nav_chat)
        bottomNavPerson =  findViewById(R.id.bottom_nav_person)
        bottomNavHamburger =  findViewById(R.id.bottom_nav_hamburger)

        bottomIvHome = findViewById(R.id.bottom_dot_home)
        bottomIvChat = findViewById(R.id.bottom_dot_chat)
        bottomIvPerson = findViewById(R.id.bottom_dot_person)
        bottomIvHamburger = findViewById(R.id.bottom_dot_hamburger)

        bottomIvPerson.visibility = View.VISIBLE

        ivHome = findViewById(R.id.iv_home)
        ivHome.setBackgroundResource(R.drawable.ic_home)

        ivPerson = findViewById(R.id.iv_person)
        ivPerson.visibility = View.GONE
        // ivPerson.setBackgroundResource(R.drawable.ic_person_fill)

        ivPersonFill = findViewById(R.id.iv_person_fill)
        ivPersonFill.visibility = View.VISIBLE
        ivPerson.setBackgroundResource(R.drawable.ic_person_fill)

        bottomNavHome.setOnClickListener{
            /*bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvHome.visibility =  View.VISIBLE*/
            navigateToDashboard()
        }

        bottomNavChat.setOnClickListener{
            bottomIvHome.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            // bottomIvChat.visibility =  View.VISIBLE
            navigateToChat()
        }

        /*bottomNavPerson.setOnClickListener{
            bottomIvHome.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.VISIBLE
            navigateToProfile()
        }*/

        bottomNavHamburger.setOnClickListener{
            /*bottomIvHome.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.VISIBLE*/
            navigateToMenu()
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

    private fun navigateToMyDocuments() {
        val intent = Intent(this, MyDocumentsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMyHealthCard() {
        val intent = Intent(this, MyHealthCard::class.java)
        startActivity(intent)
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

    override fun onRestart() {
        super.onRestart()
        bottomIvPerson.visibility = View.VISIBLE
    }
}