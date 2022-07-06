package com.ivan.letstalk.ui

import `in`.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.DummyAdapter
import com.ivan.letstalk.helper.AlphabetItem
import com.ivan.letstalk.model.SideEffectsModel
import java.util.*

open class KnowYourSideEffectsActivity : AppCompatActivity() {
    private lateinit var rvSideEffects: IndexFastScrollRecyclerView
    private var mDataArray: List<String>? = null
    private var mAlphabetItems: List<AlphabetItem>? = null

    var movieList: List<SideEffectsModel>? = null
    var dummyArray: ArrayList<String>? = null

    private val sideEffectsModel = ArrayList<SideEffectsModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_know_your_side_effects)
        rvSideEffects = findViewById(R.id.rv_side_effects)
        initialiseData()
        initialiseUI()

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }
        // knowYourSideEffectsAdapter = KnowYourSideEffectsAdapter(sideEffectsModel)
        /*rvSideEffects.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            true
        )
        rvSideEffects.itemAnimator = DefaultItemAnimator()
        rvSideEffects.adapter = knowYourSideEffectsAdapter
        initSideEffects()*/
    }

    /*private fun initSideEffects() {
        var movie = SideEffectsModel("Nausea","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        sideEffectsModel.add(movie)
        movie = SideEffectsModel("Nasopharyngitis","A common viral infection of the nose and throat. In contrast to the flu, a common cold can be caused by many different types of viruses. The condition is generally harmless and symptoms usually resolve within two weeks.","Symptoms: Runny Nose, Sneezing & Congestion")
        sideEffectsModel.add(movie)
        knowYourSideEffectsAdapter.notifyDataSetChanged()
    }*/
    protected open fun initialiseData() {
        movieList = ArrayList()
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("A","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("B","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("C","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("D","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("E","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("F","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("G","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        (movieList as ArrayList<SideEffectsModel>).add(
            SideEffectsModel("H","Nausea is an uneasiness of the stomach that often comes before vomiting. Vomiting is the forcible voluntary or involuntary emptying (\"throwing up\") of stomach contents through the mouth.","Symptoms: Vomiting")
        )
        dummyArray = ArrayList<String>()
        for (i in (movieList as ArrayList<SideEffectsModel>).indices) {
            (movieList as ArrayList<SideEffectsModel>)[i].getAlkTitle()?.let { (dummyArray as ArrayList<String>).add(it) }
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
        rvSideEffects.layoutManager = LinearLayoutManager(this)
        rvSideEffects.adapter = DummyAdapter(mDataArray)
        rvSideEffects.setIndexTextSize(12)
        rvSideEffects.setIndexBarColor("#33334c")
        rvSideEffects.setIndexBarCornerRadius(0)
        rvSideEffects.setIndexBarTransparentValue(0.4.toFloat())
        /*rvSideEffects.setIndexbarTopMargin(60)
        rvSideEffects.setIndexbarBottomMargin(100)
        rvSideEffects.setIndexbarHorizontalMargin(20)*/
        rvSideEffects.setPreviewPadding(0)
        rvSideEffects.setIndexBarTextColor("#FFFFFF")
        rvSideEffects.setPreviewTextSize(60)
        rvSideEffects.setPreviewColor("#33334c")
        rvSideEffects.setPreviewTextColor("#FFFFFF")
        rvSideEffects.setPreviewTransparentValue(0.6f)
        rvSideEffects.setIndexBarVisibility(true)
        rvSideEffects.setIndexBarStrokeVisibility(true)
        rvSideEffects.setIndexBarStrokeWidth(1)
        rvSideEffects.setIndexBarStrokeColor("#000000")
        rvSideEffects.setIndexbarHighLightTextColor("#33334c")
        rvSideEffects.setIndexBarHighLightTextVisibility(true)
        Objects.requireNonNull<RecyclerView.LayoutManager>(rvSideEffects.layoutManager)
            .scrollToPosition(0)
    }
}