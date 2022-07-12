package com.ivan.letstalk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.CustomAdapter


class AddNewVitalFragment : BottomSheetDialogFragment() {

    var fruits = arrayOf(
        "Blood Pressure (SYS)",
        "Blood Pressure (DIA)",
        "Blood Sugar (PP)",
        "Blood Sugar (Fasting)",
        "Oxygen Saturation (SpO2)"
    )
    private var images = intArrayOf(
        R.drawable.ic_blood_pressure,
        R.drawable.ic_documents,
        R.drawable.ic_blood_sugar,
        R.drawable.ic_blood_sugar,
        R.drawable.ic_oxygen
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_add_new_vital, container, false)
        val view: View = inflater.inflate(R.layout.fragment_add_new_vital, container, false)
        var ivDropdown = view.findViewById<ImageView>(R.id.iv_dropdown)
        val spin = view.findViewById<Spinner>(R.id.spCategory)
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                /*Toast.makeText(
                    requireActivity(),
                    "You Select Position: " + position + " " + fruits[position],
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val customAdapter =
            CustomAdapter(requireActivity(), images, fruits)
        spin.adapter = customAdapter

        ivDropdown.setOnClickListener {
            spin.performClick()
        }
        return view
    }
}