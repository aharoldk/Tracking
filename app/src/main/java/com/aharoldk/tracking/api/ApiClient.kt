package com.aharoldk.tracking.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aharoldk on 12/12/17.
 */

class ApiClient {
    private var apicall: ApiCall? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://202.43.162.180:8026/PowerTruckWebServices/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apicall = retrofit.create(ApiCall::class.java)
    }

    fun getService() : ApiCall {
        return apicall!!
    }
}
