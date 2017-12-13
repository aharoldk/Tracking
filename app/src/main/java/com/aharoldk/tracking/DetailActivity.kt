@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.aharoldk.tracking

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.aharoldk.tracking.pojo.SuratJalan
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private val TAG = DetailActivity::class.simpleName

    private val SURAT_JALAN = "suratJalan"
    private var suratjalanJson: String? = null
    private var suratJalan: SuratJalan? = null
    val PERMISSION_REQUEST_FINE_LOCATION = 101

    fun start(context: Context, newsJson: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(SURAT_JALAN, newsJson)
        context.startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        requestLocationUpdates()

        if (intent.hasExtra(SURAT_JALAN)) {
            suratjalanJson = intent.getStringExtra(SURAT_JALAN)
            suratJalan = SuratJalan().fromJson(suratjalanJson!!)

            tv_suratJalan.text = suratJalan!!.csuratjalanid
            tv_jarak.text = "Jarak : " + suratJalan!!.kmjarakkirim
            tv_tgl.text = Util().getShortDate(suratJalan!!.tglkirim!!)

        } else {
            finish()
        }

        val intent = Intent(this, TrackingService::class.java)

        btn_start.setOnClickListener {
            checkButtonn()

            intent.putExtra(TrackingService().data, suratJalan!!.csuratjalanid)
            startService(intent)
        }

        btn_stop.setOnClickListener {
            processLocationStop()
            checkButtonn()
            stopService(intent)
        }

    }

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_FINE_LOCATION)
            }
            return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "requestCode : $requestCode, grantResults : ${grantResults.get(0)}")
        when (requestCode) {
            PERMISSION_REQUEST_FINE_LOCATION -> {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_FINE_LOCATION)
                    }
                }
            }
        }
    }


    fun processLocationStop() {
        SmartLocation.with(this).location().stop()
        SmartLocation.with(this).activity().stop()
        SmartLocation.with(this).geofencing().stop()
    }

    private fun checkButtonn() {
        if (btn_start.visibility == View.GONE) {
            btn_start.visibility = View.VISIBLE
        } else {
            btn_start.visibility = View.GONE
        }

        if (btn_stop.visibility == View.GONE) {
            btn_stop.visibility = View.VISIBLE
        } else {
            btn_stop.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        processLocationStop()
        stopService(intent)
    }
}

