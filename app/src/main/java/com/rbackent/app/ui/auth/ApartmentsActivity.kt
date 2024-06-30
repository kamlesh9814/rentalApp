package com.rbackent.app.ui.auth

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.app.rbakent.utils.Status
import com.rbackent.app.R
import com.rbackent.app.databinding.ActivityAppartmentsBinding
import com.rbackent.app.ui.base.MyApplication
import com.rbackent.app.ui.network.Repository
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar
import com.rbackent.app.ui.viewmodel.ViewModel
import com.rbackent.app.ui.viewmodel.ViewModelFactory

class ApartmentsActivity : AppCompatActivity() {
    private val viewModel by viewModels<ViewModel>
    { ViewModelFactory(Application(), Repository()) }
    lateinit var binding: ActivityAppartmentsBinding
    var type = ConstantsVar.APARTMENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_appartments)
        onClick()
        colourChange()
        observer()
        setButtonState()
    }

    private fun setButtonState() {
        if (ConstantsVar.module_type == ConstantsVar.APARTMENT){
            type = ConstantsVar.APARTMENT
           binding.IVApparments.setBackgroundResource(R.drawable.red_drawable)
           binding.txtHouseTV.setBackgroundColor(
                ContextCompat.getColor(
                    this@ApartmentsActivity,
                    R.color.white
                )
            )
            binding.txtHouseTV.setTextColor(
                ContextCompat.getColor(
                    this@ApartmentsActivity,
                    R.color.gray
                )
            )
            binding.IVApparments.setTextColor(
                ContextCompat.getColor(
                    this@ApartmentsActivity,
                    R.color.white
                )
            )
            if (AppPrefs(this@ApartmentsActivity).getString("filledFill") == "true") {
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.ivCheck.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("filled") == "true") {
                binding.ivCheckEmployDetails.visibility = View.VISIBLE
            } else {
                binding.ivCheckEmployDetails.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("ed-filled") == "true") {
                binding.ivTick.visibility = View.VISIBLE
            } else {
                binding.ivTick.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("glow") == "true") {
                binding.ivRental.visibility = View.VISIBLE
            } else {
                binding.ivRental.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("rd-glow") == "true") {
                binding.ivTicked.visibility = View.VISIBLE
            } else {
                binding.ivTicked.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("FilledCo") == "true") {
                binding.ivCo.visibility = View.VISIBLE
            } else {
                binding.ivCo.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("ad-fill") == "true") {
                binding.ivGreen.visibility = View.VISIBLE
            } else {
                binding.ivGreen.visibility = View.GONE
            }
            if (type == ConstantsVar.APARTMENT){
                binding.TVApartments.text = "APARTMENTS RENTAL APPLICATION"
            }

        }else{
            type = ConstantsVar.HOUSE
           binding.IVApparments.setBackgroundColor(
                ContextCompat.getColor(
                    this@ApartmentsActivity,
                    R.color.white
                )
            )
            binding.txtHouseTV.setBackgroundResource(R.drawable.red_drawable)
            binding.txtHouseTV.setTextColor(
                ContextCompat.getColor(
                    this@ApartmentsActivity,
                    R.color.white
                )
            )
            binding.IVApparments.setTextColor(
                ContextCompat.getColor(
                    this@ApartmentsActivity,
                    R.color.gray
                )
            )
            if (AppPrefs(this@ApartmentsActivity).getString("house-filledFill") == "true") {
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.ivCheck.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("house-filled") == "true") {
                binding.ivCheckEmployDetails.visibility = View.VISIBLE
            } else {
                binding.ivCheckEmployDetails.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("house-ed-filled") == "true") {
                binding.ivTick.visibility = View.VISIBLE
            } else {
                binding.ivTick.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("house-glow") == "true") {
                binding.ivRental.visibility = View.VISIBLE
            } else {
                binding.ivRental.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("house-rd-glow") == "true") {
                binding.ivTicked.visibility = View.VISIBLE
            } else {
                binding.ivTicked.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("house-FilledCo") == "true") {
                binding.ivCo.visibility = View.VISIBLE
            } else {
                binding.ivCo.visibility = View.GONE
            }
            if (AppPrefs(this@ApartmentsActivity).getString("house-ad-fill") == "true") {
                binding.ivGreen.visibility = View.VISIBLE
            } else {
                binding.ivGreen.visibility = View.GONE
            }
            if (type == ConstantsVar.HOUSE){
                binding.TVApartments.text = "HOUSE RENTAL APPLICATION"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        employmentDetail1()
        employmentDetail2()
        rentalHistory()
        rentalHistory1()
        applicantInformation()
        coApplicantActivity()
        additionalInformation()
        textChange()
    }

    private fun textChange() {
        if (type == ConstantsVar.APARTMENT){
            binding.TVApartments.text = "APARTMENTS RENTAL APPLICATION"
        } else if (type == ConstantsVar.HOUSE){
            binding.TVApartments.text = "HOUSE RENTAL APPLICATION"
        }
    }

    private fun onClick() {
        binding.rrl2.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, EmploymentDetailActivity::class.java)
            intent.putExtra("from", "Applicant")
            intent.putExtra("typeEmployment", type)
            startActivity(intent)
        }
        binding.rrl1.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, ApplicantInformationActivity::class.java)
            intent.putExtra("type", type)
            startActivity(intent)
        }
        binding.rrl3.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, RentalHistoryActivity::class.java)
            intent.putExtra("rentalHouse", type)
            intent.putExtra("where", "Rental")
            startActivity(intent)
        }
        binding.rrl4.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, CoApplicantInformationActivity::class.java)
            intent.putExtra("typeCoApplicant", type)
            startActivity(intent)
        }
        binding.rrl5.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, EmploymentDetailActivity::class.java)
            intent.putExtra("typeEmployment", type)
            intent.putExtra("from", "Employment-Detail")

            startActivity(intent)
        }
        binding.rrl6.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, RentalHistoryActivity::class.java)
            intent.putExtra("rentalHouse", type)
            intent.putExtra("where", "History")
            startActivity(intent)
        }
        binding.rrl7.setOnClickListener {
            val intent = Intent(this@ApartmentsActivity, AdditionalInformationActivity::class.java)
            intent.putExtra("houseAdditional", type)
            startActivity(intent)

        }
        binding.IVSubmit.setOnClickListener {
            if (checkValidation()) {
                if (type == ConstantsVar.APARTMENT) {
                    viewModel.getUserFormDetail(this,binding.ivCo,binding.ivTick,binding.ivTicked,type)

                } else if (type == ConstantsVar.HOUSE) {
                    viewModel.getUserFormDetail(this,
                        binding.ivCo,
                        binding.ivTick,
                        binding.ivTicked,
                        type)
                }
            }
        }
    }
    private fun applicantInformation() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("filledFill") == "true") {
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.ivCheck.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-filledFill") == "true") {
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.ivCheck.visibility = View.GONE
            }
        }
    }
    private fun employmentDetail1() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("filled") == "true") {
                binding.ivCheckEmployDetails.visibility = View.VISIBLE
            } else {
                binding.ivCheckEmployDetails.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-filled") == "true") {
                binding.ivCheckEmployDetails.visibility = View.VISIBLE
            } else {
                binding.ivCheckEmployDetails.visibility = View.GONE
            }
        }
    }
    private fun employmentDetail2() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("ed-filled") == "true") {
                binding.ivTick.visibility = View.VISIBLE
            } else {
                binding.ivTick.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-ed-filled") == "true") {
                binding.ivTick.visibility = View.VISIBLE
            } else {
                binding.ivTick.visibility = View.GONE
            }
        }
    }
    private fun rentalHistory() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("glow") == "true") {
                binding.ivRental.visibility = View.VISIBLE
            } else {
                binding.ivRental.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-glow") == "true") {
                binding.ivRental.visibility = View.VISIBLE
            } else {
                binding.ivRental.visibility = View.GONE
            }
        }
    }
    private fun rentalHistory1() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("rd-glow") == "true") {
                binding.ivTicked.visibility = View.VISIBLE
            } else {
                binding.ivTicked.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-rd-glow") == "true") {
                binding.ivTicked.visibility = View.VISIBLE
            } else {
                binding.ivTicked.visibility = View.GONE
            }
        }
    }
    private fun coApplicantActivity() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("FilledCo") == "true") {
                binding.ivCo.visibility = View.VISIBLE
            } else {
                binding.ivCo.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-FilledCo") == "true") {
                binding.ivCo.visibility = View.VISIBLE
            } else {
                binding.ivCo.visibility = View.GONE
            }
        }
    }
    private fun additionalInformation() {
        if (type == ConstantsVar.APARTMENT) {
            if (AppPrefs(this).getString("ad-fill") == "true") {
                binding.ivGreen.visibility = View.VISIBLE
            } else {
                binding.ivGreen.visibility = View.GONE
            }
        } else if (type == ConstantsVar.HOUSE) {
            if (AppPrefs(this).getString("house-ad-fill") == "true") {
                binding.ivGreen.visibility = View.VISIBLE
            } else {
                binding.ivGreen.visibility = View.GONE
            }
        }
    }
    private fun checkValidation(): Boolean {
        binding.apply {
            if (ivCheck.visibility != View.VISIBLE) {

                Toast.makeText(
                    this@ApartmentsActivity,
                    "Please Fill Applicant Information",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (ivCheckEmployDetails.visibility != View.VISIBLE) {
                Toast.makeText(
                    this@ApartmentsActivity,
                    "Please Fill Employment Details",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (ivRental.visibility != View.VISIBLE) {
                Toast.makeText(
                    this@ApartmentsActivity,
                    "Please Fill Rental History",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (ivGreen.visibility != View.VISIBLE) {
                Toast.makeText(
                    this@ApartmentsActivity,
                    "Please Fill Additional Information",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                return true
            }
            return false
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun colourChange() {
        binding.apply {
            IVApparments.setOnClickListener {
                type = ConstantsVar.APARTMENT
                IVApparments.setBackgroundResource(R.drawable.red_drawable)
                txtHouseTV.setBackgroundColor(ContextCompat.getColor(this@ApartmentsActivity, R.color.white))
                txtHouseTV.setTextColor(ContextCompat.getColor(this@ApartmentsActivity, R.color.gray))
                IVApparments.setTextColor(ContextCompat.getColor(this@ApartmentsActivity, R.color.white))

                if (AppPrefs(this@ApartmentsActivity).getString("filledFill") == "true") {
                    binding.ivCheck.visibility = View.VISIBLE
                } else {
                    binding.ivCheck.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("filled") == "true") {
                    binding.ivCheckEmployDetails.visibility = View.VISIBLE
                } else {
                    binding.ivCheckEmployDetails.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("ed-filled") == "true") {
                    binding.ivTick.visibility = View.VISIBLE
                } else {
                    binding.ivTick.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("glow") == "true") {
                    binding.ivRental.visibility = View.VISIBLE
                } else {
                    binding.ivRental.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("rd-glow") == "true") {
                    binding.ivTicked.visibility = View.VISIBLE
                } else {
                    binding.ivTicked.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("FilledCo") == "true") {
                    binding.ivCo.visibility = View.VISIBLE
                } else {
                    binding.ivCo.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("ad-fill") == "true") {
                    binding.ivGreen.visibility = View.VISIBLE
                } else {
                    binding.ivGreen.visibility = View.GONE
                }
                if (type == ConstantsVar.APARTMENT){
                    binding.TVApartments.text = "APARTMENTS RENTAL APPLICATION"
                }
            }
            txtHouseTV.setOnClickListener {
                type = ConstantsVar.HOUSE
                IVApparments.setBackgroundColor(ContextCompat.getColor(this@ApartmentsActivity,R.color.white))
                txtHouseTV.setBackgroundResource(R.drawable.red_drawable)
                txtHouseTV.setTextColor(ContextCompat.getColor(this@ApartmentsActivity,R.color.white))
                IVApparments.setTextColor(ContextCompat.getColor(this@ApartmentsActivity, R.color.gray))

                if (AppPrefs(this@ApartmentsActivity).getString("house-filledFill") == "true") {
                    binding.ivCheck.visibility = View.VISIBLE
                } else {
                    binding.ivCheck.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("house-filled") == "true") {
                    binding.ivCheckEmployDetails.visibility = View.VISIBLE
                } else {
                    binding.ivCheckEmployDetails.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("house-ed-filled") == "true") {
                    binding.ivTick.visibility = View.VISIBLE
                } else {
                    binding.ivTick.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("house-glow") == "true") {
                    binding.ivRental.visibility = View.VISIBLE
                } else {
                    binding.ivRental.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("house-rd-glow") == "true") {
                    binding.ivTicked.visibility = View.VISIBLE
                } else {
                    binding.ivTicked.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("house-FilledCo") == "true") {
                    binding.ivCo.visibility = View.VISIBLE
                } else {
                    binding.ivCo.visibility = View.GONE
                }
                if (AppPrefs(this@ApartmentsActivity).getString("house-ad-fill") == "true") {
                    binding.ivGreen.visibility = View.VISIBLE
                } else {
                    binding.ivGreen.visibility = View.GONE
                }
              if (type == ConstantsVar.HOUSE){
                    binding.TVApartments.text = "HOUSE RENTAL APPLICATION"
                }
            }
        }
    }
    private fun observer() {
        viewModel.userFormDetail.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    MyApplication.hideLoader()
                    Toast.makeText(
                        this@ApartmentsActivity,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    val editor: SharedPreferences.Editor =
                        getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE).edit()
                    editor.clear()
                    editor.commit()
                    finish();
                    startActivity(intent);
                   /* if (type == ConstantsVar.APARTMENT){
                        if (AppPrefs(this).getString("filledFill") == "true") {
                            binding.ivCheck.visibility = View.VISIBLE
                        } else {
                            binding.ivCheck.visibility = View.GONE
                        }
                    } else {

                    }*/
                }
                Status.LOADING -> {
                    MyApplication.showLoader(this)
                }
                Status.ERROR -> {
                    MyApplication.hideLoader()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


