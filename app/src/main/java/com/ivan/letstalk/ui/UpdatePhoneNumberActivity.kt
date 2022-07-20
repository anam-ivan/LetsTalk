package com.ivan.letstalk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.ivan.letstalk.R

class UpdatePhoneNumberActivity : AppCompatActivity() {
    private lateinit var tvCancel: TextView
    private lateinit var btnUpdate: MaterialButton
    private lateinit var edtMobile: AppCompatEditText
    private lateinit var llMobile: LinearLayoutCompat
    private lateinit var rrOthers: RelativeLayout
    private lateinit var tvCountryCode: TextView
    private lateinit var ivDropdown: ImageView
    private lateinit var etPhone: AppCompatEditText
    private lateinit var rrPhone: RelativeLayout

    private lateinit var rrOthersTwo: RelativeLayout
    private lateinit var edtConfirmMobile: AppCompatEditText
    private lateinit var tvConfirmMobileCountryCode: TextView
    private lateinit var ivSecondDropDown: ImageView
    private lateinit var rrConfirmPhone: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_phone_number)
        rrPhone = findViewById(R.id.rr_phone)
        // etPhone = findViewById(R.id.edt_mobile)
        ivDropdown = findViewById(R.id.iv_dropdown)
        rrOthers = findViewById(R.id.rr_others)
        tvCancel = findViewById(R.id.tv_cancel)
        btnUpdate = findViewById(R.id.btn_update)
        edtMobile = findViewById(R.id.edt_mobile)
        llMobile = findViewById(R.id.llPhone)
        tvCancel.paint?.isUnderlineText = true
        tvCountryCode = findViewById(R.id.tv_country_code)

        rrConfirmPhone = findViewById(R.id.rr_confirm_phone)
        rrOthersTwo = findViewById(R.id.rr_others_two)
        edtConfirmMobile = findViewById(R.id.edt_confirm_mobile)
        tvConfirmMobileCountryCode = findViewById(R.id.tv_confirm_mobile_country_code)
        ivSecondDropDown = findViewById(R.id.iv_second_drop_down)

        tvCancel.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
            startActivity(intent)
        }
        btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateMobileConfirmationActivity::class.java)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
            startActivity(intent)
        }

        edtMobile.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                llMobile.setBackgroundResource(R.drawable.edit_text_border_focused)
            } else {
                llMobile.setBackgroundResource(R.drawable.edit_text_border)
            }

        }

        rrOthers.setOnClickListener {
            // ivDropdown.isClickable = true
            rrOthers.visibility = View.GONE
            tvCountryCode.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            tvCountryCode.text = "Others"
            ivDropdown.rotation = 360f
            edtMobile.hint = "Ex.442087599036"
            edtMobile.letterSpacing = 0.1F
            rrPhone.setBackgroundResource((R.drawable.country_code_back))
            /*tvEnterYourEmail.visibility = View.VISIBLE
            etEmail.visibility = View.VISIBLE*/
            ivDropdown.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        }

        ivDropdown.setOnClickListener {
            if (tvCountryCode.text.toString() == "Others") {
                ivDropdown.rotation = 180f
                tvCountryCode.text = "+91"
                tvCountryCode.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.greyy
                    )
                )
                // rrPhone.setBackgroundResource((R.drawable.others_back))
                rrPhone.setBackgroundResource((R.drawable.country_code_focused))
                rrOthers.visibility = View.VISIBLE
                ivDropdown.setColorFilter(ContextCompat.getColor(this, R.color.greyy), android.graphics.PorterDuff.Mode.SRC_IN)
            } else {
                ivDropdown.rotation = 180f
                // rrPhone.setBackgroundResource((R.drawable.others_back))
                rrPhone.setBackgroundResource((R.drawable.country_code_focused))
                tvCountryCode.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.greyy
                    )
                )
                ivDropdown.setColorFilter(
                    ContextCompat.getColor(this, R.color.greyy),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                rrOthers.visibility = View.VISIBLE
            }
        }

        tvCountryCode.setOnClickListener {
            rrPhone.setBackgroundResource((R.drawable.country_code_back))
            tvCountryCode.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            ivDropdown.setColorFilter(
                ContextCompat.getColor(this, R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            rrOthers.visibility = View.GONE
            ivDropdown.rotation = 360f
            edtMobile.hint = "0000000000"
            edtMobile.letterSpacing = 0.4F
        }




        ivSecondDropDown.setOnClickListener {
            if (tvConfirmMobileCountryCode.text.toString() == "Others") {
                ivSecondDropDown.rotation = 180f
                tvConfirmMobileCountryCode.text = "+91"
                tvConfirmMobileCountryCode.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.greyy
                    )
                )
                // rrConfirmPhone.setBackgroundResource((R.drawable.others_back))
                rrConfirmPhone.setBackgroundResource((R.drawable.country_code_focused))
                rrOthersTwo.visibility = View.VISIBLE
                ivSecondDropDown.setColorFilter(ContextCompat.getColor(this, R.color.greyy), android.graphics.PorterDuff.Mode.SRC_IN)
            } else {
                ivSecondDropDown.rotation = 180f
                // rrConfirmPhone.setBackgroundResource((R.drawable.others_back))
                rrConfirmPhone.setBackgroundResource((R.drawable.country_code_focused))
                tvConfirmMobileCountryCode.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.greyy
                    )
                )
                ivSecondDropDown.setColorFilter(
                    ContextCompat.getColor(this, R.color.greyy),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                rrOthersTwo.visibility = View.VISIBLE
            }
        }



        rrOthersTwo.setOnClickListener {
            rrOthersTwo.visibility = View.GONE
            tvConfirmMobileCountryCode.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            tvConfirmMobileCountryCode.text = "Others"
            ivSecondDropDown.rotation = 360f
            edtConfirmMobile.hint = "Ex.442087599036"
            edtConfirmMobile.letterSpacing = 0.1F
            rrConfirmPhone.setBackgroundResource((R.drawable.country_code_back))
            ivSecondDropDown.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        }

        tvConfirmMobileCountryCode.setOnClickListener {
            rrConfirmPhone.setBackgroundResource((R.drawable.country_code_back))
            tvConfirmMobileCountryCode.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            ivSecondDropDown.setColorFilter(
                ContextCompat.getColor(this, R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            rrOthersTwo.visibility = View.GONE
            ivSecondDropDown.rotation = 360f
            edtConfirmMobile.hint = "0000000000"
            edtConfirmMobile.letterSpacing = 0.4F
        }
    }
}