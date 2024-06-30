package com.rbackent.app.ui.network

import com.rbackent.app.ui.model.final_api.EmploymentDetailResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {
    @Multipart
    @POST("Rental_v4.php")
    suspend fun sendFormData(
        // Employment Detail Activity
        @Part("EmploymentDetails[employment_status]") employment_status: RequestBody,
        @Part("EmploymentDetails[current_employer]") current_employer: RequestBody,
        @Part("EmploymentDetails[supervisors_name]") supervisors_name: RequestBody,
        @Part("EmploymentDetails[phone]") phone: RequestBody,
        @Part("EmploymentDetails[job_title]") job_title: RequestBody,
        @Part("EmploymentDetails[date_hired]") date_hired: RequestBody,
        @Part("EmploymentDetails[monthly_income]") monthly_income: RequestBody,
        @Part("EmploymentDetails[other_source_income]") other_source_income: RequestBody,
        @Part("EmploymentDetails[previous_employer_ifany]") previous_employer_ifany: RequestBody,
        @Part("EmploymentDetails[supervisor_name]") supervisor_name: RequestBody,
        @Part("EmploymentDetails[phones]") phones: RequestBody,
        @Part("EmploymentDetails[period_of_employment]") period_of_employment: RequestBody,
        @Part proof_of_income: MultipartBody.Part?,

        // Additional Information
        @Part("Additional_informatation[date]") date: RequestBody,
        @Part Applicant_signature: MultipartBody.Part?,
        @Part("Additional_informatation[Date]") Date: RequestBody,
        @Part co_application_signature: MultipartBody.Part?,

        // Rental History
        @Part("Rental_history[current_address]") current_address: RequestBody,
        @Part("Rental_history[how_long_residing_address]") how_long_residing_address: RequestBody,
        @Part("Rental_history[monthlys_rent]") monthlys_rent: RequestBody,
        @Part("Rental_history[landlord_name]") landlord_name: RequestBody,
        @Part("Rental_history[landlord_contact_number]") landlord_contact_number: RequestBody,
        @Part("Rental_history[reason_leaving_property]") reason_leaving_property: RequestBody,
        @Part("Rental_history[previos_addresss]") previos_addresss: RequestBody,
        @Part("Rental_history[how_long_did_you_stay_at_address]") how_long_did_you_stay_at_address: RequestBody,
        @Part("Rental_history[monthly_rent]") monthly_rent: RequestBody,
        @Part("Rental_history[landlords_name]") landlords_name: RequestBody,
        @Part("Rental_history[landlords_contact]") landlords_contact: RequestBody,
        @Part("Rental_history[reasons_for_leaving]") reasons_for_leaving: RequestBody,
        @Part("Rental_history[how_you_ever_been]") how_you_ever_been: RequestBody,
        @Part("Rental_history[how_you_missed_two_payment]") how_you_missed_two_payment: RequestBody?,
        @Part("Rental_history[have_you_everrefused]") have_you_everrefused: RequestBody?,
        @Part("Rental_history[ifyouhaveanswered_yesno]") ifyouhaveanswered_yesno: RequestBody?,

        // Applicant Information Activity
        @Part("Applicantinfo[Name]") Name: RequestBody,
        @Part("Applicantinfo[SocialSecuritynumberIDnumber]") SocialSecuritynumberIDnumber: RequestBody,
        @Part("Applicantinfo[CellPhone]") CellPhone: RequestBody,
        @Part AndaplaceforthemtoUploadDriversLicense: MultipartBody.Part?,

        // Co-Employment Detail Activity
        @Part("Co_EmploymentDetails[Co_employment_status]") Co_employment_status: RequestBody,
        @Part("Co_EmploymentDetails[Co_current_employer]") Co_current_employer: RequestBody,
        @Part("Co_EmploymentDetails[Co_supervisors_name]") Co_supervisors_name: RequestBody,
        @Part("Co_EmploymentDetails[Co_phone]") Co_phone: RequestBody,
        @Part("Co_EmploymentDetails[Co_job_title]") Co_job_title: RequestBody,
        @Part("Co_EmploymentDetails[Co_date_hired]") Co_date_hired: RequestBody,
        @Part("Co_EmploymentDetails[Co_monthly_income]") Co_monthly_income: RequestBody,
        @Part("Co_EmploymentDetails[Co_other_source_income]") Co_other_source_income: RequestBody,
        @Part("Co_EmploymentDetails[Co_previous_employer_ifany]") Co_previous_employer_ifany: RequestBody,
        @Part("Co_EmploymentDetails[Co_supervisor_name]") Co_supervisor_name: RequestBody,
        @Part("Co_EmploymentDetails[Co_phones]") Co_phones: RequestBody,
        @Part("Co_EmploymentDetails[Co_period_of_employment]") Co_period_of_employment: RequestBody,
        @Part Co_proof_of_income: MultipartBody.Part?,

        // C0-Rental History Activity
        @Part("Co_Rental_history[Co_current_address]") Co_current_address: RequestBody,
        @Part("Co_Rental_history[Co_how_long_residing_address]") Co_how_long_residing_address: RequestBody,
        @Part("Co_Rental_history[Co_monthly_rent]") Co_monthly_rent: RequestBody,
        @Part("Co_Rental_history[Co_landlord_name]") Co_landlord_name: RequestBody,
        @Part("Co_Rental_history[Co_landlord_contact_number]") Co_landlord_contact_number: RequestBody,
        @Part("Co_Rental_history[Co_reason_leaving_property]") Co_reason_leaving_property: RequestBody,
        @Part("Co_Rental_history[Co_previos_address]") Co_previos_address: RequestBody,
        @Part("Co_Rental_history[Co_how_long_did_you_stay_at_address]") Co_how_long_did_you_stay_at_address: RequestBody,
        @Part("Co_Rental_history[Co_monthlys_rent]") Co_monthlys_rent: RequestBody,
        @Part("Co_Rental_history[Co_landlords_name]") Co_landlords_name: RequestBody,
        @Part("Co_Rental_history[Co_landlords_contact]") Co_landlords_contact: RequestBody,
        @Part("Co_Rental_history[Co_reasons_for_leaving]") Co_reasons_for_leaving: RequestBody,
        @Part("Co_Rental_history[Co_how_you_ever_been]") Co_how_you_ever_been: RequestBody,
        @Part("Co_Rental_history[Co_how_you_missed_two_payment]") Co_how_you_missed_two_payment: RequestBody?,
        @Part("Co_Rental_history[Co_have_you_everrefused]")Co_have_you_everrefused: RequestBody?,
        @Part("Co_Rental_history[Co_ifyouhaveanswered_yesno]") Co_ifyouhaveanswered_yesno: RequestBody?,

        // Co_Applicant Information
        @Part("CoApplicantinfo[Names]") CoApplicantinfoNames:RequestBody?,
        @Part("CoApplicantinfo[SocialSecuritynumberIDnumbers]") CoApplicantinfoSocialSecuritynumberIDnumbers:RequestBody?,
        @Part("CoApplicantinfo[CellPhones]") CoApplicantinfoCellPhones:RequestBody?,
        @Part AndaplaceforthemtoUploadDriversLicenses: MultipartBody.Part?

    ): Response<EmploymentDetailResponse>
}