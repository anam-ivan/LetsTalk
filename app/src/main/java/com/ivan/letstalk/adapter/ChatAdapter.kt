package com.ivan.letstalk.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ivan.letstalk.R
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class ChatAdapter(applicationContext: Context, private val inflater: LayoutInflater) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val messages: MutableList<JSONObject> = ArrayList()
    var context: Context = applicationContext

    private inner class SentMessageHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageTxt: TextView
        var tvTimeStamp: TextView

        init {
            messageTxt = itemView.findViewById(R.id.tv_send)
            tvTimeStamp = itemView.findViewById(R.id.tv_time_stamp)
        }
    }

    private inner class ReceivedMessageHolder(itemView: View) :
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

    override fun getItemViewType(position: Int): Int {
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
    }

    /*override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        try {
            if (message.has("greetings")) {
                TYPE_MESSAGE_SENT
            } else if (message.has("patient_issue")){
                PATIENT_ISSUE
            } else if (message.has("side_effects_list")){
                SIDE_EFFECT_LIST
            } else {
                PATIENT_REQUEST
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return -1
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            TYPE_MESSAGE_SENT -> {
                view = inflater.inflate(R.layout.chat_send_message_item, parent, false)
                return SentMessageHolder(view)
            }
            TYPE_MESSAGE_RECEIVED -> {
                view = inflater.inflate(R.layout.chat_message_received_item, parent, false)
                return ReceivedMessageHolder(view)
            }
        }
        view = inflater.inflate(R.layout.chat_send_message_item, parent, false)
        return SentMessageHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun addItem(jsonObject: JSONObject) {
        messages.add(jsonObject)
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_MESSAGE_SENT = 0
        private const val TYPE_MESSAGE_RECEIVED = 1
        private const val MESSAGE_GREETINGS = 2
        private const val PATIENT_ISSUE = 3
        private const val SIDE_EFFECT_LIST = 4
        private const val PATIENT_REQUEST = 5
    }

    @SuppressLint("SimpleDateFormat")
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
                // messageHolder.tvGreetings.text = greetingsMsg
                messageHolder.tvGreetings.text = Html.fromHtml(greetingsMsg)
                messageHolder.tvHealthDetails.text = Html.fromHtml(patientHealthDetails)
                messageHolder.tvReceiveTimeStamp.text = currentDateAndTime
                val option = dataJsonObject.getString("option")
                /*Log.d("option",option)
                val anim: Animation = AnimationUtils.loadAnimation(
                    context,
                    android.R.anim.slide_out_right
                )
                anim.duration = 300
                messageHolder.rootView.startAnimation(anim)*/

                /*if (option.isNotEmpty() && option.toString() == "next&2&") {
                    messageHolder.cvHelp.visibility = View.VISIBLE
                    messageHolder.cvGreetings.visibility = View.GONE
                    messageHolder.tvReceiveTimeStamp.visibility = View.GONE
                    val dataJsonObject = message.getJSONObject("data")
                    val responseText = dataJsonObject.getJSONArray("response_text")
                    val patientHealthDetails = responseText.getJSONObject(0).getString("0")
                    messageHolder.tvPatientHealthDetails.text = patientHealthDetails
                }*/
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
