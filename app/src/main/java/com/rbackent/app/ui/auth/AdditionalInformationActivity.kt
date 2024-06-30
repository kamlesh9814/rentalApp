package com.rbackent.app.ui.auth

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rbackent.app.R
import com.rbackent.app.databinding.ActivityAdditionalInformationBinding
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar
import java.io.ByteArrayOutputStream


class AdditionalInformationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdditionalInformationBinding
    var imageData = ""
    var signatureData = ""
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_additional_information)
        onClick()
        getIntentData()
        getSharedData()

    }

    private fun getIntentData() {
        type = intent.getStringExtra("houseAdditional")!!
    }

    override fun onResume() {
        super.onResume()
        // set signature
        if (type == ConstantsVar.APARTMENT) {
            imageData = AppPrefs(this@AdditionalInformationActivity).getString("signatureImage")!!
            if (imageData != "") {
                val b: ByteArray = Base64.decode(imageData, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
                binding.IVSignature.setImageBitmap(bitmap)
            }
            signatureData =
                AppPrefs(this@AdditionalInformationActivity).getString("imageSignature").toString()
            if (signatureData != "") {
                val a: ByteArray = Base64.decode(signatureData, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(a, 0, a.size)
                binding.IVSignature1.setImageBitmap(bitmap)
            }
        } else if (type == ConstantsVar.HOUSE){
            imageData =
                AppPrefs(this@AdditionalInformationActivity).getString("house-signatureImage").toString()
            if (imageData != "") {
                val b: ByteArray = Base64.decode(imageData, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
                binding.IVSignature.setImageBitmap(bitmap)
            }
            signatureData =
                AppPrefs(this@AdditionalInformationActivity).getString("house-imageSignature").toString()
            if (signatureData != "") {
                val a: ByteArray = Base64.decode(signatureData, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(a, 0, a.size)
                binding.IVSignature1.setImageBitmap(bitmap)
            }
        }
    }
    private fun checkValidation(): Boolean {
        binding.apply {
            when {
                imageData == "" -> {
                    Toast.makeText(
                        this@AdditionalInformationActivity,
                        "Please Fill Applicant Signature",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et2.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@AdditionalInformationActivity,
                        "Please Enter Date",
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
        binding.cclSignature.setOnClickListener {
            val intent = Intent(this, SignatureActivity::class.java)
            intent.putExtra("houseSignature",type)
            intent.putExtra("additional","signature")
            startActivityForResult(intent, 1)
        }
        binding.cclSignature1.setOnClickListener {
            val intent = Intent(this, SignatureActivity::class.java)
            intent.putExtra("houseSignature",type)
            intent.putExtra("additional","signature1")
            startActivityForResult(intent, 1)
        }
        binding.et2.setOnClickListener {
            showDate()
        }
        binding.et4.setOnClickListener {
            showDate1()
        }

        binding.IVSubmit.setOnClickListener {
            if (checkValidation()) {
                if (type == ConstantsVar.APARTMENT) {
                    ConstantsVar.module_type = type
                    AppPrefs(this).setString("ad-fill", "true")
                    AppPrefs(this).setString("ad-applicantSignature", imageData)
                    AppPrefs(this).setString("ad-date", binding.et2.text.toString())
                    AppPrefs(this).setString("ad-dates", binding.et4.text.toString())
                    AppPrefs(this).setString("ad-co_applicantSignature", signatureData)
                    startActivity(Intent(this@AdditionalInformationActivity,ApartmentsActivity::class.java))
                    finishAffinity()
                } else if (type == ConstantsVar.HOUSE){
                    ConstantsVar.module_type = type
                    AppPrefs(this).setString("house-ad-fill", "true")
                    AppPrefs(this).setString("house-ad-applicantSignature", imageData)
                    AppPrefs(this).setString("house-ad-date", binding.et2.text.toString())
                    AppPrefs(this).setString("house-ad-dates", binding.et4.text.toString())
                    AppPrefs(this).setString("house-ad-co_applicantSignature", signatureData)
                    startActivity(Intent(this@AdditionalInformationActivity,ApartmentsActivity::class.java))
                    finishAffinity()
                }
            }
        }
    }
    private fun showDate1() {
        binding.et4.setOnClickListener {
            ConstantsVar.showDate(this@AdditionalInformationActivity, binding.et4)
        }
    }
    private fun showDate() {
        binding.et2.setOnClickListener {
            ConstantsVar.showDate(this@AdditionalInformationActivity, binding.et2)
        }
    }
    private fun getSharedData() {
        if (type == ConstantsVar.APARTMENT) {
            imageData = Uri.parse(AppPrefs(this).getString("ad-applicantSignature")).toString()
            binding.et2.setText(AppPrefs(this).getString("ad-date"))
            setbitmapInShared()
            signatureData =
                Uri.parse(AppPrefs(this).getString("ad-co_applicantSignature")).toString()
            binding.et4.setText(AppPrefs(this).getString("ad-dates"))

        } else if (type == ConstantsVar.HOUSE){
            imageData = Uri.parse(AppPrefs(this).getString("house-ad-applicantSignature")).toString()
            binding.et2.setText(AppPrefs(this).getString("house-ad-date"))
            setbitmapInShared()
            signatureData =
                Uri.parse(AppPrefs(this).getString("house-ad-co_applicantSignature")).toString()
            binding.et4.setText(AppPrefs(this).getString("house-ad-dates"))
        }
    }

    private fun setbitmapInShared() {
        val baos = ByteArrayOutputStream()
        var bm = AppPrefs(this).getString("signatureImage")
        val imageAsBytes = Base64.decode(bm!!.toByteArray(), Base64.DEFAULT)
        binding.IVSignature.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))
    }
}