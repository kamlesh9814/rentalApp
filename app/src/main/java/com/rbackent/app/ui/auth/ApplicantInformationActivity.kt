package com.rbackent.app.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.rbackent.app.R
import com.rbackent.app.databinding.ActivityApplicantInformationBinding
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar

class ApplicantInformationActivity : AppCompatActivity() {
    var uploadImage: Uri? = null
    lateinit var binding: ActivityApplicantInformationBinding
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_applicant_information)
        getInstanceData()
        initialize()
    }
    private fun getInstanceData() {
        type = intent.getStringExtra("type")!!
    }
    private fun initialize() {
        getSharedData()
        onClick()
        // code for image Add button Gone
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("setImage") == "true") {
                binding.ivPlus.visibility = View.GONE
            } else {
                binding.ivPlus.visibility = View.VISIBLE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-setImage") == "true") {
                binding.ivPlus.visibility = View.GONE
            } else {
                binding.ivPlus.visibility = View.VISIBLE
            }
        }
    }
    private fun checkValidation(): Boolean {
        binding.apply {
            when {
                et1.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@ApplicantInformationActivity,
                        "Please Enter Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et2.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@ApplicantInformationActivity,
                        "Please Enter Social security Number/ID Number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et3.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@ApplicantInformationActivity,
                        "Please Enter Cell Phone",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                (uploadImage.toString() == "") -> {
                    Toast.makeText(
                        this@ApplicantInformationActivity,
                        "Please Upload drivers license or identification card.",
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
                    AppPrefs(this).setString("filledFill", "true")
                    AppPrefs(this).setString("Name1", binding.et1.text.toString())
                    AppPrefs(this).setString("socialSecurity", binding.et2.text.toString())
                    AppPrefs(this).setString("cellphone", binding.et3.text.toString())
                    AppPrefs(this).setString("images", uploadImage.toString())
                    startActivity(Intent(this@ApplicantInformationActivity,ApartmentsActivity::class.java))
                    finishAffinity()
                } else if (type == ConstantsVar.HOUSE) {
                    ConstantsVar.module_type = type
                    AppPrefs(this).setString("house-filledFill", "true")
                    AppPrefs(this).setString("house-Name1", binding.et1.text.toString())
                    AppPrefs(this).setString("house-socialSecurity", binding.et2.text.toString())
                    AppPrefs(this).setString("house-cellphone", binding.et3.text.toString())
                    AppPrefs(this).setString("house-images", uploadImage.toString())
                    startActivity(Intent(this@ApplicantInformationActivity,ApartmentsActivity::class.java))
                    finishAffinity()
                }
            }
        }

        binding.ccl1.setOnClickListener {
            ImagePicker.with(this@ApplicantInformationActivity)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(201)
        }
    }

    private fun getSharedData() {
        if (type == ConstantsVar.APARTMENT) {
            binding.et1.setText(AppPrefs(this).getString("Name1"))
            binding.et2.setText(AppPrefs(this).getString("socialSecurity"))
            binding.et3.setText(AppPrefs(this).getString("cellphone"))
            uploadImage = Uri.parse(AppPrefs(this).getString("images"))
            Glide.with(this).load(uploadImage).into(binding.ivCircle)

        } else if (type == ConstantsVar.HOUSE) {
            binding.et1.setText(AppPrefs(this).getString("house-Name1"))
            binding.et2.setText(AppPrefs(this).getString("house-socialSecurity"))
            binding.et3.setText(AppPrefs(this).getString("house-cellphone"))
            uploadImage = Uri.parse(AppPrefs(this).getString("house-images"))
            Glide.with(this).load(uploadImage).into(binding.ivCircle)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 201) {
            if (type  == ConstantsVar.APARTMENT) {
                uploadImage = data!!.data!!
                Glide.with(this@ApplicantInformationActivity).load(uploadImage)
                    .into(binding.ivCircle)
                binding.ivPlus.visibility = View.GONE
                AppPrefs(this@ApplicantInformationActivity).setString("setImage", "true")

            } else if (type == ConstantsVar.HOUSE) {
                uploadImage = data!!.data!!
                Glide.with(this@ApplicantInformationActivity).load(uploadImage)
                    .into(binding.ivCircle)
                binding.ivPlus.visibility = View.GONE
                AppPrefs(this@ApplicantInformationActivity).setString("house-setImage", "true")
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}






