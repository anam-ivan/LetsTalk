package com.ivan.letstalk.ui

import `in`.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.DummyAdapter
import com.ivan.letstalk.helper.AlphabetItem
import com.ivan.letstalk.model.MedicalTermsModel
import java.util.*

class GlossaryActivity : AppCompatActivity() {
    private lateinit var tvMedicalTerms: AppCompatTextView
    private lateinit var rvMedicalTerms: IndexFastScrollRecyclerView
    private var mDataArray: List<String>? = null
    private var mAlphabetItems: List<AlphabetItem>? = null

    var movieList: List<MedicalTermsModel>? = null
    var dummyArray: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary)
        rvMedicalTerms = findViewById(R.id.rv_medical_terms)
        tvMedicalTerms = findViewById(R.id.tv_medical_terms)
        tvMedicalTerms.paint?.isUnderlineText = true
        tvMedicalTerms.setTextColor(ContextCompat.getColor(this, R.color.blue))
        initialiseData()
        initialiseUI()
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }
    }

    protected fun initialiseData() {
        movieList = ArrayList()
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("ALS","Advanced life support.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("Bl wk","Blood work")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("BMI","Body mass index, a measure of body fat based on height and weight.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("BMI","Blood pressure.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("CPR","Cardiopulmonary resuscitation, a life-saving technique that’s also called mouth-to-mouth resuscitation.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("C-spine","Cervical spine.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("DNR","Do not resuscitate, a medical order indicating providers should not perform CPR.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("ED/ER","Emergency department or emergency room.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("EKG","Electrocardiogram, a way of monitoring the heart and testing for problems.")
        )
        (movieList as ArrayList<MedicalTermsModel>).add(
            MedicalTermsModel("Rx","Prescription, usually for medication but can also signify another treatment.")
        )
        dummyArray = ArrayList<String>()
        for (i in (movieList as ArrayList<MedicalTermsModel>).indices) {
            (movieList as ArrayList<MedicalTermsModel>)[i].getTitle()?.let { (dummyArray as ArrayList<String>).add(it) }
            // Toast.makeText(this, dummyArray.toString() + "", Toast.LENGTH_SHORT).show()
            Log.d("dummyArray", dummyArray.toString() + "")
        }
        //Recycler view full data
        //mDataArray = DataHelper.getAlphabetData();

        //Recycler view not full data
        // mDataArray = NotFullDataHelper.getAlphabetNotFullData();
        mDataArray = dummyArray


        // 123
        //Alphabet fast scroller data
        mAlphabetItems = ArrayList()
        val strAlphabets: MutableList<String> = ArrayList()
        for (i in (mDataArray as ArrayList<String>).indices) {
            val name = (mDataArray as ArrayList<String>)[i]
            if (name == null || name.trim { it <= ' ' }.isEmpty()) continue
            val word = name.substring(0, 1)
            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word)
                (mAlphabetItems as ArrayList<AlphabetItem>).add(AlphabetItem(i, word, false))
            }
        }
    }
    private fun initialiseUI() {
        rvMedicalTerms.layoutManager = LinearLayoutManager(this)
        rvMedicalTerms.adapter = DummyAdapter(mDataArray)
        rvMedicalTerms.setIndexTextSize(12)
        rvMedicalTerms.setIndexBarColor("#33334c")
        rvMedicalTerms.setIndexBarCornerRadius(0)
        rvMedicalTerms.setIndexBarTransparentValue(0.4.toFloat())
        /*rvSideEffects.setIndexbarTopMargin(60)
        rvSideEffects.setIndexbarBottomMargin(100)
        rvSideEffects.setIndexbarHorizontalMargin(20)*/
        rvMedicalTerms.setPreviewPadding(0)
        rvMedicalTerms.setIndexBarTextColor("#FFFFFF")
        rvMedicalTerms.setPreviewTextSize(60)
        rvMedicalTerms.setPreviewColor("#33334c")
        rvMedicalTerms.setPreviewTextColor("#FFFFFF")
        rvMedicalTerms.setPreviewTransparentValue(0.6f)
        rvMedicalTerms.setIndexBarVisibility(true)
        rvMedicalTerms.setIndexBarStrokeVisibility(true)
        rvMedicalTerms.setIndexBarStrokeWidth(1)
        rvMedicalTerms.setIndexBarStrokeColor("#000000")
        rvMedicalTerms.setIndexbarHighLightTextColor("#33334c")
        rvMedicalTerms.setIndexBarHighLightTextVisibility(true)
        Objects.requireNonNull<RecyclerView.LayoutManager>(rvMedicalTerms.layoutManager)
            .scrollToPosition(0)
    }
}