package com.ivan.letstalk.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.AlkSideEffectsAdapter
import com.ivan.letstalk.model.AlkSideEffectsModel

class DashboardActivity : AppCompatActivity() {
    private lateinit var tvSeeAll: TextView
    private val alkSideEffectsModel = ArrayList<AlkSideEffectsModel>()
    private lateinit var alkSideEffectsAdapter: AlkSideEffectsAdapter
    private lateinit var  llSideEffects : LinearLayoutCompat

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
        setContentView(R.layout.activity_dashboard)

        bottomNavHome =  findViewById(R.id.bottom_nav_home)
        bottomNavChat =  findViewById(R.id.bottom_nav_chat)
        bottomNavPerson =  findViewById(R.id.bottom_nav_person)
        bottomNavHamburger =  findViewById(R.id.bottom_nav_hamburger)

        bottomIvHome = findViewById(R.id.bottom_dot_home)
        bottomIvChat = findViewById(R.id.bottom_dot_chat)
        bottomIvPerson = findViewById(R.id.bottom_dot_person)
        bottomIvHamburger = findViewById(R.id.bottom_dot_hamburger)

        bottomIvHome.visibility = View.VISIBLE;

        llSideEffects = findViewById(R.id.ll_side_effects)
        tvSeeAll = findViewById(R.id.tv_see_all)
        tvSeeAll.paint?.isUnderlineText = true
        tvSeeAll.setOnClickListener {
            val intent = Intent(this, TopALKSideEffectsActivity::class.java)
            startActivity(intent)
        }
        val recyclerView: RecyclerView = findViewById(R.id.rv_alk)
        alkSideEffectsAdapter = AlkSideEffectsAdapter(alkSideEffectsModel)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            true
        )
        // val layoutManager = LinearLayoutManager(applicationContext)
        // recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = alkSideEffectsAdapter
        prepareMovieData()

        llSideEffects.setOnClickListener {
            val intent = Intent(this, KnowYourSideEffectsActivity::class.java)
            startActivity(intent)
        }

        bottomNavHome.setOnClickListener{
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvHome.visibility =  View.VISIBLE
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

    private fun prepareMovieData() {
        var movie = AlkSideEffectsModel("Manage Weight Loss")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkSideEffectsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkSideEffectsModel.add(movie)
        alkSideEffectsAdapter.notifyDataSetChanged()
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
}