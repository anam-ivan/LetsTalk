package com.ivan.letstalk.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.AlkMarkedSymptomsAdapter
import com.ivan.letstalk.adapter.AlkSideEffectsAdapter
import com.ivan.letstalk.model.AlkSideEffectsModel


class TopALKSideEffectsActivity : AppCompatActivity() {
    private lateinit var tvAllSymptoms : AppCompatTextView
    private lateinit var tvMarkedSymptoms : AppCompatTextView
    private val alkAllSymptomsModel = ArrayList<AlkSideEffectsModel>()
    private val alkMarkedSymptomsModel = ArrayList<AlkSideEffectsModel>()
    private lateinit var rvAlkAllSymptoms: RecyclerView
    private lateinit var rvAlkMarkedSymptoms: RecyclerView
    private lateinit var alkSideEffectsAdapter: AlkSideEffectsAdapter

    private lateinit var alkMarkedSymptomsAdapter: AlkMarkedSymptomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_alkside_effects)

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        tvAllSymptoms=  findViewById(R.id.tv_all_symptoms)
        tvMarkedSymptoms=  findViewById(R.id.tv_marked_symptoms)
        tvAllSymptoms.paint?.isUnderlineText = true
        tvAllSymptoms.setTextColor(ContextCompat.getColor(this, R.color.blue))

        rvAlkAllSymptoms = findViewById(R.id.rv_alk_all_symptoms)
        rvAlkAllSymptoms.visibility =  View.VISIBLE
        alkSideEffectsAdapter = AlkSideEffectsAdapter(alkAllSymptomsModel)
        rvAlkAllSymptoms.layoutManager = GridLayoutManager(this, 3)
        rvAlkAllSymptoms.itemAnimator = DefaultItemAnimator()
        rvAlkAllSymptoms.adapter = alkSideEffectsAdapter
        initAllSymptomsData()

        rvAlkMarkedSymptoms = findViewById(R.id.rv_alk_marked_symptoms)
        alkMarkedSymptomsAdapter = AlkMarkedSymptomsAdapter(alkMarkedSymptomsModel)
        rvAlkMarkedSymptoms.layoutManager = GridLayoutManager(this, 3)
        rvAlkMarkedSymptoms.itemAnimator = DefaultItemAnimator()
        rvAlkMarkedSymptoms.adapter = alkMarkedSymptomsAdapter
        initMarkedSymptomsData()


        tvMarkedSymptoms.setOnClickListener {
            tvAllSymptoms.paint?.isUnderlineText = false
            tvAllSymptoms.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))

            tvAllSymptoms.visibility = View.VISIBLE
            tvMarkedSymptoms.paint?.isUnderlineText = true
            tvMarkedSymptoms.setTextColor(ContextCompat.getColor(this, R.color.blue))

            rvAlkAllSymptoms.visibility = View.GONE
            rvAlkMarkedSymptoms.visibility = View.VISIBLE
        }

        tvAllSymptoms.setOnClickListener {
            tvAllSymptoms.paint?.isUnderlineText = true
            tvAllSymptoms.setTextColor(ContextCompat.getColor(this, R.color.blue))

            tvMarkedSymptoms.paint?.isUnderlineText = false
            tvMarkedSymptoms.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))

            rvAlkAllSymptoms.visibility = View.VISIBLE
            rvAlkMarkedSymptoms.visibility = View.GONE
        }
    }

    private fun initAllSymptomsData() {
        var movie = AlkSideEffectsModel("Manage Weight Loss")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkAllSymptomsModel.add(movie)
        /*movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkAllSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")*/
        alkSideEffectsAdapter.notifyDataSetChanged()
    }

    private fun initMarkedSymptomsData() {
        var movie = AlkSideEffectsModel("Manage Weight Loss")
        alkMarkedSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Having Persistent Cough ?")
        alkMarkedSymptomsModel.add(movie)
        movie = AlkSideEffectsModel("Feeling Weak & Tired ?")
        alkMarkedSymptomsModel.add(movie)
        alkMarkedSymptomsAdapter.notifyDataSetChanged()
    }
}