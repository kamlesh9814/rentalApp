package com.rbackent.app.ui.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Base64
import android.view.View
import com.rbackent.app.ui.model.final_api.EmploymentDetailResponse
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileDescriptor
import java.io.IOException

class Repository {
    open fun convertBitmapToByteArrayUncompressed(bitmap: Bitmap): ByteArray? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream)
        return stream.toByteArray()
    }

    fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
        var parcelFileDescriptor: ParcelFileDescriptor? = null
        return try {
            parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
            val fileDescriptor: FileDescriptor? = parcelFileDescriptor?.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor?.close()
            image
        } catch (e: Exception) {
            //Log.e(TAG, "Failed to load image.", e)
            null
        } finally {
            try {
                parcelFileDescriptor?.close()
            } catch (e: IOException) {
                e.printStackTrace()
                //Log.e(TAG, "Error closing ParcelFile Descriptor")
            }
        }
    }

    open fun getAlphaNumericString(): String? {
        val n = 20
        val AlphaNumericString = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ" /*+ "0123456789"*/
                + "abcdefghijklmnopqrstuvxyz")
        // create StringBuffer size of AlphaNumericString
        val sb = StringBuilder(n)
        for (i in 0 until n) {
            val index = (AlphaNumericString.length
                    * Math.random()).toInt()
            sb.append(
                AlphaNumericString[index]
            )
        }
        return sb.toString()
    }
    suspend fun userFormData(
        context: Context,
        ivCo: CircleImageView,
        ivTick: CircleImageView,
        ivTicked: CircleImageView,
        type: String,
    ): Response<EmploymentDetailResponse>? {

        // Applicant information Activity
        var name = (AppPrefs(context).getString("Name1"))
        var socialSecurity = (AppPrefs(context).getString("socialSecurity"))
        var cellphone = (AppPrefs(context).getString("cellphone"))
        var images = Uri.parse(AppPrefs(context).getString("images"))

        // Employment Detail Activity
        var employmentStatus = AppPrefs(context).getString("emp_status")
        var currentEmployer = AppPrefs(context).getString("currentEmployer")
        var supervisorName = AppPrefs(context).getString("supervisorName")
        var cell = AppPrefs(context).getString("phone")
        var jobTitle = AppPrefs(context).getString("jobTitle")
        var dateHired = AppPrefs(context).getString("dateHired")
        var monthlyIncome = AppPrefs(context).getString("monthlyIncome")
        var otherSourceOfIncome = AppPrefs(context).getString("otherSourceOfIncome")
        var previousEmployer = AppPrefs(context).getString("previousEmployer")
        var supervisorsName = AppPrefs(context).getString("supervisorsName")
        var Phones = AppPrefs(context).getString("phones")
        var periodOfEmployment = AppPrefs(context).getString("periodOfEmployment")
        var image = Uri.parse(AppPrefs(context).getString("image"))

        // Rental History Activity
        var currentAddress = (AppPrefs(context).getString("current-Address"))
        var howlong = (AppPrefs(context).getString("how-long"))
        var monthlyrent = (AppPrefs(context).getString("monthly-rent"))
        var landlord = (AppPrefs(context).getString("land-lord"))
        var landcontact = (AppPrefs(context).getString("land-contact"))
        var reasonsfor = (AppPrefs(context).getString("reasons-for"))
        var pvaddress = (AppPrefs(context).getString("pv-address"))
        var howlongstay = (AppPrefs(context).getString("how-long-stay"))
        var monthlyRent = (AppPrefs(context).getString("monthly-rents"))
        var landname = (AppPrefs(context).getString("land-name"))
        var llcnumber = (AppPrefs(context).getString("llc-number"))
        var reasonsleaving = (AppPrefs(context).getString("reasons-leaving"))
        var haveYou = AppPrefs(context).getString("haveYou")
        var radio1 = AppPrefs(context).getString("radio1")
        var radio2 = AppPrefs(context).getString("radio2")
        var youanswered = (AppPrefs(context).getString("you-answered"))

        // Additional Information Activity
        var addate = (AppPrefs(context).getString("ad-date"))
        var signatureBitmap: Bitmap? = null
        var adcoapplicantSignature = AppPrefs(context).getString("signatureImage")!!
        if (adcoapplicantSignature != "") {
            val b: ByteArray = Base64.decode(adcoapplicantSignature, Base64.DEFAULT)
            signatureBitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
        }
        var adDates = (AppPrefs(context).getString("ad-dates"))
        var signaturessssBitmap: Bitmap? = null
        var Coapplicationsignature = AppPrefs(context).getString("ad-co_applicantSignature")
        if (Coapplicationsignature != "") {
            val b: ByteArray = Base64.decode(Coapplicationsignature, Base64.DEFAULT)
            signaturessssBitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
        }

        // Co-Employment Detail Activity
        var edemploymentStatus = AppPrefs(context).getString("ed-emp_status")
        var edcurrentEmployer = (AppPrefs(context).getString("ed-currentEmployer"))
        var edsupervisorName = (AppPrefs(context).getString("ed-supervisorName"))
        var edphone = (AppPrefs(context).getString("ed-phone"))
        var edjobTitle = (AppPrefs(context).getString("ed-jobTitle"))
        var eddateHired = (AppPrefs(context).getString("ed-dateHired"))
        var edmonthlyIncome = (AppPrefs(context).getString("ed-monthlyIncome"))
        var edotherSourceOfIncome = (AppPrefs(context).getString("ed-otherSourceOfIncome"))
        var edpreviousEmployer = (AppPrefs(context).getString("ed-previousEmployer"))
        var edsupervisorsName = (AppPrefs(context).getString("ed-supervisorsName"))
        var eddphones = (AppPrefs(context).getString("ed-phones"))
        var edperiodOfEmployment = (AppPrefs(context).getString("ed-periodOfEmployment"))
        var coImaged = Uri.parse(AppPrefs(context).getString("ed-image"))

        // CO-Rental History Activity
        var rdcurrentAddress = (AppPrefs(context).getString("rd-current-Address"))
        var rdhowlong = (AppPrefs(context).getString("rd-how-long"))
        var rdmonthlyrent = (AppPrefs(context).getString("rd-monthly-rent"))
        var rdlandlord = (AppPrefs(context).getString("rd-land-lord"))
        var rdlandcontact = (AppPrefs(context).getString("rd-land-contact"))
        var rdreasonsfor = (AppPrefs(context).getString("rd-reasons-for"))
        var rdpvaddress = (AppPrefs(context).getString("rd-pv-address"))
        var rdhowlongstay = (AppPrefs(context).getString("rd-how-long-stay"))
        var rdmonthlyRent = (AppPrefs(context).getString("rd-monthly-rent"))
        var rdlandname = (AppPrefs(context).getString("rd-land-name"))
        var rdllcnumber = (AppPrefs(context).getString("rd-llc-number"))
        var rdreasonsleaving = (AppPrefs(context).getString("rd-reasons-leaving"))
        var rdhaveYou = AppPrefs(context).getString("rd-haveYou")
        var rdradio1 = AppPrefs(context).getString("rd-radio1")
        var rdradio2 = AppPrefs(context).getString("rd-radio2")
        var rdyouanswered = (AppPrefs(context).getString("rd-you-answered"))

        // Co_Applicant Information Activity
        var Phoned = (AppPrefs(context).getString("Phoned"))
        var SocialSecurity = (AppPrefs(context).getString("SocialSecurity"))
        var Cellphone = (AppPrefs(context).getString("Cellphone"))
        var tashbir = Uri.parse(AppPrefs(context).getString("Co-Images"))

        // House-Applicant Information
        var houseName = (AppPrefs(context).getString("house-Name1"))
        var housesocialSecurity = (AppPrefs(context).getString("house-socialSecurity"))
        var housecellphone = (AppPrefs(context).getString("house-cellphone"))
        var houseimages = Uri.parse(AppPrefs(context).getString("house-images"))

        // House-Employment Details
        var houseedempstatus = AppPrefs(context).getString("house-emp_status")!!
        var housecurrentEmployer = (AppPrefs(context).getString("house-currentEmployer"))
        var housesupervisorName = (AppPrefs(context).getString("house-supervisorName"))
        var housephone = (AppPrefs(context).getString("house-phone"))
        var housejobTitle = (AppPrefs(context).getString("house-jobTitle"))
        var housedateHired = (AppPrefs(context).getString("house-dateHired"))
        var housemonthlyIncome = (AppPrefs(context).getString("house-monthlyIncome"))
        var houseotherSourceOfIncome = (AppPrefs(context).getString("house-otherSourceOfIncome"))
        var housepreviousEmployer = (AppPrefs(context).getString("house-previousEmployer"))
        var housesupervisorsName = (AppPrefs(context).getString("house-supervisorsName"))
        var housephones = (AppPrefs(context).getString("house-phones"))
        var houseperiodOfEmployment = (AppPrefs(context).getString("house-periodOfEmployment"))
        var houseimage = Uri.parse(AppPrefs(context).getString("house-image"))

        // House-Rental History
        var houserdcurrentAddressrd = (AppPrefs(context).getString("house-current-Address"))
        var houserdhowlonged = (AppPrefs(context).getString("house-how-long"))
        var houserdmonthlyrentrd = (AppPrefs(context).getString("house-monthly-rent"))
        var houserdlandlorded = (AppPrefs(context).getString("house-land-lord"))
        var houserdlandcontactrd = (AppPrefs(context).getString("house-land-contact"))
        var houserdreasonsfored = (AppPrefs(context).getString("house-reasons-for"))
        var houserdpvaddressrd = (AppPrefs(context).getString("house-pv-address"))
        var houserdhowlongstayed = (AppPrefs(context).getString("house-how-long-stay"))
        var houserdmonthlyrentsrd = (AppPrefs(context).getString("house-monthly-rents"))
        var houserdlandnamerd = (AppPrefs(context).getString("house-land-name"))
        var houserdllcnumberd = (AppPrefs(context).getString("house-llc-number"))
        var houserdreasonsleavingrd = (AppPrefs(context).getString("house-reasons-leaving"))
        var houserdyouansweredrd = (AppPrefs(context).getString("house-you-answered"))
        var househaveYou = AppPrefs(context).getString("house-haveYou")!!
        var houseradio1= AppPrefs(context).getString("house-radio1")!!
        var houseradio2 = AppPrefs(context).getString("house-radio2")!!

        // House-Co-Applicant Information
        var housePhoned = (AppPrefs(context).getString("house-Phoned"))
        var houseSocialSecurity = (AppPrefs(context).getString("house-SocialSecurity"))
        var houseCellphone = (AppPrefs(context).getString("house-Cellphone"))
        var houseCoImages =Uri.parse(AppPrefs(context).getString("house-Co-Images"))

        // House-Co-Employment Details
        var houseedempstatuss =  AppPrefs(context).getString("house-ed-emp_status")!!
        var houseedcurrentEmployer = (AppPrefs(context).getString("house-ed-currentEmployer"))
        var houseedsupervisorName = (AppPrefs(context).getString("house-ed-supervisorName"))
        var houseedphone = (AppPrefs(context).getString("house-ed-phone"))
        var houseedjobTitle = (AppPrefs(context).getString("house-ed-jobTitle"))
        var houseeddateHired = (AppPrefs(context).getString("house-ed-dateHired"))
        var houseedmonthlyIncome = (AppPrefs(context).getString("house-ed-monthlyIncome"))
        var houseedotherSourceOfIncome =
            (AppPrefs(context).getString("house-ed-otherSourceOfIncome"))
        var houseedpreviousEmployer = (AppPrefs(context).getString("house-ed-previousEmployer"))
        var houseedsupervisorsName = (AppPrefs(context).getString("house-ed-supervisorsName"))
        var houseedphones = (AppPrefs(context).getString("house-ed-phones"))
        var houseedperiodOfEmployment = (AppPrefs(context).getString("house-ed-periodOfEmployment"))
        var houseedimage = Uri.parse(AppPrefs(context).getString("house-ed-image"))

        // House Co-Rental History
        var houserdcurrentAddress = (AppPrefs(context).getString("house-rd-current-Address"))
        var houserdhowlong = (AppPrefs(context).getString("house-rd-how-long"))
        var houserdmonthlyrent = (AppPrefs(context).getString("house-rd-monthly-rent"))
        var houserdlandlord = (AppPrefs(context).getString("house-rd-land-lord"))
        var houserdlandcontact = (AppPrefs(context).getString("house-rd-land-contact"))
        var houserdreasonsfor = (AppPrefs(context).getString("house-rd-reasons-for"))
        var houserdpvaddress = (AppPrefs(context).getString("house-rd-pv-address"))
        var houserdhowlongstay = (AppPrefs(context).getString("house-rd-how-long-stay"))
        var houserdmonthlyrents = (AppPrefs(context).getString("house-rd-monthly-rents"))
        var houserdlandname = (AppPrefs(context).getString("house-rd-land-name"))
        var houserdllcnumber = (AppPrefs(context).getString("house-rd-llc-number"))
        var houserdreasonsleaving = (AppPrefs(context).getString("house-rd-reasons-leaving"))
        var houserdyouanswered = (AppPrefs(context).getString("house-rd-you-answered"))
        var houserdhaveYou = AppPrefs(context).getString("house-rd-haveYou")!!
       var houserdradio1 =  AppPrefs(context).getString("house-rd-radio1")!!
       var houserdradio2 =  AppPrefs(context).getString("house-rd-radio2")!!

        // House-Additional Activity

        var houseSignatureBitmap: Bitmap? = null
        var houseAdapplicantSignature = AppPrefs(context).getString("house-ad-applicantSignature")!!
        if (houseAdapplicantSignature != "") {
            val b: ByteArray = Base64.decode(houseAdapplicantSignature, Base64.DEFAULT)
            houseSignatureBitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
        }
        var houseCOSignatureBitmap: Bitmap? = null
        var houseAdCoapplicantSignature = AppPrefs(context).getString("house-ad-co_applicantSignature")!!
        if (houseAdCoapplicantSignature != "") {
            val b: ByteArray = Base64.decode(houseAdCoapplicantSignature, Base64.DEFAULT)
            houseCOSignatureBitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
        }
        var houseaddate = (AppPrefs(context).getString("house-ad-date"))
        var houseaddates = (AppPrefs(context).getString("house-ad-dates"))


        // check for Co- Rental History
        var Co_current_address: RequestBody? = null
        var Co_how_long_residing_address: RequestBody? = null
        var Co_monthly_rent: RequestBody? = null
        var Co_landlord_name: RequestBody? = null
        var Co_landlord_contact_number: RequestBody? = null
        var Co_reason_leaving_property: RequestBody? = null
        var Co_previos_address: RequestBody? = null
        var Co_how_long_did_you_stay_at_address: RequestBody? = null
        var Co_monthly_rents: RequestBody? = null
        var Co_landlords_name: RequestBody? = null
        var Co_landlords_contact: RequestBody? = null
        var Co_reasons_for_leaving: RequestBody? = null
        var Co_how_you_ever_been: RequestBody? = null
        var Co_how_you_missed_two_payment: RequestBody? = null
        var Co_have_you_everrefused: RequestBody? = null
        var Co_ifyouhaveanswered_yesno: RequestBody? = null
        // Co-Applicant Check
        var phoned: RequestBody? = null
        var securitySocial: RequestBody? = null
        var phoneCell: RequestBody? = null
        var picture: MultipartBody.Part?= null
        // check of CO-EmploymentDetail Activity
        var Co_employment_status: RequestBody? = null
        var Co_current_employer: RequestBody? = null
        var Co_supervisors_name: RequestBody? = null
        var Co_phone: RequestBody? = null
        var Co_job_title: RequestBody? = null
        var Co_date_hired: RequestBody? = null
        var Co_monthly_income: RequestBody? = null
        var Co_other_source_income: RequestBody? = null
        var Co_previous_employer_ifany: RequestBody? = null
        var Co_supervisor_name: RequestBody? = null
        var Co_phones: RequestBody? = null
        var Co_period_of_employment: RequestBody? = null
        var Co_proof_of_income: MultipartBody.Part? =null

        // Check of Applicant Information Activity
        var Name: RequestBody? = null
        var SocialSecuritynumberIDnumber: RequestBody? = null
        var CellPhone: RequestBody? = null
        var AndaplaceforthemtoUploadDriversLicense:MultipartBody.Part? =null

        // check for Employment Detail Activity
        var employment_status: RequestBody? = null
        var current_employer: RequestBody? = null
        var supervisors_name: RequestBody? = null
        var phone: RequestBody? = null
        var job_title: RequestBody? = null
        var date_hired: RequestBody? = null
        var monthly_income: RequestBody? = null
        var other_source_income: RequestBody? = null
        var previous_employment: RequestBody? = null
        var supervisor_name: RequestBody? = null
        var phones: RequestBody? = null
        var period_of_employment: RequestBody? = null
        var proof_of_income: MultipartBody.Part? = null

        //Check for  Rental History Activity
        var current_address:RequestBody? = null
        var how_long_residing_address:RequestBody? = null
        var monthlys_rent:RequestBody? = null
        var landlord_name:RequestBody? = null
        var landlord_contact_number:RequestBody? = null
        var reason_leaving_property:RequestBody? = null
        var previos_address:RequestBody? = null
        var how_long_did_you_stay_at_address:RequestBody? = null
        var monthly_rent:RequestBody? = null
        var landlords_name:RequestBody? = null
        var landlords_contact:RequestBody? = null
        var reasons_for_leaving:RequestBody? = null
        var how_you_ever_been:RequestBody? = null
        var how_you_missed_two_payment:RequestBody? = null
        var have_you_everrefused:RequestBody? = null
        var ifyouhaveanswered_yesno:RequestBody? = null

        // Check for Additional Information Activity
        var date:RequestBody? = null
        var Applicant_signature:MultipartBody.Part? =null
        var dates:RequestBody? = null
        var co_application_signature:MultipartBody.Part? =null

        if (type == ConstantsVar.APARTMENT){
            // Applicant information Activity
             Name = name.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             SocialSecuritynumberIDnumber =
                socialSecurity.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             CellPhone = cellphone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val AndaplaceforthemtoUploadDriversLicenses: RequestBody? =
                convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, images)!!)?.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it
                    )
                }
             AndaplaceforthemtoUploadDriversLicense = AndaplaceforthemtoUploadDriversLicenses?.let {
                MultipartBody.Part.createFormData(
                    " Applicantinfo[AndaplaceforthemtoUploadDriversLicense]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }

            // Employment Detail Activity
            employment_status =
                employmentStatus.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             current_employer =
                currentEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             supervisors_name =
                supervisorName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             phone = cell.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             job_title = jobTitle.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             date_hired = dateHired.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             monthly_income =
                monthlyIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             other_source_income =
                otherSourceOfIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            previous_employment =
                previousEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             supervisor_name =
                supervisorsName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             phones = Phones.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             period_of_employment =
                periodOfEmployment.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val proofofincome: RequestBody? =
                convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, image)!!)?.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it)
                }
             proof_of_income = proofofincome?.let {
                MultipartBody.Part.createFormData(
                    "EmploymentDetails[proof_of_income]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }

            // Rental History Activity
             current_address =
                currentAddress.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             how_long_residing_address =
                howlong.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             monthlys_rent =
                monthlyrent.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             landlord_name = landlord.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             landlord_contact_number =
                landcontact.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             reason_leaving_property =
                reasonsfor.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             previos_address =
                pvaddress.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             how_long_did_you_stay_at_address =
                howlongstay.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             monthly_rent =
                monthlyRent.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             landlords_name = landname.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             landlords_contact =
                llcnumber.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             reasons_for_leaving =
                reasonsleaving.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             how_you_ever_been =haveYou
                .toString().toRequestBody("plain/text".toMediaTypeOrNull())
             how_you_missed_two_payment =radio1
                .toString().toRequestBody("plain/text".toMediaTypeOrNull())
             have_you_everrefused =
                radio2.toString().toRequestBody("plain/text".toMediaTypeOrNull())
             ifyouhaveanswered_yesno =
                 youanswered.toString().toRequestBody("plain/text".toMediaTypeOrNull())

            // Additional Information Activity
             date = addate.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val Applicantsignature: RequestBody? =
                convertBitmapToByteArrayUncompressed(signatureBitmap!!)!!.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it)
                }
            Applicant_signature = Applicantsignature?.let {
                MultipartBody.Part.createFormData(
                    " Additional_informatation[Applicant_signature]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }
             dates = adDates.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val coapplicationsignature: RequestBody? =
                convertBitmapToByteArrayUncompressed(signaturessssBitmap!!)?.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it)
                }
             co_application_signature = coapplicationsignature?.let {
                MultipartBody.Part.createFormData(
                    "Additional_informatation[co_application_signature]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }

            if (ivCo.visibility == View.VISIBLE) {
                // Co-Applicant Information Activity
                phoned = Phoned.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                securitySocial =
                    SocialSecurity.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                phoneCell = Cellphone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                val coImage: RequestBody? =
                    convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, tashbir)!!)?.let {
                        RequestBody.create(
                            "multipart/form-data".toMediaTypeOrNull(), it)
                    }
                picture = coImage?.let {
                    MultipartBody.Part.createFormData(
                        "CoApplicantinfo[AndaplaceforthemtoUploadDriversLicenses]",
                        getAlphaNumericString()!!.toString() + ".jpg",
                        it)
                }
            } else {
                //  copy Co-Applicant Information Activity
                phoned = "".toRequestBody("plain/text".toMediaTypeOrNull())
                securitySocial =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                phoneCell = "".toRequestBody("plain/text".toMediaTypeOrNull())

                var vFileName = ""
                var vFileBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
                picture = MultipartBody.Part.createFormData(
                    "image",
                    vFileName,
                    vFileBody
                )
            }


            if (ivTick.visibility == View.VISIBLE) {
                // Co_Employment Detail Activity
                Co_employment_status =
                    edemploymentStatus.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_current_employer =
                    edcurrentEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisors_name =
                    edsupervisorName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phone = edphone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_job_title = edjobTitle.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_date_hired =
                    eddateHired.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_income =
                    edmonthlyIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_other_source_income =
                    edotherSourceOfIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previous_employer_ifany =
                    edpreviousEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisor_name =
                    edsupervisorsName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phones = eddphones.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_period_of_employment =
                    edperiodOfEmployment.toString().toRequestBody("plain/text".toMediaTypeOrNull())

                val Coproofofincome: RequestBody? =
                    convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, coImaged)!!)?.let {
                        RequestBody.create(
                            "multipart/form-data".toMediaTypeOrNull(), it
                        )
                    }
                Co_proof_of_income = Coproofofincome?.let {
                    MultipartBody.Part.createFormData(
                        "Co_EmploymentDetails[Co_proof_of_income]",
                        getAlphaNumericString()!!.toString() + ".jpg",
                        it)
                }
            } else {
                //  Copy Co_Employment Detail Activity
                Co_employment_status =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_current_employer =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisors_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phone = "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_job_title = "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_date_hired =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_income =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_other_source_income =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previous_employer_ifany =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisor_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phones = "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_period_of_employment = "".toRequestBody("plain/text".toMediaTypeOrNull())
                var vFileNames = ""
                var vFileBodys = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
                Co_proof_of_income = MultipartBody.Part.createFormData(
                    "image",
                    vFileNames,
                    vFileBodys
                )
            }


            if (ivTicked.visibility == View.VISIBLE) {
                // CO-Rental History Activity
                Co_current_address =
                    rdcurrentAddress.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_residing_address =
                    rdhowlong.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rent =
                    rdmonthlyrent.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_name =
                    rdlandlord.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_contact_number =
                    rdlandcontact.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reason_leaving_property =
                    rdreasonsfor.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previos_address =
                    rdpvaddress.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_did_you_stay_at_address =
                    rdhowlongstay.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rents =
                    rdmonthlyRent.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_name =
                    rdlandname.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_contact =
                    rdllcnumber.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reasons_for_leaving =
                    rdreasonsleaving.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_ever_been =rdhaveYou
                    .toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_missed_two_payment =
                    rdradio1.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_have_you_everrefused =rdradio2
                    .toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_ifyouhaveanswered_yesno =
                    rdyouanswered.toString().toRequestBody("plain/text".toMediaTypeOrNull())

            } else {
                //  copy Co employment details
                Co_current_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_residing_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rent =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_contact_number =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reason_leaving_property =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previos_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_did_you_stay_at_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rents =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_contact =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reasons_for_leaving =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_ever_been =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_missed_two_payment =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_have_you_everrefused =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_ifyouhaveanswered_yesno =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
            }

        }else if (type == ConstantsVar.HOUSE){
            // Applicant information Activity
            Name = houseName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            SocialSecuritynumberIDnumber =
                housesocialSecurity.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            CellPhone = housecellphone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val AndaplaceforthemtoUploadDriversLicenses: RequestBody? =
                convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, houseimages)!!)?.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it
                    )
                }
            AndaplaceforthemtoUploadDriversLicense = AndaplaceforthemtoUploadDriversLicenses?.let {
                MultipartBody.Part.createFormData(
                    " Applicantinfo[AndaplaceforthemtoUploadDriversLicense]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }

            // Employment Detail Activity
            employment_status =
                houseedempstatus.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            current_employer =
                housecurrentEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            supervisors_name =
                housesupervisorName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            phone = housephone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            job_title = housejobTitle.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            date_hired = housedateHired.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            monthly_income =
                housemonthlyIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            other_source_income =
                houseotherSourceOfIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            previous_employment =
                housepreviousEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            supervisor_name =
                housesupervisorsName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            phones = housephones.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            period_of_employment =
                houseperiodOfEmployment.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val proofofincome: RequestBody? =
                convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, houseimage)!!)?.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it)
                }
            proof_of_income = proofofincome?.let {
                MultipartBody.Part.createFormData(
                    "EmploymentDetails[proof_of_income]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }

            // Rental History Activity
            current_address =
                houserdcurrentAddressrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            how_long_residing_address =
                houserdhowlonged.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            monthlys_rent =
                houserdmonthlyrentrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            landlord_name = houserdlandlorded.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            landlord_contact_number =
                houserdlandcontactrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            reason_leaving_property =
                houserdreasonsfored.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            previos_address =
                houserdpvaddressrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            how_long_did_you_stay_at_address =
                houserdhowlongstayed.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            monthly_rent =
                houserdmonthlyrentsrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            landlords_name = houserdlandnamerd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            landlords_contact =
                houserdllcnumberd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            reasons_for_leaving =
                houserdreasonsleavingrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            how_you_ever_been =
                househaveYou.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            how_you_missed_two_payment =
                houseradio1.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            have_you_everrefused =
                houseradio2.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            ifyouhaveanswered_yesno =
                houserdyouansweredrd.toString().toRequestBody("plain/text".toMediaTypeOrNull())

            // Additional Information Activity
            date = houseaddate.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val Applicantsignature: RequestBody? =
                convertBitmapToByteArrayUncompressed(houseSignatureBitmap!!)!!.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it)
                }
            Applicant_signature = Applicantsignature?.let {
                MultipartBody.Part.createFormData(
                    " Additional_informatation[Applicant_signature]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }
            dates = houseaddates.toString().toRequestBody("plain/text".toMediaTypeOrNull())
            val coapplicationsignature: RequestBody? =
                convertBitmapToByteArrayUncompressed(houseCOSignatureBitmap!!)?.let {
                    RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(), it)
                }
            co_application_signature = coapplicationsignature?.let {
                MultipartBody.Part.createFormData(
                    "Additional_informatation[co_application_signature]",
                    getAlphaNumericString()!!.toString() + ".jpg",
                    it)
            }

            if (ivCo.visibility == View.VISIBLE) {

                // Co-Applicant Information Activity
                phoned = housePhoned.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                securitySocial =
                    houseSocialSecurity.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                phoneCell = houseCellphone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                val coImage: RequestBody? =
                    convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, houseCoImages)!!)?.let {
                        RequestBody.create(
                            "multipart/form-data".toMediaTypeOrNull(), it)
                    }
                picture = coImage?.let {
                    MultipartBody.Part.createFormData(
                        "CoApplicantinfo[AndaplaceforthemtoUploadDriversLicenses]",
                        getAlphaNumericString()!!.toString() + ".jpg",
                        it)
                }
            } else {
                //  copy Co-Applicant Information Activity
                phoned = "".toRequestBody("plain/text".toMediaTypeOrNull())
                securitySocial =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                phoneCell = "".toRequestBody("plain/text".toMediaTypeOrNull())

                var vFileName = ""
                var vFileBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
                picture = MultipartBody.Part.createFormData(
                    "image",
                    vFileName,
                    vFileBody
                )
            }

            if (ivTick.visibility == View.VISIBLE) {

                // Co_Employment Detail Activity
                Co_employment_status =
                    houseedempstatuss.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_current_employer =
                    houseedcurrentEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisors_name =
                    houseedsupervisorName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phone = houseedphone.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_job_title = houseedjobTitle.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_date_hired =
                    houseeddateHired.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_income =
                    houseedmonthlyIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_other_source_income =
                    houseedotherSourceOfIncome.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previous_employer_ifany =
                    houseedpreviousEmployer.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisor_name =
                    houseedsupervisorsName.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phones = houseedphones.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_period_of_employment =
                    houseedperiodOfEmployment.toString().toRequestBody("plain/text".toMediaTypeOrNull())

                val Coproofofincome: RequestBody? =
                    convertBitmapToByteArrayUncompressed(getBitmapFromUri(context, houseedimage)!!)?.let {
                        RequestBody.create(
                            "multipart/form-data".toMediaTypeOrNull(), it
                        )
                    }
                Co_proof_of_income = Coproofofincome?.let {
                    MultipartBody.Part.createFormData(
                        "Co_EmploymentDetails[Co_proof_of_income]",
                        getAlphaNumericString()!!.toString() + ".jpg",
                        it)
                }
            } else {
                //  Copy Co_Employment Detail Activity
                Co_employment_status =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_current_employer =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisors_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phone = "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_job_title = "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_date_hired =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_income =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_other_source_income =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previous_employer_ifany =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_supervisor_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_phones = "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_period_of_employment = "".toRequestBody("plain/text".toMediaTypeOrNull())
                var vFileNames = ""
                var vFileBodys = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
                Co_proof_of_income = MultipartBody.Part.createFormData(
                    "image",
                    vFileNames,
                    vFileBodys
                )
            }


            if (ivTicked.visibility == View.VISIBLE) {

                // CO-Rental History Activity
                Co_current_address =
                    houserdcurrentAddress.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_residing_address =
                    houserdhowlong.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rent =
                    houserdmonthlyrent.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_name =
                    houserdlandlord.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_contact_number =
                    houserdlandcontact.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reason_leaving_property =
                    houserdreasonsfor.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previos_address =
                    houserdpvaddress.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_did_you_stay_at_address =
                    houserdhowlongstay.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rents =
                    houserdmonthlyrents.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_name =
                    houserdlandname.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_contact =
                    houserdllcnumber.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reasons_for_leaving =
                    houserdreasonsleaving.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_ever_been =
                    houserdhaveYou.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_missed_two_payment =
                    houserdradio1.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_have_you_everrefused =
                    houserdradio2.toString().toRequestBody("plain/text".toMediaTypeOrNull())
                Co_ifyouhaveanswered_yesno =
                    houserdyouanswered.toString().toRequestBody("plain/text".toMediaTypeOrNull())

            } else {
                //  copy Co employment details
                Co_current_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_residing_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rent =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlord_contact_number =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reason_leaving_property =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_previos_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_long_did_you_stay_at_address =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_monthly_rents =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_name =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_landlords_contact =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_reasons_for_leaving =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_ever_been =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_how_you_missed_two_payment =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_have_you_everrefused =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
                Co_ifyouhaveanswered_yesno =
                    "".toRequestBody("plain/text".toMediaTypeOrNull())
            }
        }



        return RetrofitClient().apiInterface?.sendFormData(
            employment_status!!,
            current_employer!!,
            supervisors_name!!,
            phone!!,
            job_title!!,
            date_hired!!,
            monthly_income!!,
            other_source_income!!,
            previous_employment!!,
            supervisor_name!!,
            phones!!,
            period_of_employment!!,
            proof_of_income!!,
            date!!,
            Applicant_signature!!,
            dates!!,
            co_application_signature!!,
            current_address!!,
            how_long_residing_address!!,
            monthlys_rent!!,
            landlord_name!!,
            landlord_contact_number!!,
            reason_leaving_property!!,
            previos_address!!,
            how_long_did_you_stay_at_address!!,
            monthly_rent!!,
            landlords_name!!,
            landlords_contact!!,
            reasons_for_leaving!!,
            how_you_ever_been!!,
            how_you_missed_two_payment,
            have_you_everrefused,
            ifyouhaveanswered_yesno,
            Name!!,
            SocialSecuritynumberIDnumber!!,
            CellPhone!!,
            AndaplaceforthemtoUploadDriversLicense!!,
            Co_employment_status!!,
            Co_current_employer!!,
            Co_supervisors_name!!,
            Co_phone!!,
            Co_job_title!!,
            Co_date_hired!!,
            Co_monthly_income!!,
            Co_other_source_income!!,
            Co_previous_employer_ifany!!,
            Co_supervisor_name!!,
            Co_phones!!,
            Co_period_of_employment!!,
            Co_proof_of_income!!,
            Co_current_address!!,
            Co_how_long_residing_address!!,
            Co_monthly_rent!!,
            Co_landlord_name!!,
            Co_landlord_contact_number!!,
            Co_reason_leaving_property!!,
            Co_previos_address!!,
            Co_how_long_did_you_stay_at_address!!,
            Co_monthly_rents!!,
            Co_landlords_name!!,
            Co_landlords_contact!!,
            Co_reasons_for_leaving!!,
            Co_how_you_ever_been!!,
            Co_how_you_missed_two_payment,
            Co_have_you_everrefused,
            Co_ifyouhaveanswered_yesno,
            phoned,
            securitySocial,
            phoneCell,
            picture!!
        )
    }
}
