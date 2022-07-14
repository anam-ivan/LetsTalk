package com.ivan.letstalk.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.AlkSideEffectsAdapter
import com.ivan.letstalk.model.AlkSideEffectsModel
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import smartdevelop.ir.eram.showcaseviewlib.config.PointerType
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener

class DashboardActivity : AppCompatActivity() {
    private lateinit var tvSeeAll: TextView
    private val alkSideEffectsModel = ArrayList<AlkSideEffectsModel>()
    private lateinit var alkSideEffectsAdapter: AlkSideEffectsAdapter
    private lateinit var  llSideEffects : LinearLayoutCompat
    private lateinit var  llGlossary : LinearLayoutCompat
    private lateinit var  rrDoctorSay : RelativeLayout

    private lateinit var ivHome: AppCompatImageView
    private lateinit var ivNotifications: AppCompatImageView
    private lateinit var ivChat: AppCompatImageView

    private lateinit var  bottomNavHome : LinearLayoutCompat
    private lateinit var  bottomNavChat : LinearLayoutCompat
    private lateinit var  bottomNavPerson : LinearLayoutCompat
    private lateinit var  bottomNavHamburger : LinearLayoutCompat

    private lateinit var  bottomIvHome : AppCompatImageView
    private lateinit var  bottomIvChat : AppCompatImageView
    private lateinit var  bottomIvPerson : AppCompatImageView
    private lateinit var  bottomIvHamburger : AppCompatImageView

    private lateinit var  btnStartChat : MaterialButton

    var view1: View? = null
    var view2: View? = null
    var view3: View? = null
    private lateinit var mGuideView: GuideView
    private var builder: GuideView.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        view1 = findViewById(R.id.tv_username)
        view2 = findViewById(R.id.rr_doctor_say)
        view3 = findViewById(R.id.ll_side_effects)

        // rrDoctorSay = findViewById(R.id.rr_doctor_say)
        ivChat = findViewById(R.id.iv_chat)

        btnStartChat = findViewById(R.id.btn_start_chat)
        ivNotifications = findViewById(R.id.iv_notifications)

        bottomNavHome =  findViewById(R.id.bottom_nav_home)
        bottomNavChat =  findViewById(R.id.bottom_nav_chat)
        bottomNavPerson =  findViewById(R.id.bottom_nav_person)
        bottomNavHamburger =  findViewById(R.id.bottom_nav_hamburger)

        bottomIvHome = findViewById(R.id.bottom_dot_home)
        bottomIvChat = findViewById(R.id.bottom_dot_chat)
        bottomIvPerson = findViewById(R.id.bottom_dot_person)
        bottomIvHamburger = findViewById(R.id.bottom_dot_hamburger)

        ivHome = findViewById(R.id.iv_home)

        bottomIvHome.visibility = View.VISIBLE
        ivHome.setBackgroundResource(R.drawable.ic_home_fill)

        llSideEffects = findViewById(R.id.ll_side_effects)
        llGlossary = findViewById(R.id.ll_glossary)
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
            false
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

        llGlossary.setOnClickListener {
            val intent = Intent(this, GlossaryActivity::class.java)
            startActivity(intent)
        }

        /*bottomNavHome.setOnClickListener{
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvHome.visibility =  View.VISIBLE
            navigateToDashboard()
        }*/

        bottomNavChat.setOnClickListener{
            bottomIvHome.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            // bottomIvChat.visibility =  View.VISIBLE
            navigateToChat()
        }
        bottomNavPerson.setOnClickListener{
            /*bottomIvHome.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.VISIBLE*/
            navigateToProfile()
        }

        bottomNavHamburger.setOnClickListener{
            /*bottomIvHome.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.VISIBLE*/
            navigateToMenu()
        }

        ivNotifications.setOnClickListener {
            navigateToNotifications()
        }

        btnStartChat.setOnClickListener {
            navigateToChat()
        }

        /*builder = GuideView.Builder(this)
            .setTitle("Greetings")
            .setContentText("Here you can the greetings")
            .setGravity(Gravity.center)
            .setDismissType(DismissType.anywhere)
            .setPointerType(PointerType.arrow)
            .setTargetView(view1)
            .setGuideListener(GuideListener { view ->
                when (view.id) {
                    R.id.tv_username -> {
                        builder!!.setTargetView(view2).build()
                        builder!!.setTitle("Doctor Say")
                        builder!!.setContentText("Here you can find Doctor say")
                    }
                    R.id.rr_doctor_say -> {
                        builder!!.setTargetView(view3).build()
                        builder!!.setTitle("Glossary")
                        builder!!.setContentText("Here you can find Glossary")
                    }
                    R.id.ll_side_effects -> return@GuideListener
                }
                mGuideView = builder!!.build()
                mGuideView.show()
            })
        mGuideView = builder!!.build()
        mGuideView.show()*/
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

    private fun navigateToNotifications() {
        val intent = Intent(this, NotificationsActivity::class.java)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        bottomIvHome.visibility = View.VISIBLE
        bottomIvPerson.visibility =  View.INVISIBLE
        bottomIvHamburger.visibility =  View.INVISIBLE
        bottomIvChat.visibility =  View.INVISIBLE
    }
}