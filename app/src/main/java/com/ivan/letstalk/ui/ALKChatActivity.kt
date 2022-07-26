package com.ivan.letstalk.ui
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.gson.Gson
import com.ivan.letstalk.R
import com.ivan.letstalk.databinding.ActivityAlkchatBinding
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject


class ALKChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlkchatBinding
    /*private lateinit var cgExistingSideEffects: ChipGroup
    private lateinit var rrMyChats: RelativeLayout
    private lateinit var ivMenu: ImageView
    private lateinit var ivCross: ImageView*/
    private lateinit var dialog: Dialog
    val gson: Gson = Gson()
    private var existingSideEffectsList = arrayOf(
        "Abdominal Pain",
        "Constipation",
        "Dyspepsia",
        "Dysphagia",
        "Electrocardiogram QT prlonged",
        "Nausea",
        "Vomiting",
        "Vision Disorder",
        "Constipation",
        "Dyspepsia",
        "Nausea",
        "Abdominal Pain",
        "Constipation",
        "Dyspepsia",
        "Dysphagia",
        "Electrocardiogram QT prlonged",
        "Nausea",
        "Vomiting",
        "Vision Disorder",
        "Constipation",
        "Dyspepsia",
        "Nausea"
    )
    private var existingSideEffectsChipItems = ArrayList<String>()

    private var webSocket: WebSocket? = null
    private val SERVER_PATH = "ws://0.tcp.in.ngrok.io:14344"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alkchat)
        /*rrMyChats = findViewById(R.id.rr_my_chats)
        ivMenu = findViewById(R.id.iv_menu)
        ivCross = findViewById(R.id.iv_cross)
        cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)*/
        initExistingSideEffectsData()
        binding.rrMyChats.setOnClickListener {
            navigateToMyChats()
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setOnCancelListener(DialogInterface.OnCancelListener {
            binding.ivMenu.visibility = View.VISIBLE
            binding.ivCross.visibility = View.INVISIBLE
        })

        binding.ivMenu.setOnClickListener {
            showChatDialog()
            binding.ivMenu.visibility = View.INVISIBLE
            binding.ivCross.visibility = View.VISIBLE
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

        if (binding.ivCross.visibility == View.VISIBLE) {
            binding.ivCross.setOnClickListener {
                // ivCross.visibility = View.INVISIBLE
                // ivMenu.visibility = View.VISIBLE
                dialog.dismiss()
            }
        }


        /*Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                try {
                    socket = IO.socket("https://letstalkwebsocket.dev13.ivantechnology.in/chat")
                    socket.connect()
                    socket.emit("joined", {})
                    Log.d("isSocketCon", socket.connected().toString())
                } catch (e: URISyntaxException) {
                    Log.d("chat_error", e.toString())
                    e.printStackTrace()
                }
            }
        }, 0, 1000)*/


        /*try {
            socket = IO.socket("https://letstalkwebsocket.dev13.ivantechnology.in/chat")
            socket.connect()
            socket.emit("joined", {})
            Log.d("isSocketCon",socket.connected().toString())
        } catch (e: URISyntaxException) {
            Log.d("chat_error",e.toString())
            e.printStackTrace()
        }*/

        /*SocketHandler.setSocket()
        SocketHandler.establishConnection()
        val mSocket = SocketHandler.getSocket()
        mSocket.emit("joined", {})
        Log.d("isSocketCon",mSocket.connected().toString())*/

        initiateSocketConnection()

        binding.btnSendMessage.setOnClickListener(View.OnClickListener { v: View? ->
            val jsonObject = JSONObject()
            try {
                jsonObject.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6dHJ1ZSwiZW1haWxfaWQiOiJsZXRzdGFsa3BhdGllbnQxQHlvcG1haWwuY29tIiwicm9sZV9pZCI6IjMiLCJfaWQiOiI2MmQ1MDI2ZjM4ZDM2OWVhNWY1ODkzYjgiLCJleHAiOjE2NTkyNTIxMDYsIm1vYmlsZSI6IjcyNDk5OTk4MDkifQ.RgFYade3DEG0r1isTvUTAjJIGZCfzIupiqZi-_XKW2U")
                webSocket!!.send(jsonObject.toString())
                jsonObject.put("isSent", true)
                /*messageAdapter.addItem(jsonObject)
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1)
                resetMessageEdit()*/
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        })
    }

    private fun initExistingSideEffectsData() {
        for (i in existingSideEffectsList.indices) {
            // cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
            val entryChip2: Chip = getChip(existingSideEffectsList[i])
            entryChip2.id = i
            //set default selected language
            //entryChip2.setChecked(true);
            binding.chipExistingSideEffects.addView(entryChip2)
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
    }

    private class SocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            /*this@ALKChatActivity.runOnUiThread(Runnable {
                Toast.makeText(
                    this@ALKChatActivity,
                    "Socket Connection Successful!",
                    Toast.LENGTH_SHORT
                ).show()
                // initializeView()
            })*/
            Handler(Looper.getMainLooper()).post {
                // Log.v("WSS", text!!)
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            /*this@ALKChatActivity.runOnUiThread(Runnable {
                try {
                    val jsonObject = JSONObject(text)
                    jsonObject.put("isSent", false)
                    *//*messageAdapter.addItem(jsonObject)
                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1)*//*
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            })*/
            Handler(Looper.getMainLooper()).post {
                Log.v("WSS", text)
            }
        }
    }

    private fun initiateSocketConnection() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(SERVER_PATH)
            .build()
        val wsListener = SocketListener()
        webSocket = client.newWebSocket(request, wsListener)
    }

    public fun output(txt: String) {
        Log.v("WSS", txt)
    }

}