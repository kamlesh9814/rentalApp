package com.rbackent.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rbackent.app.R
import com.rbackent.app.databinding.ActivityRentalHistoryBinding
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar

class RentalHistoryActivity : AppCompatActivity() {
    var haveYouChecked = ""
    var haveYouChecked1 = ""
    var haveYouChecked2 = ""
    var where = ""
    var type = ""
    lateinit var binding: ActivityRentalHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rental_history)
        getIntentData()
        getSharedData()
        onClick()
    }

    private fun getIntentData() {
        where = intent.getStringExtra("where")!!
        type = intent.getStringExtra("rentalHouse")!!
    }

    private fun checkValidation(): Boolean {
        binding.apply {
            when {
                et1.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Current Address",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et2.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter How long have you been residing at this address?",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et3.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Monthly Rent",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et4.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Landlord Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et5.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Landlord's Contact Number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et6.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Reasons For Leaving This Property",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                (!(radioYes.isChecked || radioNo.isChecked)) -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Have You ever been evicted from a rental residence",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                (!(radioYes1.isChecked || radioNo1.isChecked)) -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Have You missed two or more rental payments in the past 12 months",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                (!(radioYes2.isChecked || radioNo2.isChecked)) -> {
                    Toast.makeText(
                        this@RentalHistoryActivity,
                        "Please Enter Have You ever refused to pay rent when due?",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    return true
                }
            }
        }
        return false
    }

    private fun onClick() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.IVSubmit.setOnClickListener {
            if (checkValidation()) {
                if (type == ConstantsVar.APARTMENT) {
                    ConstantsVar.module_type = type
                    if (where == "Rental") {
                        AppPrefs(this).setString("glow", "true")
                        AppPrefs(this).setString("haveYou", haveYouChecked)
                        AppPrefs(this).setString("radio1", haveYouChecked1)
                        AppPrefs(this).setString("radio2", haveYouChecked2)
                        AppPrefs(this).setString("current-Address", binding.et1.text.toString())
                        AppPrefs(this).setString("how-long", binding.et2.text.toString())
                        AppPrefs(this).setString("monthly-rent", binding.et3.text.toString())
                        AppPrefs(this).setString("land-lord", binding.et4.text.toString())
                        AppPrefs(this).setString("land-contact", binding.et5.text.toString())
                        AppPrefs(this).setString("reasons-for", binding.et6.text.toString())
                        AppPrefs(this).setString("pv-address", binding.et7.text.toString())
                        AppPrefs(this).setString("how-long-stay", binding.et8.text.toString())
                        AppPrefs(this).setString("monthly-rents", binding.et9.text.toString())
                        AppPrefs(this).setString("land-name", binding.et10.text.toString())
                        AppPrefs(this).setString("llc-number", binding.et11.text.toString())
                        AppPrefs(this).setString("reasons-leaving", binding.et12.text.toString())
                        AppPrefs(this).setString("you-answered", binding.et13.text.toString())
                        startActivity(Intent(this@RentalHistoryActivity,ApartmentsActivity::class.java))
                        finishAffinity()

                    } else if (where == "History") {
                        AppPrefs(this).setString("rd-glow", "true")
                        AppPrefs(this).setString("rd-haveYou", haveYouChecked)
                        AppPrefs(this).setString("rd-radio1", haveYouChecked1)
                        AppPrefs(this).setString("rd-radio2", haveYouChecked2)
                        AppPrefs(this).setString("rd-current-Address", binding.et1.text.toString())
                        AppPrefs(this).setString("rd-how-long", binding.et2.text.toString())
                        AppPrefs(this).setString("rd-monthly-rent", binding.et3.text.toString())
                        AppPrefs(this).setString("rd-land-lord", binding.et4.text.toString())
                        AppPrefs(this).setString("rd-land-contact", binding.et5.text.toString())
                        AppPrefs(this).setString("rd-reasons-for", binding.et6.text.toString())
                        AppPrefs(this).setString("rd-pv-address", binding.et7.text.toString())
                        AppPrefs(this).setString("rd-how-long-stay", binding.et8.text.toString())
                        AppPrefs(this).setString("rd-monthly-rents", binding.et9.text.toString())
                        AppPrefs(this).setString("rd-land-name", binding.et10.text.toString())
                        AppPrefs(this).setString("rd-llc-number", binding.et11.text.toString())
                        AppPrefs(this).setString("rd-reasons-leaving", binding.et12.text.toString())
                        AppPrefs(this).setString("rd-you-answered", binding.et13.text.toString())
                        startActivity(Intent(this@RentalHistoryActivity,ApartmentsActivity::class.java))
                        finishAffinity()
                    }

                } else if (type == ConstantsVar.HOUSE){
                    ConstantsVar.module_type = type
                    if (where == "Rental") {
                        AppPrefs(this).setString("house-glow", "true")
                        AppPrefs(this).setString("house-haveYou", haveYouChecked)
                        AppPrefs(this).setString("house-radio1", haveYouChecked1)
                        AppPrefs(this).setString("house-radio2", haveYouChecked2)
                        AppPrefs(this).setString("house-current-Address", binding.et1.text.toString())
                        AppPrefs(this).setString("house-how-long", binding.et2.text.toString())
                        AppPrefs(this).setString("house-monthly-rent", binding.et3.text.toString())
                        AppPrefs(this).setString("house-land-lord", binding.et4.text.toString())
                        AppPrefs(this).setString("house-land-contact", binding.et5.text.toString())
                        AppPrefs(this).setString("house-reasons-for", binding.et6.text.toString())
                        AppPrefs(this).setString("house-pv-address", binding.et7.text.toString())
                        AppPrefs(this).setString("house-how-long-stay", binding.et8.text.toString())
                        AppPrefs(this).setString("house-monthly-rents", binding.et9.text.toString())
                        AppPrefs(this).setString("house-land-name", binding.et10.text.toString())
                        AppPrefs(this).setString("house-llc-number", binding.et11.text.toString())
                        AppPrefs(this).setString("house-reasons-leaving", binding.et12.text.toString())
                        AppPrefs(this).setString("house-you-answered", binding.et13.text.toString())
                        startActivity(Intent(this@RentalHistoryActivity,ApartmentsActivity::class.java))
                        finishAffinity()

                    } else if (where == "History") {
                        AppPrefs(this).setString("house-rd-glow", "true")
                        AppPrefs(this).setString("house-rd-haveYou", haveYouChecked)
                        AppPrefs(this).setString("house-rd-radio1", haveYouChecked1)
                        AppPrefs(this).setString("house-rd-radio2", haveYouChecked2)
                        AppPrefs(this).setString("house-rd-current-Address", binding.et1.text.toString())
                        AppPrefs(this).setString("house-rd-how-long", binding.et2.text.toString())
                        AppPrefs(this).setString("house-rd-monthly-rent", binding.et3.text.toString())
                        AppPrefs(this).setString("house-rd-land-lord", binding.et4.text.toString())
                        AppPrefs(this).setString("house-rd-land-contact", binding.et5.text.toString())
                        AppPrefs(this).setString("house-rd-reasons-for", binding.et6.text.toString())
                        AppPrefs(this).setString("house-rd-pv-address", binding.et7.text.toString())
                        AppPrefs(this).setString("house-rd-how-long-stay", binding.et8.text.toString())
                        AppPrefs(this).setString("house-rd-monthly-rents", binding.et9.text.toString())
                        AppPrefs(this).setString("house-rd-land-name", binding.et10.text.toString())
                        AppPrefs(this).setString("house-rd-llc-number", binding.et11.text.toString())
                        AppPrefs(this).setString("house-rd-reasons-leaving", binding.et12.text.toString())
                        AppPrefs(this).setString("house-rd-you-answered", binding.et13.text.toString())
                        startActivity(Intent(this@RentalHistoryActivity,ApartmentsActivity::class.java))
                        finishAffinity()
                    }
                }
            }
        }
    }

    private fun getSharedData() {
        if (type == ConstantsVar.APARTMENT) {
            if (where == "Rental") {
                haveYouChecked = AppPrefs(this).getString("haveYou")!!
                when (haveYouChecked) {
                    "yes" -> {
                        binding.radioYes.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo.isChecked = true
                    }
                }
                haveYouChecked1 = AppPrefs(this).getString("radio1")!!
                when (haveYouChecked1) {
                    "yes" -> {
                        binding.radioYes1.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo1.isChecked = true
                    }
                }
                haveYouChecked2 = AppPrefs(this).getString("radio2")!!
                when (haveYouChecked2) {
                    "yes" -> {
                        binding.radioYes2.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo2.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("current-Address"))
                binding.et2.setText(AppPrefs(this).getString("how-long"))
                binding.et3.setText(AppPrefs(this).getString("monthly-rent"))
                binding.et4.setText(AppPrefs(this).getString("land-lord"))
                binding.et5.setText(AppPrefs(this).getString("land-contact"))
                binding.et6.setText(AppPrefs(this).getString("reasons-for"))
                binding.et7.setText(AppPrefs(this).getString("pv-address"))
                binding.et8.setText(AppPrefs(this).getString("how-long-stay"))
                binding.et9.setText(AppPrefs(this).getString("monthly-rents"))
                binding.et10.setText(AppPrefs(this).getString("land-name"))
                binding.et11.setText(AppPrefs(this).getString("llc-number"))
                binding.et12.setText(AppPrefs(this).getString("reasons-leaving"))
                binding.et13.setText(AppPrefs(this).getString("you-answered"))

            } else if (where == "History") {
                haveYouChecked = AppPrefs(this).getString("rd-haveYou")!!
                when (haveYouChecked) {
                    "yes" -> {
                        binding.radioYes.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo.isChecked = true
                    }
                }
                haveYouChecked1 = AppPrefs(this).getString("rd-radio1")!!
                when (haveYouChecked1) {
                    "yes" -> {
                        binding.radioYes1.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo1.isChecked = true
                    }
                }
                haveYouChecked2 = AppPrefs(this).getString("rd-radio2")!!
                when (haveYouChecked2) {
                    "yes" -> {
                        binding.radioYes2.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo2.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("rd-current-Address"))
                binding.et2.setText(AppPrefs(this).getString("rd-how-long"))
                binding.et3.setText(AppPrefs(this).getString("rd-monthly-rent"))
                binding.et4.setText(AppPrefs(this).getString("rd-land-lord"))
                binding.et5.setText(AppPrefs(this).getString("rd-land-contact"))
                binding.et6.setText(AppPrefs(this).getString("rd-reasons-for"))
                binding.et7.setText(AppPrefs(this).getString("rd-pv-address"))
                binding.et8.setText(AppPrefs(this).getString("rd-how-long-stay"))
                binding.et9.setText(AppPrefs(this).getString("rd-monthly-rents"))
                binding.et10.setText(AppPrefs(this).getString("rd-land-name"))
                binding.et11.setText(AppPrefs(this).getString("rd-llc-number"))
                binding.et12.setText(AppPrefs(this).getString("rd-reasons-leaving"))
                binding.et13.setText(AppPrefs(this).getString("rd-you-answered"))
            }
        } else if (type == ConstantsVar.HOUSE){
            if (where == "Rental") {
                haveYouChecked = AppPrefs(this).getString("house-haveYou")!!
                when (haveYouChecked) {
                    "yes" -> {
                        binding.radioYes.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo.isChecked = true
                    }
                }
                haveYouChecked1 = AppPrefs(this).getString("house-radio1")!!
                when (haveYouChecked1) {
                    "yes" -> {
                        binding.radioYes1.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo1.isChecked = true
                    }
                }
                haveYouChecked2 = AppPrefs(this).getString("house-radio2")!!
                when (haveYouChecked2) {
                    "yes" -> {
                        binding.radioYes2.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo2.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("house-current-Address"))
                binding.et2.setText(AppPrefs(this).getString("house-how-long"))
                binding.et3.setText(AppPrefs(this).getString("house-monthly-rent"))
                binding.et4.setText(AppPrefs(this).getString("house-land-lord"))
                binding.et5.setText(AppPrefs(this).getString("house-land-contact"))
                binding.et6.setText(AppPrefs(this).getString("house-reasons-for"))
                binding.et7.setText(AppPrefs(this).getString("house-pv-address"))
                binding.et8.setText(AppPrefs(this).getString("house-how-long-stay"))
                binding.et9.setText(AppPrefs(this).getString("house-monthly-rents"))
                binding.et10.setText(AppPrefs(this).getString("house-land-name"))
                binding.et11.setText(AppPrefs(this).getString("house-llc-number"))
                binding.et12.setText(AppPrefs(this).getString("house-reasons-leaving"))
                binding.et13.setText(AppPrefs(this).getString("house-you-answered"))

            } else if (where == "History") {
                haveYouChecked = AppPrefs(this).getString("house-rd-haveYou")!!
                when (haveYouChecked) {
                    "yes" -> {
                        binding.radioYes.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo.isChecked = true
                    }
                }
                haveYouChecked1 = AppPrefs(this).getString("house-rd-radio1")!!
                when (haveYouChecked1) {
                    "yes" -> {
                        binding.radioYes1.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo1.isChecked = true
                    }
                }
                haveYouChecked2 = AppPrefs(this).getString("house-rd-radio2")!!
                when (haveYouChecked2) {
                    "yes" -> {
                        binding.radioYes2.isChecked = true
                    }
                    "no" -> {
                        binding.radioNo2.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("house-rd-current-Address"))
                binding.et2.setText(AppPrefs(this).getString("house-rd-how-long"))
                binding.et3.setText(AppPrefs(this).getString("house-rd-monthly-rent"))
                binding.et4.setText(AppPrefs(this).getString("house-rd-land-lord"))
                binding.et5.setText(AppPrefs(this).getString("house-rd-land-contact"))
                binding.et6.setText(AppPrefs(this).getString("house-rd-reasons-for"))
                binding.et7.setText(AppPrefs(this).getString("house-rd-pv-address"))
                binding.et8.setText(AppPrefs(this).getString("house-rd-how-long-stay"))
                binding.et9.setText(AppPrefs(this).getString("house-rd-monthly-rents"))
                binding.et10.setText(AppPrefs(this).getString("house-rd-land-name"))
                binding.et11.setText(AppPrefs(this).getString("house-rd-llc-number"))
                binding.et12.setText(AppPrefs(this).getString("house-rd-reasons-leaving"))
                binding.et13.setText(AppPrefs(this).getString("house-rd-you-answered"))
            }

        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radioYes -> {
                    haveYouChecked = "yes"
                    haveYouChecked2 = "yes"
                }
                R.id.radioNo -> {
                    haveYouChecked = "no"

                    haveYouChecked2 = "no"
                }
                R.id.radioYes1 -> {
                    haveYouChecked1 = "yes"
                }
                R.id.radioNo1 -> {
                    haveYouChecked1 = "no"
                }
                R.id.radioYes2 -> {
                    haveYouChecked2 = "yes"
                }
                R.id.radioNo2-> {
                    haveYouChecked2 = "no"
                }
            }
        }
    }
}





