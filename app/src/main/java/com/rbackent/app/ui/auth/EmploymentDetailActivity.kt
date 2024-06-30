package com.rbackent.app.ui.auth
import android.app.Activity
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
import com.rbackent.app.databinding.ActivityEmploymentDetailsBinding
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar
import java.io.IOException

class EmploymentDetailActivity : AppCompatActivity() {
    var uriImage: Uri? = null
    var from = ""
    var employmentStatus = ""
    var type = ""
    lateinit var binding: ActivityEmploymentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employment_details)
        initialize()
    }
    private fun initialize() {
        buttonGone()
        getIntentData()
        checkBoxClick()
        getSharedData()
        onClick()
    }
    private fun buttonGone() {
        if (type == ConstantsVar.APARTMENT) {
            if (from == "Applicant") {
                if (AppPrefs(this).getString("imageSet") == "true") {
                    binding.ivAddButton.visibility = View.GONE
                } else {
                    binding.ivAddButton.visibility = View.VISIBLE
                }
            } else if (from == "Employment-Detail") {
                if (AppPrefs(this).getString("ed-imageSet") == "true") {
                    binding.ivAddButton.visibility = View.GONE
                } else {
                    binding.ivAddButton.visibility = View.VISIBLE
                }
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (from == "Applicant") {
                if (AppPrefs(this).getString("house-imageSet") == "true") {
                    binding.ivAddButton.visibility = View.GONE
                } else {
                    binding.ivAddButton.visibility = View.VISIBLE
                }
            } else if (from == "Employment-Detail") {
                if (AppPrefs(this).getString("house-ed-imageSet") == "true") {
                    binding.ivAddButton.visibility = View.GONE
                } else {
                    binding.ivAddButton.visibility = View.VISIBLE
                }
            }
        }
    }
    private fun getIntentData() {
        from = intent.getStringExtra("from")!!
        type = intent.getStringExtra("typeEmployment")!!
    }

    private fun checkValidation(): Boolean {
        binding.apply {
            when {
                (!(checkBox.isChecked || checkBox1.isChecked || checkBox2.isChecked || checkBox3.isChecked || checkBox4.isChecked)) -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Fill Employment Status",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et1.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Current Employer",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et2.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Supervisor Name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et3.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Phone",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et4.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Job Title",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et5.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Date Hired",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et6.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Monthly Income",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                et7.text.toString().trim().isNullOrEmpty() -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Other Source Of Income",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                !et9.text.toString().trim().isNullOrEmpty() -> {
                    if (et10.text.toString().trim().isNullOrEmpty()) {
                        Toast.makeText(
                            this@EmploymentDetailActivity,
                            "Please Enter Supervisor's Name",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (et11.text.toString().trim().isNullOrEmpty()) {
                        Toast.makeText(
                            this@EmploymentDetailActivity,
                            "Please Enter Phone Number",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (et12.text.toString().trim().isNullOrEmpty()) {

                        Toast.makeText(
                            this@EmploymentDetailActivity,
                            "Please Enter Period Of Employment",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (uriImage.toString() == "") {
                        Toast.makeText(
                            this@EmploymentDetailActivity,
                            "Please Enter Proof Income",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!CBAccept.isChecked) {
                        Toast.makeText(
                            this@EmploymentDetailActivity,
                            "Please Checked Acceptable Documentation",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        return true
                    }
                    return false
                }
                (uriImage.toString() == "") -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Enter Proof Income",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                (!CBAccept.isChecked) -> {
                    Toast.makeText(
                        this@EmploymentDetailActivity,
                        "Please Checked Acceptable Documentation",
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
    private fun checkBoxClick() {
        binding.apply {
            checkBox.setOnClickListener {
                checkBox.isChecked = true
                checkBox1.isChecked = false
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox4.isChecked = false
                employmentStatus = "fulltime"
            }
            checkBox1.setOnClickListener {
                checkBox.isChecked = false
                checkBox1.isChecked = true
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox4.isChecked = false
                employmentStatus = "parttime"
            }
            checkBox2.setOnClickListener {
                checkBox.isChecked = false
                checkBox1.isChecked = false
                checkBox2.isChecked = true
                checkBox3.isChecked = false
                checkBox4.isChecked = false
                employmentStatus = "student"
            }
            checkBox3.setOnClickListener {
                checkBox.isChecked = false
                checkBox1.isChecked = false
                checkBox2.isChecked = false
                checkBox3.isChecked = true
                checkBox4.isChecked = false
                employmentStatus = "unemployment"
            }
            checkBox4.setOnClickListener {
                checkBox.isChecked = false
                checkBox1.isChecked = false
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox4.isChecked = true
                employmentStatus = "retired"
            }
        }
    }
    private fun onClick() {
        binding.IVDelete.setOnClickListener {
            try {
               // binding.IVDelete.setImageDrawable(R.drawable.remove)
                Toast.makeText(applicationContext, "Image removed!", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "Some error occurred!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.et5.setOnClickListener {
            showDate()

        }
        binding.IVSubmit.setOnClickListener {
            if (checkValidation()) {
                //  code for save Data in SharedPreferences
                if (type == ConstantsVar.APARTMENT) {
                    ConstantsVar.module_type = type
                    if (from == "Applicant") {
                        AppPrefs(this).setString("filled", "true")
                        AppPrefs(this).setString("emp_status", employmentStatus)
                        AppPrefs(this).setString("currentEmployer", binding.et1.text.toString())
                        AppPrefs(this).setString("supervisorName", binding.et2.text.toString())
                        AppPrefs(this).setString("phone", binding.et3.text.toString())
                        AppPrefs(this).setString("jobTitle", binding.et4.text.toString())
                        AppPrefs(this).setString("dateHired", binding.et5.text.toString())
                        AppPrefs(this).setString("monthlyIncome", binding.et6.text.toString())
                        AppPrefs(this).setString("otherSourceOfIncome", binding.et7.text.toString())
                        AppPrefs(this).setString("previousEmployer", binding.et9.text.toString())
                        AppPrefs(this).setString("supervisorsName", binding.et10.text.toString())
                        AppPrefs(this).setString("phones", binding.et11.text.toString())
                        AppPrefs(this).setString("periodOfEmployment", binding.et12.text.toString())
                        AppPrefs(this).setString("image", uriImage.toString())
                        AppPrefs(this).setBoolean("checked", binding.CBAccept.isChecked)
                        startActivity(Intent(this@EmploymentDetailActivity, ApartmentsActivity::class.java))
                        finishAffinity()

                    } else if (from == "Employment-Detail") {
                        ConstantsVar.module_type = type
                        AppPrefs(this).setString("ed-filled", "true")
                        AppPrefs(this).setString("ed-emp_status", employmentStatus)
                        AppPrefs(this).setString("ed-currentEmployer", binding.et1.text.toString())
                        AppPrefs(this).setString("ed-supervisorName", binding.et2.text.toString())
                        AppPrefs(this).setString("ed-phone", binding.et3.text.toString())
                        AppPrefs(this).setString("ed-jobTitle", binding.et4.text.toString())
                        AppPrefs(this).setString("ed-dateHired", binding.et5.text.toString())
                        AppPrefs(this).setString("ed-monthlyIncome", binding.et6.text.toString())
                        AppPrefs(this).setString("ed-otherSourceOfIncome",binding.et7.text.toString())
                        AppPrefs(this).setString("ed-previousEmployer", binding.et9.text.toString())
                        AppPrefs(this).setString("ed-supervisorsName", binding.et10.text.toString())
                        AppPrefs(this).setString("ed-phones", binding.et11.text.toString())
                        AppPrefs(this).setString("ed-periodOfEmployment", binding.et12.text.toString())
                        AppPrefs(this).setString("ed-image", uriImage.toString())
                        AppPrefs(this).setBoolean("ed-checked", binding.CBAccept.isChecked)
                        startActivity(Intent(this@EmploymentDetailActivity, ApartmentsActivity::class.java))
                          finishAffinity()
                    }
                } else if (type == ConstantsVar.HOUSE) {
                    if (from == "Applicant") {
                        AppPrefs(this).setString("house-filled", "true")
                        AppPrefs(this).setString("house-emp_status", employmentStatus)
                        AppPrefs(this).setString("house-currentEmployer",binding.et1.text.toString())
                        AppPrefs(this).setString("house-supervisorName",binding.et2.text.toString())
                        AppPrefs(this).setString("house-phone", binding.et3.text.toString())
                        AppPrefs(this).setString("house-jobTitle", binding.et4.text.toString())
                        AppPrefs(this).setString("house-dateHired", binding.et5.text.toString())
                        AppPrefs(this).setString("house-monthlyIncome", binding.et6.text.toString())
                        AppPrefs(this).setString("house-otherSourceOfIncome",binding.et7.text.toString())
                        AppPrefs(this).setString("house-previousEmployer",binding.et9.text.toString())
                        AppPrefs(this).setString("house-supervisorsName",binding.et10.text.toString())
                        AppPrefs(this).setString("house-phones", binding.et11.text.toString())
                        AppPrefs(this).setString("house-periodOfEmployment",binding.et12.text.toString())
                        AppPrefs(this).setString("house-image", uriImage.toString())
                        AppPrefs(this).setBoolean("house-checked", binding.CBAccept.isChecked)
                        startActivity(Intent(this@EmploymentDetailActivity, ApartmentsActivity::class.java))
                        finishAffinity()

                    } else if (from == "Employment-Detail") {
                        AppPrefs(this).setString("house-ed-filled", "true")
                        AppPrefs(this).setString("house-ed-emp_status", employmentStatus)
                        AppPrefs(this).setString("house-ed-currentEmployer", binding.et1.text.toString())
                        AppPrefs(this).setString("house-ed-supervisorName",binding.et2.text.toString())
                        AppPrefs(this).setString("house-ed-phone", binding.et3.text.toString())
                        AppPrefs(this).setString("house-ed-jobTitle", binding.et4.text.toString())
                        AppPrefs(this).setString("house-ed-dateHired", binding.et5.text.toString())
                        AppPrefs(this).setString("house-ed-monthlyIncome",binding.et6.text.toString())
                        AppPrefs(this).setString("house-ed-otherSourceOfIncome",binding.et7.text.toString())
                        AppPrefs(this).setString("house-ed-previousEmployer",binding.et9.text.toString())
                        AppPrefs(this).setString("house-ed-supervisorsName",binding.et10.text.toString())
                        AppPrefs(this).setString("house-ed-phones", binding.et11.text.toString())
                        AppPrefs(this).setString("house-ed-periodOfEmployment",binding.et12.text.toString())
                        AppPrefs(this).setString("house-ed-image", uriImage.toString())
                        AppPrefs(this).setBoolean("house-ed-checked", binding.CBAccept.isChecked)
                        startActivity(Intent(this@EmploymentDetailActivity,ApartmentsActivity::class.java))
                        finishAffinity()
                    }
                }

            }
        }

        binding.IV1.setOnClickListener {
            ImagePicker.with(this@EmploymentDetailActivity)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(201)
        }
    }
    private fun showDate() {
        binding.et5.setOnClickListener {
            ConstantsVar.showDate(this@EmploymentDetailActivity, binding.et5)
        }
    }

    private fun getSharedData() {
        if (type == ConstantsVar.APARTMENT) {
            if (from == "Applicant") {
                employmentStatus = AppPrefs(this).getString("emp_status")!!
                when (employmentStatus) {
                    "fulltime" -> {
                        binding.checkBox.isChecked = true
                    }
                    "parttime" -> {
                        binding.checkBox1.isChecked = true
                    }
                    "student" -> {
                        binding.checkBox2.isChecked = true
                    }
                    "unemployment" -> {
                        binding.checkBox3.isChecked = true
                    }
                    "retired" -> {
                        binding.checkBox4.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("currentEmployer"))
                binding.et2.setText(AppPrefs(this).getString("supervisorName"))
                binding.et3.setText(AppPrefs(this).getString("phone"))
                binding.et4.setText(AppPrefs(this).getString("jobTitle"))
                binding.et5.setText(AppPrefs(this).getString("dateHired"))
                binding.et6.setText(AppPrefs(this).getString("monthlyIncome"))
                binding.et7.setText(AppPrefs(this).getString("otherSourceOfIncome"))
                binding.et9.setText(AppPrefs(this).getString("previousEmployer"))
                binding.et10.setText(AppPrefs(this).getString("supervisorsName"))
                binding.et11.setText(AppPrefs(this).getString("phones"))
                binding.et12.setText(AppPrefs(this).getString("periodOfEmployment"))
                uriImage = Uri.parse(AppPrefs(this).getString("image"))
                Glide.with(this).load(uriImage).into(binding.IV1)
                binding.CBAccept.isChecked = AppPrefs(this).getBoolean("checked")

            } else if (from == "Employment-Detail") {
                employmentStatus = AppPrefs(this).getString("ed-emp_status")!!
                when (employmentStatus) {
                    "fulltime" -> {
                        binding.checkBox.isChecked = true
                    }
                    "parttime" -> {
                        binding.checkBox1.isChecked = true
                    }
                    "student" -> {
                        binding.checkBox2.isChecked = true
                    }
                    "unemployment" -> {
                        binding.checkBox3.isChecked = true
                    }
                    "retired" -> {
                        binding.checkBox4.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("ed-currentEmployer"))
                binding.et2.setText(AppPrefs(this).getString("ed-supervisorName"))
                binding.et3.setText(AppPrefs(this).getString("ed-phone"))
                binding.et4.setText(AppPrefs(this).getString("ed-jobTitle"))
                binding.et5.setText(AppPrefs(this).getString("ed-dateHired"))
                binding.et6.setText(AppPrefs(this).getString("ed-monthlyIncome"))
                binding.et7.setText(AppPrefs(this).getString("ed-otherSourceOfIncome"))
                binding.et9.setText(AppPrefs(this).getString("ed-previousEmployer"))
                binding.et10.setText(AppPrefs(this).getString("ed-supervisorsName"))
                binding.et11.setText(AppPrefs(this).getString("ed-phones"))
                binding.et12.setText(AppPrefs(this).getString("ed-periodOfEmployment"))
                uriImage = Uri.parse(AppPrefs(this).getString("ed-image"))
                Glide.with(this).load(uriImage).into(binding.IV1)
                binding.CBAccept.isChecked = AppPrefs(this).getBoolean("ed-checked")
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (from == "Applicant") {
                employmentStatus = AppPrefs(this).getString("house-emp_status")!!
                when (employmentStatus) {
                    "fulltime" -> {
                        binding.checkBox.isChecked = true
                    }
                    "parttime" -> {
                        binding.checkBox1.isChecked = true
                    }
                    "student" -> {
                        binding.checkBox2.isChecked = true
                    }
                    "unemployment" -> {
                        binding.checkBox3.isChecked = true
                    }
                    "retired" -> {
                        binding.checkBox4.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("house-currentEmployer"))
                binding.et2.setText(AppPrefs(this).getString("house-supervisorName"))
                binding.et3.setText(AppPrefs(this).getString("house-phone"))
                binding.et4.setText(AppPrefs(this).getString("house-jobTitle"))
                binding.et5.setText(AppPrefs(this).getString("house-dateHired"))
                binding.et6.setText(AppPrefs(this).getString("house-monthlyIncome"))
                binding.et7.setText(AppPrefs(this).getString("house-otherSourceOfIncome"))
                binding.et9.setText(AppPrefs(this).getString("house-previousEmployer"))
                binding.et10.setText(AppPrefs(this).getString("house-supervisorsName"))
                binding.et11.setText(AppPrefs(this).getString("house-phones"))
                binding.et12.setText(AppPrefs(this).getString("house-periodOfEmployment"))
                uriImage = Uri.parse(AppPrefs(this).getString("house-image"))
                Glide.with(this).load(uriImage).into(binding.IV1)
                binding.CBAccept.isChecked = AppPrefs(this).getBoolean("house-checked")

            } else if (from == "Employment-Detail") {
                employmentStatus = AppPrefs(this).getString("house-ed-emp_status")!!
                when (employmentStatus) {
                    "fulltime" -> {
                        binding.checkBox.isChecked = true
                    }
                    "parttime" -> {
                        binding.checkBox1.isChecked = true
                    }
                    "student" -> {
                        binding.checkBox2.isChecked = true
                    }
                    "unemployment" -> {
                        binding.checkBox3.isChecked = true
                    }
                    "retired" -> {
                        binding.checkBox4.isChecked = true
                    }
                }
                binding.et1.setText(AppPrefs(this).getString("house-ed-currentEmployer"))
                binding.et2.setText(AppPrefs(this).getString("house-ed-supervisorName"))
                binding.et3.setText(AppPrefs(this).getString("house-ed-phone"))
                binding.et4.setText(AppPrefs(this).getString("house-ed-jobTitle"))
                binding.et5.setText(AppPrefs(this).getString("house-ed-dateHired"))
                binding.et6.setText(AppPrefs(this).getString("house-ed-monthlyIncome"))
                binding.et7.setText(AppPrefs(this).getString("house-ed-otherSourceOfIncome"))
                binding.et9.setText(AppPrefs(this).getString("house-ed-previousEmployer"))
                binding.et10.setText(AppPrefs(this).getString("house-ed-supervisorsName"))
                binding.et11.setText(AppPrefs(this).getString("house-ed-phones"))
                binding.et12.setText(AppPrefs(this).getString("house-ed-periodOfEmployment"))
                uriImage = Uri.parse(AppPrefs(this).getString("house-ed-image"))
                Glide.with(this).load(uriImage).into(binding.IV1)
                binding.CBAccept.isChecked = AppPrefs(this).getBoolean("house-ed-checked")
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (type == ConstantsVar.APARTMENT) {
                uriImage = data!!.data!!
                Glide.with(this@EmploymentDetailActivity).load(uriImage)
                    .into(binding.IV1)
                binding.ivAddButton.visibility = View.GONE
                AppPrefs(this@EmploymentDetailActivity).setString("imageSet", "true")

            } else if (type == ConstantsVar.HOUSE) {
                uriImage = data!!.data!!
                Glide.with(this@EmploymentDetailActivity).load(uriImage)
                    .into(binding.IV1)
                binding.ivAddButton.visibility = View.GONE
                AppPrefs(this@EmploymentDetailActivity).setString("house-imageSet", "true")
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}


