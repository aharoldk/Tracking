package com.aharoldk.tracking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.aharoldk.tracking.api.ApiClient
import com.aharoldk.tracking.pojo.SuratJalan
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), TruckingInterface.clickListenerSuratJalan {

    val TAG:String = MainActivity::class.java.simpleName

    var list :List<SuratJalan>? = null
    var adapter : SuratJalanAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        declare()
        callData()
    }

    private fun callData() {
        val apiclient = ApiClient()
        val observable :Observable<List<SuratJalan>> = apiclient.getService().getSuratJalan()

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result ->
                        Log.d(TAG, ""+result)
                    adapter?.clearData(result)

                }, {
                    error -> Log.d(TAG, error.message)
                })

    }

    private fun declare() {

        list = ArrayList<SuratJalan>()
        adapter = SuratJalanAdapter(list)

        rv_main.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_main.adapter = adapter

        adapter!!.setItemClickListener(this)
    }

    override fun suratJalanClicked(list: SuratJalan) {
        DetailActivity().start(this, list.toJson())
    }
}
