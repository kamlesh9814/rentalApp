package com.rbackent.app.ui.model.final_api

import com.google.gson.annotations.SerializedName

data class EmploymentDetailResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("body")
    val body: Body
)
