package com.aharoldk.tracking

import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.util.Log
import com.aharoldk.tracking.api.ApiClient
import com.aharoldk.tracking.pojo.Xstatus
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.location.config.LocationAccuracy
import io.nlopez.smartlocation.location.config.LocationParams
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrackingService : Service() {

    private val TAG = TrackingService::class.simpleName
    val data = "INIT_DATA"
    var initData :String? = null

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        initData = intent?.getStringExtra("INIT_DATA")

        Log.d(TAG, initData)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()

        val mLocTrackingInterval = (10000 * 6).toLong() // 1 minute
        val trackingDistance = 0f
        val trackingAccuracy = LocationAccuracy.HIGH

        val builder = LocationParams.Builder()
                .setAccuracy(trackingAccuracy)
                .setDistance(trackingDistance)
                .setInterval(mLocTrackingInterval)

        SmartLocation.with(this)
                .location()
                .continuous()
                .config(builder.build())
                .start { location -> processLocationStart(location) }
    }

    private fun processLocationStart(location: Location?) {
        Log.d(TAG, "" + location)

        val apiclient = ApiClient()
        val observable : Observable<List<Xstatus>> = apiclient.getService().insertTracking(
                initData.toString(), location?.latitude.toString(), location?.longitude.toString()
        )

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result ->
                    Log.d(TAG, ""+result)

                }, {
                    error -> Log.d(TAG, error.message)
                })
    }

}
