package com.ivan.letstalk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.AlkSideEffectsAdapter
import com.ivan.letstalk.model.AlkSideEffectsModel

class DashboardActivity : AppCompatActivity() {
    private val alkSideEffectsModel = ArrayList<AlkSideEffectsModel>()
    private lateinit var alkSideEffectsAdapter: AlkSideEffectsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
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
}