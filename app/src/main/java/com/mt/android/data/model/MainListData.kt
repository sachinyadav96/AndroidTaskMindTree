package com.mt.android.data.model

import com.google.gson.annotations.SerializedName

data class MainList(
    // @SerializedName("status") var status: String? = null,
    @SerializedName("users") var data: ArrayList<MainListDataResponse> = arrayListOf()

)


data class MainListDataResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("birthDate") var birthDate: String? = null,
    @SerializedName("image") var image: String? = null,
)