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
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.gson.Gson
import com.ivan.letstalk.R
import com.ivan.letstalk.adapter.ChatAdapter
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
    private var isConnect = false
    private var messageAdapter: ChatAdapter? = null
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
    private val SERVER_PATH = "ws://0.tcp.in.ngrok.io:16024"

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
            this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
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

        messageAdapter = ChatAdapter(this,layoutInflater)
        binding.rvChat.adapter = messageAdapter
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        initiateSocketConnection()
        joinChatWithToken()
        Log.d("isConnect",isConnect().toString())
        /*if (isConnect()) {
            emitUserMessage()
        }*/
        // emitUserMessage()
    }

    private fun initExistingSideEffectsData() {
        for (i in existingSideEffectsList.indices) {
            // cgExistingSideEffects = findViewById(R.id.chip_existing_side_effects)
            val entryChip2: Chip = getChip(existingSideEffectsList[i])
            entryChip2.id = i
            //set default selected language
            //entryChip2.setChecked(true);
            // binding.chipExistingSideEffects.addView(entryChip2)
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

    class SocketListener(_context: ALKChatActivity) : WebSocketListener() {
        var context: ALKChatActivity? = _context

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Handler(Looper.getMainLooper()).post {
                /*context!!.messageAdapter = ChatAdapter(context!!.layoutInflater)
                context!!.binding.rvChat.adapter = context!!.messageAdapter
                context!!.binding.rvChat.layoutManager = LinearLayoutManager(context)*/
                // isConnect = response.code == 101
                if (response.code == 101) {
                    context!!.isConnect = true
                    context!!.emitUserMessage()
                }
                Log.v("response_code", response.code.toString())
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            Handler(Looper.getMainLooper()).post {
                if (text.isNotEmpty()) {
                    val jsonObject = JSONObject(text)
                    val dataJsonObject = jsonObject.getJSONObject("data")
                    if (dataJsonObject.has("chatid")) {
                        val chatID = dataJsonObject.getString("chatid")
                        chatId = chatID
                        Log.v("chatID", chatId)
                    } else {
                        Log.v("webSocketResponse", text)
                        if (dataJsonObject.has("response_text")) {
                            Log.v("response_text", text)
                            jsonObject.put("isSent", false)
                            context!!.messageAdapter?.addItem(jsonObject)
                            context!!.binding.rvChat.smoothScrollToPosition(context!!.messageAdapter!!.itemCount - 1)
                            val option = dataJsonObject.getString("option")
                            Log.d("option",option)
                            if (option.isNotEmpty() && option.toString() == "next&2&") {
                                context!! emitUserMessageOption ("next&2&")
                                Log.v("health_options", text)
                            }
                        } else {
                            /*Log.v("WS2", text)
                            jsonObject.put("isSent", true)
                            context!!.messageAdapter?.addItem(jsonObject)
                            context!!.binding.rvChat.smoothScrollToPosition(context!!.messageAdapter!!.itemCount - 1)*/
                        }
                    }
                    /*context!!emitUserMessageOption("next&1&")
                    Log.v("health_options", text)*/
                }
            }
        }
    }

    private fun initiateSocketConnection() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(SERVER_PATH)
            .build()
        val wsListener = SocketListener(this)
        webSocket = client.newWebSocket(request, wsListener)
    }

    private fun joinChatWithToken() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put(
                "token",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6dHJ1ZSwiZW1haWxfaWQiOiJsZXRzdGFsa3BhdGllbnQxQHlvcG1haWwuY29tIiwicm9sZV9pZCI6IjMiLCJfaWQiOiI2MmQ1MDI2ZjM4ZDM2OWVhNWY1ODkzYjgiLCJleHAiOjE2NTkyNTIxMDYsIm1vYmlsZSI6IjcyNDk5OTk4MDkifQ.RgFYade3DEG0r1isTvUTAjJIGZCfzIupiqZi-_XKW2U"
            )
            webSocket!!.send(jsonObject.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun emitUserMessage(){
        binding.btnSendMessage.setOnClickListener(View.OnClickListener { v: View? ->
            val jsonObject = JSONObject()
            try {
                jsonObject.put("user", binding.edtSendMessage.text.toString().trim())
                jsonObject.put("chatid", chatId)
                webSocket!!.send(jsonObject.toString())
                binding.edtSendMessage.setText("")
                hideSoftKeyboard(binding.edtSendMessage)
                jsonObject.put("isSent", true)
                // jsonObject.put("message_type","greetings")
                messageAdapter!!.addItem(jsonObject)
                binding.rvChat.smoothScrollToPosition(messageAdapter!!.itemCount - 1)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        })
    }

    private infix fun emitUserMessageOption(option: String){
            val jsonObject = JSONObject()
            try {
                jsonObject.put("user", option)
                jsonObject.put("chatid", chatId)
                webSocket!!.send(jsonObject.toString())
                Log.d("next",Gson().toJson(jsonObject))
                binding.edtSendMessage.setText("")
                hideSoftKeyboard(binding.edtSendMessage)
                /*jsonObject.put("isSent", false)
                messageAdapter!!.addItem(jsonObject)
                binding.rvChat.smoothScrollToPosition(messageAdapter!!.itemCount - 1)*/
            } catch (e: JSONException) {
                e.printStackTrace()
            }
    }

    companion object {
        private var chatId = ""
        // private var isConnect = false
    }

    private fun isConnect(): Boolean {
        return isConnect
    }

    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}