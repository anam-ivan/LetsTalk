package com.ivan.letstalk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.FaqAdapter
import com.ivan.letstalk.model.FaqModel
import java.util.ArrayList

class FaqActivity : AppCompatActivity() {
    var rvFaqs: RecyclerView? = null
    var faqsList: List<FaqModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        rvFaqs = findViewById(R.id.rv_faqs)
        initData()
        initRecyclerView()
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }
    }
    private fun initRecyclerView() {
        val faqAdapter = FaqAdapter(faqsList!!)
        rvFaqs!!.layoutManager = LinearLayoutManager(this)
        rvFaqs!!.adapter = faqAdapter
    }

    private fun initData() {
        faqsList = ArrayList()
        (faqsList as ArrayList<FaqModel>).add(
            FaqModel(
                "Can non-smokers get lung cancer?",
                "Yes. Smoking is the leading risk factor for lung cancer. But lung cancer also occurs in people who have never smoked or who did not have prolonged exposure to secondhand smoke. Other risks include exposure to asbestos, radon and other environmental chemicals. Non-smokers account for about 10 percent of lung cancer patients, and they have a higher incidence of mutations that may be treated with targeted therapies."
            )
        )
        (faqsList as ArrayList<FaqModel>).add(
            FaqModel(
                "How common is lung cancer in women?",
                "Yes. Smoking is the leading risk factor for lung cancer. But lung cancer also occurs in people who have never smoked or who did not have prolonged exposure to secondhand smoke. Other risks include exposure to asbestos, radon and other environmental chemicals. Non-smokers account for about 10 percent of lung cancer patients, and they have a higher incidence of mutations that may be treated with targeted therapies."
            )
        )
        (faqsList as ArrayList<FaqModel>).add(
            FaqModel(
                "What innovations are helping the fight against lung cancer?",
                "Yes. Smoking is the leading risk factor for lung cancer. But lung cancer also occurs in people who have never smoked or who did not have prolonged exposure to secondhand smoke. Other risks include exposure to asbestos, radon and other environmental chemicals. Non-smokers account for about 10 percent of lung cancer patients, and they have a higher incidence of mutations that may be treated with targeted therapies."
            )
        )
        (faqsList as ArrayList<FaqModel>).add(
            FaqModel(
                "Is chemotherapy the only treatment option for my lung cancer?",
                "Yes. Smoking is the leading risk factor for lung cancer. But lung cancer also occurs in people who have never smoked or who did not have prolonged exposure to secondhand smoke. Other risks include exposure to asbestos, radon and other environmental chemicals. Non-smokers account for about 10 percent of lung cancer patients, and they have a higher incidence of mutations that may be treated with targeted therapies."
            )
        )
        (faqsList as ArrayList<FaqModel>).add(
            FaqModel(
                "What treatment-related side effects should I expect?",
                "Yes. Smoking is the leading risk factor for lung cancer. But lung cancer also occurs in people who have never smoked or who did not have prolonged exposure to secondhand smoke. Other risks include exposure to asbestos, radon and other environmental chemicals. Non-smokers account for about 10 percent of lung cancer patients, and they have a higher incidence of mutations that may be treated with targeted therapies."
            )
        )
        (faqsList as ArrayList<FaqModel>).add(
            FaqModel(
                "Should I think about taking part in a clinical trial?",
                "Yes. Smoking is the leading risk factor for lung cancer. But lung cancer also occurs in people who have never smoked or who did not have prolonged exposure to secondhand smoke. Other risks include exposure to asbestos, radon and other environmental chemicals. Non-smokers account for about 10 percent of lung cancer patients, and they have a higher incidence of mutations that may be treated with targeted therapies."
            )
        )
    }
}