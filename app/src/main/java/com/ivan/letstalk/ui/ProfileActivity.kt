package com.ivan.letstalk.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ivan.letstalk.R
import com.ivan.letstalk.api.ApiHelper
import com.ivan.letstalk.api.RetrofitBuilder
import com.ivan.letstalk.databinding.ActivityProfileBinding
import com.ivan.letstalk.helper.Status
import com.ivan.letstalk.helper.ViewModelFactory
import com.ivan.letstalk.viewModel.LoginViewModel

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: LoginViewModel

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
        // setContentView(R.layout.activity_profile)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setupViewModel()
        setupPatientProfileObservers()

        /*llHealthVitals = findViewById(R.id.ll_health_vitals)
        llSideEffects = findViewById(R.id.ll_side_effects)
        llMedicine = findViewById(R.id.ll_medicine)
        llDocument = findViewById(R.id.ll_document)
        rlHealthCard = findViewById(R.id.rl_health_card)*/

        binding.llHealthVitals.setOnClickListener {
            navigateToMyHealthVitals()
        }

        binding.llSideEffects.setOnClickListener {
            navigateToSideEffects()
        }

        binding.llMedicine.setOnClickListener {
            navigateToMedicineReminder()
        }

        binding.llDocument.setOnClickListener {
            navigateToMyDocuments()
        }

        binding.rlHealthCard.setOnClickListener {
            navigateToMyHealthCard()
        }

       /* bottomNavHome =  findViewById(R.id.bottom_nav_home)
        bottomNavChat =  findViewById(R.id.bottom_nav_chat)
        bottomNavPerson =  findViewById(R.id.bottom_nav_person)
        bottomNavHamburger =  findViewById(R.id.bottom_nav_hamburger)

        bottomIvHome = findViewById(R.id.bottom_dot_home)
        bottomIvChat = findViewById(R.id.bottom_dot_chat)
        bottomIvPerson = findViewById(R.id.bottom_dot_person)
        bottomIvHamburger = findViewById(R.id.bottom_dot_hamburger)*/

        binding.bottomDotPerson.visibility = View.VISIBLE

        // ivHome = findViewById(R.id.iv_home)
        binding.ivHome.setBackgroundResource(R.drawable.ic_home)

        // ivPerson = findViewById(R.id.iv_person)
        binding.ivPerson.visibility = View.GONE
        // ivPerson.setBackgroundResource(R.drawable.ic_person_fill)

        // ivPersonFill = findViewById(R.id.iv_person_fill)
        binding.ivPersonFill.visibility = View.VISIBLE
        binding.ivPerson.setBackgroundResource(R.drawable.ic_person_fill)

        binding.bottomNavHome.setOnClickListener{
            /*bottomIvPerson.visibility =  View.INVISIBLE
            bottomIvHamburger.visibility =  View.INVISIBLE
            bottomIvChat.visibility =  View.INVISIBLE
            bottomIvHome.visibility =  View.VISIBLE*/
            navigateToDashboard()
        }

        binding.bottomNavChat.setOnClickListener{
            binding.bottomDotHome.visibility =  View.INVISIBLE
            binding.bottomDotPerson.visibility =  View.INVISIBLE
            binding.bottomDotHamburger.visibility =  View.INVISIBLE
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

        binding.bottomNavHamburger.setOnClickListener{
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
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToSideEffects() {
        val intent = Intent(this, MySideEffectHistory::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToMedicineReminder() {
        val intent = Intent(this, MedicineReminderActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToMyDocuments() {
        val intent = Intent(this, MyDocumentsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToMyHealthCard() {
        val intent = Intent(this, MyHealthCard::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToChat() {
        val intent = Intent(this, ALKChatActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    override fun onRestart() {
        super.onRestart()
        binding.bottomDotPerson.visibility = View.VISIBLE
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(LoginViewModel::class.java)
    }

    private fun setupPatientProfileObservers() {
        viewModel.patientProfileDetails().observe(this, Observer { it ->
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                            Toast.makeText(this, it.body()?.message, Toast.LENGTH_LONG).show()
                            binding.tvPatientName.text = it.body()?.data!![0].basicDetails!!.name
                            binding.tvPhone.text = it.body()?.data!![0].basicDetails!!.phoneNumber
                            binding.tvPhone.text = it.body()?.data!![0].basicDetails!!.phoneNumber
                            binding.tvDiagnosis.text = it.body()?.data!![0].basicDetails!!.dateOfDiagnosis
                            Glide.with(this)
                                .load("http://letstalk.dev13.ivantechnology.in/".plus(it.body()?.data!![0].basicDetails!!.image))
                                .into(binding.ivProfile)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        // Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                    }
                }

            }
        })
    }
}