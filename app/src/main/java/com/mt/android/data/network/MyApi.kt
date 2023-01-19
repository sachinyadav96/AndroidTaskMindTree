package com.mt.android.data.network

import com.mt.android.data.model.MainList
import retrofit2.http.*


interface MyApi {

    //get emp List api
    @GET("users")
    suspend fun getEmpDataList(
    ): MainList


}