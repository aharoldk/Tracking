package com.aharoldk.tracking.api

import com.aharoldk.tracking.pojo.SuratJalan
import com.aharoldk.tracking.pojo.Xstatus
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aharoldk on 12/12/17.
 */

interface ApiCall {

    @GET("ws_get_surat_jalan")
    fun getSuratJalan() : Observable<List<SuratJalan>>

    @GET("ws_insert_tracking")
    fun insertTracking(
        @Query("arg_suratjalan") arg_suratjalan: String,
        @Query("arg_latitude") arg_latitude: String,
        @Query("arg_longitude") arg_longitude: String
    ) : Observable <List<Xstatus>>

}