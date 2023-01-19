package com.mt.android.data.repository

import com.mt.android.data.network.MyApi
import com.mt.android.data.network.RetrofitHelper


class RetrofitRepository {

    var retrofitClient: MyApi = RetrofitHelper.instance

    suspend fun getData() = retrofitClient.getEmpDataList()

}
