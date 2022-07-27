package com.ivan.letstalk.adapter

import android.annotation.SuppressLint
import android.content.Context
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
        // var messageTxt: TextView
        var rootView: MaterialCardView

        init {
            tvGreetings = itemView.findViewById(R.id.tv_greetings)
            tvReceiveTimeStamp = itemView.findViewById(R.id.tv_receive_time_stamp)
            // messageTxt = itemView.findViewById(R.id.iv_body_weight_header)
            rootView = itemView.findViewById(R.id.cv_first_chat)
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

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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
        return view
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


    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        try {
            if (message.getBoolean("isSent")) {
                if (message.has("message")) {
                    val messageHolder = holder as SentMessageHolder
                    messageHolder.messageTxt.text = message.getString("message")
                }
            } else {
                if (message.has("message")) {
                    val messageHolder = holder as ReceivedMessageHolder
                    messageHolder.nameTxt.text = message.getString("name")
                    messageHolder.messageTxt.text = message.getString("message")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }*/

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
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        // Log.d("adapter_response", Gson().toJson(message))
        try {
            if (message.getBoolean("isSent")) {
                val simpleDateFormat = SimpleDateFormat("hh:mm a")
                val currentDateAndTime: String = simpleDateFormat.format(Date())
                val messageHolder = holder as SentMessageHolder
                // messageHolder.messageTxt.text = message.getString("message")
                messageHolder.tvTimeStamp.text = currentDateAndTime
            } else {
                val messageHolder = holder as ReceivedMessageHolder
                val dataJsonObject = message.getJSONObject("data")
                val responseText = dataJsonObject.getJSONArray("response_text")
                val greetingsMsg = responseText.getJSONObject(0).getString("0")
                /*Log.d("dataJsonObject", Gson().toJson(dataJsonObject))
                Log.d("firstValue", greetingsMsg)*/
                val simpleDateFormat = SimpleDateFormat("hh:mm a")
                val currentDateAndTime: String = simpleDateFormat.format(Date())
                messageHolder.tvGreetings.text = greetingsMsg
                messageHolder.tvReceiveTimeStamp.text = currentDateAndTime
                /*val anim: Animation = AnimationUtils.loadAnimation(
                    context,
                    android.R.anim.slide_out_right
                )
                anim.duration = 300
                messageHolder.rootView.startAnimation(anim)*/
                // messageHolder.messageTxt.text = message.getString("response_text")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
