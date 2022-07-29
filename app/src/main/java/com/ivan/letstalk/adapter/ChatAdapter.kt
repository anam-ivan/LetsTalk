package com.ivan.letstalk.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import com.ivan.letstalk.R
import com.ivan.letstalk.ui.ALKChatActivity
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class ChatAdapter(applicationContext: Context, private val inflater: LayoutInflater) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val messages: MutableList<JSONObject> = ArrayList()
    var context: Context = applicationContext

    private inner class PatientRequestHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageTxt: TextView
        var tvTimeStamp: TextView

        init {
            messageTxt = itemView.findViewById(R.id.tv_send)
            tvTimeStamp = itemView.findViewById(R.id.tv_time_stamp)
        }
    }

    private inner class PatientGreetingsMessageHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvGreetings: TextView
        var tvReceiveTimeStamp: TextView
        var cvHelp: MaterialCardView
        var cvGreetings: MaterialCardView
        var tvPatientHealthDetails: TextView
        var tvHealthDetails: TextView

        init {
            tvGreetings = itemView.findViewById(R.id.tv_greetings)
            tvReceiveTimeStamp = itemView.findViewById(R.id.tv_receive_time_stamp)
            cvHelp = itemView.findViewById(R.id.cv_help)
            cvGreetings = itemView.findViewById(R.id.cv_greetings)
            tvPatientHealthDetails = itemView.findViewById(R.id.tv_patient_health_details)
            tvHealthDetails = itemView.findViewById(R.id.tv_health_details)
        }
    }

    private inner class PatientIssueHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvSideEffects: TextView
        var tvEmergencyCare: TextView
        var tvMedicineRoutine: TextView
        var tvUpdateVitals: TextView

        init {
            tvSideEffects = itemView.findViewById(R.id.tv_side_effects)
            tvEmergencyCare = itemView.findViewById(R.id.tv_emergency_care)
            tvMedicineRoutine = itemView.findViewById(R.id.tv_medicine_routine)
            tvUpdateVitals = itemView.findViewById(R.id.tv_update_vitals)
        }
    }

    /*override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        try {
            return if (message.getBoolean("isSent")) {
                TYPE_MESSAGE_SENT
            } else {
                TYPE_MESSAGE_RECEIVED
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return -1
    }*/

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        try {
            if (message.getString("message_type").equals("greetings")) {
                TYPE_MESSAGE_GREETINGS
            } else if (message.getString("message_type").equals("patient_issue")){
                TYPE_PATIENT_ISSUE
            } else if (message.getString("message_type").equals("side_effects_list")){
                TYPE_SIDE_EFFECT_LIST
            } else if (message.getString("message_type").equals("patient_request")) {
                TYPE_PATIENT_REQUEST
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            TYPE_PATIENT_REQUEST -> {
                view = inflater.inflate(R.layout.chat_send_message, parent, false)
                return PatientRequestHolder(view)
            }
            TYPE_MESSAGE_GREETINGS -> {
                view = inflater.inflate(R.layout.chat_message_greetings_item, parent, false)
                return PatientGreetingsMessageHolder(view)
            }
            TYPE_PATIENT_ISSUE -> {
                view = inflater.inflate(R.layout.chat_patient_issue, parent, false)
                return PatientIssueHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
        /*view = inflater.inflate(R.layout.chat_send_message, parent, false)
        return PatientRequestHolder(view)*/
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun addItem(jsonObject: JSONObject) {
        messages.add(jsonObject)
        notifyDataSetChanged()
    }

    companion object {
        /*private const val TYPE_MESSAGE_SENT = 0
        private const val TYPE_MESSAGE_RECEIVED = 1*/
        private const val TYPE_PATIENT_REQUEST = 1
        private const val TYPE_MESSAGE_GREETINGS = 2
        private const val TYPE_PATIENT_ISSUE = 3
        private const val TYPE_SIDE_EFFECT_LIST = 4
    }

    /*@SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        try {
            if (message.getBoolean("isSent")) {
                val simpleDateFormat = SimpleDateFormat("hh:mm a")
                val currentDateAndTime: String = simpleDateFormat.format(Date())
                val messageHolder = holder as SentMessageHolder
                messageHolder.tvTimeStamp.text = currentDateAndTime
            } else {
                val messageHolder = holder as ReceivedMessageHolder
                val dataJsonObject = message.getJSONObject("data")
                val responseText = dataJsonObject.getJSONArray("response_text")
                val greetingsMsg = responseText.getJSONObject(0).getString("0")
                val patientHealthDetails = responseText.getJSONObject(1).getString("1")
                Log.d("patientHealthDetails",patientHealthDetails)
                val simpleDateFormat = SimpleDateFormat("hh:mm a")
                val currentDateAndTime: String = simpleDateFormat.format(Date())
                messageHolder.tvGreetings.text = Html.fromHtml(greetingsMsg)
                messageHolder.tvHealthDetails.text = Html.fromHtml(patientHealthDetails)
                messageHolder.tvReceiveTimeStamp.text = currentDateAndTime
                val option = dataJsonObject.getString("option")
                *//*Log.d("option",option)
                val anim: Animation = AnimationUtils.loadAnimation(
                    context,
                    android.R.anim.slide_out_right
                )
                anim.duration = 300
                messageHolder.rootView.startAnimation(anim)*//*

                *//*if (option.isNotEmpty() && option.toString() == "next&2&") {
                    messageHolder.cvHelp.visibility = View.VISIBLE
                    messageHolder.cvGreetings.visibility = View.GONE
                    messageHolder.tvReceiveTimeStamp.visibility = View.GONE
                    val dataJsonObject = message.getJSONObject("data")
                    val responseText = dataJsonObject.getJSONArray("response_text")
                    val patientHealthDetails = responseText.getJSONObject(0).getString("0")
                    messageHolder.tvPatientHealthDetails.text = patientHealthDetails
                }*//*
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }*/

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        // Log.d("message",Gson().toJson(message))
        try {
            if (message.getString("message_type").equals("patient_request")) {
                val simpleDateFormat = SimpleDateFormat("hh:mm a")
                val currentDateAndTime: String = simpleDateFormat.format(Date())
                val messageHolder = holder as PatientRequestHolder
                messageHolder.tvTimeStamp.text = currentDateAndTime
                /*val dataJsonObject = message.getJSONObject("data")
                val responseText = dataJsonObject.getJSONArray("user")*/
                if (message.getString("user").isNotEmpty())
                messageHolder.messageTxt.text = message.getString("user")
            } else if (message.getString("message_type").equals("greetings")) {
                Log.d("message",Gson().toJson(message))
                // val mHolder = holder as PatientGreetingsMessageHolder
                if (holder is PatientGreetingsMessageHolder) {
                    val dataJsonObject = message.getJSONObject("data")
                    val responseText = dataJsonObject.getJSONArray("response_text")
                    val greetingsMsg = responseText.getJSONObject(0).getString("0")
                    holder.tvGreetings.text = message.toString()
                }
            } else if (message.getString("message_type").equals("side_effects_list")) {
                /*if (holder is PatientIssueHolder){
                    val patientIssueHolder = holder as PatientRequestHolder
                }*/
            } else if (message.getString("message_type").equals("patient_issue")) {
                /*if (holder is PatientIssueHolder){
                    val patientIssueHolder = holder as PatientRequestHolder
                }*/
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        /*if (context is ALKChatActivity) {
            (context as ALKChatActivity).referralShare(geteventModelArrayList.get(position).getId())
        }*/
    }
}
