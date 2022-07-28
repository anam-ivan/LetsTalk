package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.AlkSideEffectsAdapter
import com.ivan.letstalk.adapter.MyHealthCardAdapter
import com.ivan.letstalk.api.ApiHelper
import com.ivan.letstalk.api.RetrofitBuilder
import com.ivan.letstalk.databinding.ActivityMyHealthCardBinding
import com.ivan.letstalk.databinding.ActivityProfileBinding
import com.ivan.letstalk.helper.Status
import com.ivan.letstalk.helper.ViewModelFactory
import com.ivan.letstalk.model.CommonModel
import com.ivan.letstalk.viewModel.LoginViewModel

class MyHealthCard : AppCompatActivity() {
    private lateinit var binding: ActivityMyHealthCardBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var myHealthCardAdapter: MyHealthCardAdapter
    private var newList = ArrayList<CommonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_health_card)
        setupViewModel()
        setupPatientProfileObservers()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
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
                            // Toast.makeText(this, it.body()?.message, Toast.LENGTH_LONG).show()
                            /*binding.tvPatientName.text = it.body()?.data!![0].basicDetails!!.name
                            binding.tvDob.text = it.body()?.data!![0].basicDetails!!.dob
                            binding.tvGender.text = it.body()?.data!![0].healthCard[4].valueDetails*/
                            // var newList = ArrayList<CommonModel>()
                            newList = ArrayList<CommonModel>()
                            if (it.body()?.data!![0].basicDetails != null) {
                                val basic = it.body()?.data!![0].basicDetails
                                newList.add(CommonModel("Name", basic?.name?: "", ""))
                                newList.add(CommonModel("Date of Birth", basic?.dob?: "", ""))
                            }
                            for (i in it.body()?.data!![0]?.healthCard!!.indices)
                                newList.add(
                                    CommonModel(
                                        it.body()?.data!![0]?.healthCard!![i].name!!,
                                        it.body()?.data!![0]?.healthCard!![i].value!!,
                                        it.body()?.data!![0]?.healthCard!![i].valueDetails!!)
                                )
                            Log.e("TAG", "setupPatientProfileObservers: ${Gson().toJson(newList)}")
                        }
                        myHealthCardAdapter = MyHealthCardAdapter(newList)
                        binding.rvHealthCardDetails.layoutManager = LinearLayoutManager(
                            this,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        binding.rvHealthCardDetails.itemAnimator = DefaultItemAnimator()
                        binding.rvHealthCardDetails.adapter = myHealthCardAdapter
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