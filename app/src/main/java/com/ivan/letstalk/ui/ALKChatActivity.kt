package com.ivan.letstalk.ui

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.ivan.letstalk.R
/*import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter*/
import okhttp3.WebSocket
import java.net.URISyntaxException


class ALKChatActivity : AppCompatActivity() {
    private lateinit var cgExistingSideEffects: ChipGroup
    private lateinit var rrMyChats: RelativeLayout
    private lateinit var ivMenu: ImageView
    private lateinit var ivCross: ImageView
    private lateinit var dialog: Dialog
    private var isButtonClicked = false
    val gson: Gson = Gson()
    // private lateinit var ivHome: AppCompatImageView
    private var existingSideEffectsList = arrayOf(
        "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea", "Abdominal Pain", "Constipation", "Dyspepsia", "Dysphagia", "Electrocardiogram QT prlonged",
        "Nausea", "Vomiting", "Vision Disorder", "Constipation",  "Dyspepsia", "Nausea"
    )
    private var existingSideEffectsChipItems = ArrayList<String>()
    private lateinit var socket: Socket
    // private var webSocket: WebSocket? = null
    // lateinit var mSocket: Socket
    // private lateinit var nSocket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alkchat)
        rrMyChats = findViewById(R.id.rr_my_chats)
        ivMenu = findViewById(R.id.iv_menu)
        ivCross = findViewById(R.id.iv_cross)
        /*ivHome = findViewById(R.id.iv_home)
        ivHome.setBackgroundResource(R.drawable.ic_home)*/

        cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
        initExistingSideEffectsData()
        rrMyChats.setOnClickListener{
            navigateToMyChats()
        }
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setOnCancelListener(DialogInterface.OnCancelListener {
            ivMenu.visibility = View.VISIBLE
            ivCross.visibility = View.INVISIBLE
        })

        ivMenu.setOnClickListener {
            showChatDialog()
            ivMenu.visibility = View.INVISIBLE
            ivCross.visibility = View.VISIBLE
        }

        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        /*if (ivCross.visibility == View.VISIBLE) {
            ivCross.setOnClickListener {
                ivCross.visibility = View.INVISIBLE
                dialog.dismiss()
                ivMenu.visibility = View.VISIBLE
            }
        }*/

        if (ivCross.visibility == View.VISIBLE) {
            ivCross.setOnClickListener {
                // ivCross.visibility = View.INVISIBLE
                // ivMenu.visibility = View.VISIBLE
                dialog.dismiss()
            }
        }

        try {
            // socket = IO.socket("http://192.168.1.89:8000/chat")
            socket = IO.socket("https://letstalk.dev13.ivantechnology.in/chat")
            socket.connect()
            socket.emit("joined", "")
            Log.d("isSocketCon",socket.connected().toString())
        } catch (e: URISyntaxException) {
            Log.d("chat_error",e.toString())
            e.printStackTrace()
        }

        /*try {
            mSocket = IO.socket("http://192.168.1.89:8000/chat")
            mSocket.connect()
            mSocket.on(Socket.EVENT_CONNECT, onConnect)
            // mSocket.emit("joined")
            Log.d("isCon", mSocket.connected().toString())
        } catch (e: Exception) {
            e.printStackTrace()
            e.message?.let { Log.d("chat_error", it) }
        }*/

        /*try {
            nSocket = IO.socket("http://192.168.1.89:8000/chat")
            nSocket.connect()
            nSocket.emit("joined", "")
            Log.d("nSocket", nSocket.isActive.toString())
        } catch (e: URISyntaxException) {
            e.message?.let { Log.d("chat_error", it) }
            e.printStackTrace()
        }*/


    }

    private fun initExistingSideEffectsData() {
        for (i in existingSideEffectsList.indices) {
            cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
            val entryChip2: Chip = getChip(existingSideEffectsList[i])
            entryChip2.id = i
            //set default selected language
            //entryChip2.setChecked(true);
            cgExistingSideEffects.addView(entryChip2)
        }
    }

    private fun getChip(text: String): Chip {
        val chip = Chip(this)
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.my_chip))
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50f,
            resources.displayMetrics
        ).toInt()
        chip.setChipBackgroundColorResource(R.color.divider_color)
        chip.setTextColor(ContextCompat.getColor(this, R.color.black))
        chip.isCloseIconVisible = false
        chip.isCheckedIconVisible = false
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        chip.text = text
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = getColorStateList(R.color.chat_answer_select)
                }
                chip.setTextColor(ContextCompat.getColor(this, R.color.white))
                chip.isChecked = true
                existingSideEffectsChipItems.add(chip.text.toString())
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.chipBackgroundColor = getColorStateList(R.color.divider_color)
                }
                chip.setTextColor(ContextCompat.getColor(this, R.color.black))
                chip.isChecked = false
                existingSideEffectsChipItems.remove(chip.text.toString())
            }
        }
        return chip
    }

    private fun navigateToMyChats() {
        val intent = Intent(this, MyALKChatActivity::class.java)
        startActivity(intent)
    }

    private fun showChatDialog() {
        /*dialog = Dialog(this)
        dialog.setOnCancelListener(DialogInterface.OnCancelListener {

        })*/
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.window?.setGravity(Gravity.LEFT)
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.window?.attributes!!.verticalMargin = 0.2F
        dialog.window?.attributes!!.horizontalMargin = 0.1F
        val params = this.window.attributes
        // this.setCanceledOnTouchOutside(true)
        // params.x = -100
        this.window.attributes = params
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.chat_dialog)
        dialog.show()
        /*isButtonClicked = dialog.isShowing
        ivMenu.setBackgroundResource(if (isButtonClicked) R.drawable.ic_menu else R.drawable.ic_cross)*/
    }

    /*var onConnect = Emitter.Listener {
        //After getting a Socket.EVENT_CONNECT which indicate socket has been connected to server,
        //send userName and roomName so that they can join the room.
        mSocket.emit("joined", "")
    }*/
}