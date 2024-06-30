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
import com.rbackent.app.databinding.ActivityC0ApplicantInformationBinding
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar

class CoApplicantInformationActivity : AppCompatActivity() {
    var uploadImage: Uri? = null
    var type = ""
    lateinit var binding: ActivityC0ApplicantInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_c0_applicant_information)
        onClick()
        getIntentData()
        getSharedData()

        // code for image Add button Gone
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("setImage1") == "true") {
                binding.ivAdding.visibility = View.GONE
            } else {
                binding.ivAdding.visibility = View.VISIBLE
            }
        } else if (type == ConstantsVar.HOUSE){
            if (AppPrefs(this).getString("house-setImage1") == "true") {
                binding.ivAdding.visibility = View.GONE
            } else {
                binding.ivAdding.visibility = View.VISIBLE
            }
        }
    }

    private fun getIntentData() {
        type = intent.getStringExtra("typeCoApplicant")!!
    }

    private fun checkValidation(): Boolean {
        binding.apply {
            when {
                et1.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@CoApplicantInformationActivity,
                        "Please Enter Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et2.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@CoApplicantInformationActivity,
                        "Please Enter Social number/ID number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et3.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@CoApplicantInformationActivity,
                        "Please Enter Cell Phone",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                (uploadImage.toString() == "") -> {
                    Toast.makeText(
                        this@CoApplicantInformationActivity,
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
                    AppPrefs(this).setString("FilledCo", "true")
                    AppPrefs(this).setString("Phoned", binding.et1.text.toString())
                    AppPrefs(this).setString("SocialSecurity", binding.et2.text.toString())
                    AppPrefs(this).setString("Cellphone", binding.et3.text.toString())
                    AppPrefs(this).setString("Co-Images", uploadImage.toString())
                    startActivity(Intent(this@CoApplicantInformationActivity,
                        ApartmentsActivity::class.java))
                    finishAffinity()

                } else if (type == ConstantsVar.HOUSE) {
                    ConstantsVar.module_type = type
                    AppPrefs(this).setString("house-FilledCo", "true")
                    AppPrefs(this).setString("house-Phoned", binding.et1.text.toString())
                    AppPrefs(this).setString("house-SocialSecurity", binding.et2.text.toString())
                    AppPrefs(this).setString("house-Cellphone", binding.et3.text.toString())
                    AppPrefs(this).setString("house-Co-Images", uploadImage.toString())
                    startActivity(Intent(this@CoApplicantInformationActivity,
                        ApartmentsActivity::class.java))
                    finishAffinity()
                }
            }
        }
        binding.ccl1.setOnClickListener {
            ImagePicker.with(this@CoApplicantInformationActivity)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(201)
/*            BottomDialogueFragment(object : BottomDialogueFragment.AddImageCallBack {
                override fun getImageList(image: File, imageUri: Uri) {
                    uploadImage = imageUri
                    binding.ivAdding.visibility = View.GONE
                    Glide.with(this@CoApplicantInformationActivity).load(imageUri)
                        .into(binding.IV1)
                    AppPrefs(this@CoApplicantInformationActivity).setString("setImage1","true")
                }
            }, true).show(supportFragmentManager, "")*/
        }
    }

    private fun getSharedData() {
        if (type == ConstantsVar.APARTMENT) {
            binding.et1.setText(AppPrefs(this).getString("Phoned"))
            binding.et2.setText(AppPrefs(this).getString("SocialSecurity"))
            binding.et3.setText(AppPrefs(this).getString("Cellphone"))
            uploadImage = Uri.parse(AppPrefs(this).getString("Co-Images"))
            Glide.with(this).load(uploadImage).into(binding.IV1)
        } else if (type == ConstantsVar.HOUSE){
            binding.et1.setText(AppPrefs(this).getString("house-Phoned"))
            binding.et2.setText(AppPrefs(this).getString("house-SocialSecurity"))
            binding.et3.setText(AppPrefs(this).getString("house-Cellphone"))
            uploadImage = Uri.parse(AppPrefs(this).getString("house-Co-Images"))
            Glide.with(this).load(uploadImage).into(binding.IV1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 201) {
            if (type == ConstantsVar.APARTMENT) {
                uploadImage = data!!.data!!
                Glide.with(this@CoApplicantInformationActivity).load(uploadImage)
                    .into(binding.IV1)
                binding.ivAdding.visibility = View.GONE
                AppPrefs(this@CoApplicantInformationActivity).setString("setImage1", "true")

            } else if (type == ConstantsVar.HOUSE){
                uploadImage = data!!.data!!
                Glide.with(this@CoApplicantInformationActivity).load(uploadImage)
                    .into(binding.IV1)
                binding.ivAdding.visibility = View.GONE
                AppPrefs(this@CoApplicantInformationActivity).setString("house-setImage1", "true")
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}