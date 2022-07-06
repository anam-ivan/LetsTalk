package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.AlkSideEffectsAdapter
import com.ivan.letstalk.adapter.MyDocumentsAdapter
import com.ivan.letstalk.model.AlkSideEffectsModel
import com.ivan.letstalk.model.MyDocumentModel

class MyDocumentsActivity : AppCompatActivity() {
    private val myDocumentModel = ArrayList<MyDocumentModel>()
    private lateinit var myDocumentsAdapter: MyDocumentsAdapter
    private lateinit var rvMyDocuments: RecyclerView
    private lateinit var btnUploadDoc: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_documents)
        rvMyDocuments = findViewById(R.id.rv_my_documents)
        btnUploadDoc = findViewById(R.id.btn_upload_doc)
        btnUploadDoc.setOnClickListener {
            val uploadDocumentFragment =
                UploadDocumentFragment()
            uploadDocumentFragment.show(supportFragmentManager, "ddd")
        }
        myDocumentsAdapter = MyDocumentsAdapter(myDocumentModel)
        rvMyDocuments.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            true
        )
        rvMyDocuments.itemAnimator = DefaultItemAnimator()
        rvMyDocuments.adapter = myDocumentsAdapter
        initAllDocs()

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }
    }

    private fun initAllDocs() {
        var movie = MyDocumentModel("Blood test prescription by Dr. Ramesh Kr. Sharma","Prescription")
        myDocumentModel.add(movie)
        movie = MyDocumentModel("Blood test prescription by Dr. Ramesh Kr. Sharma","Hospital Bill")
        myDocumentModel.add(movie)
        movie = MyDocumentModel("Blood test prescription by Dr. Ramesh Kr. Sharma","Other")
        myDocumentModel.add(movie)
        myDocumentsAdapter.notifyDataSetChanged()
    }
}