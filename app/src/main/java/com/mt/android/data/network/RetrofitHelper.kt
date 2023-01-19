package com.mt.android.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {

    private const val baseUrl = "https://dummyjson.com/"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder().method(original.method(), original.body())
        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val instance: MyApi by lazy {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

        retrofit.create(MyApi::class.java)
    }


}