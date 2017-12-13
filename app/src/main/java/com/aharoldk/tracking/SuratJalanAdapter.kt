package com.aharoldk.tracking

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aharoldk.tracking.pojo.SuratJalan
import kotlinx.android.synthetic.main.row_surat_jalan.view.*


/**
 * Created by aharoldk on 12/12/17.
 */

class SuratJalanAdapter(var list: List<SuratJalan>?) : RecyclerView.Adapter<SuratJalanAdapter.SuratJalanViewHolder>() {
    private var detailClickListener: TruckingInterface.clickListenerSuratJalan? = null
    private var choosenList :SuratJalan? = null


    override fun onBindViewHolder(holder: SuratJalanViewHolder?, position: Int) {
        holder?.bind(list!!.get(position))
        holder?.itemView!!.setOnClickListener {

            if (detailClickListener != null){
                choosenList = list!!.get(position)
                detailClickListener!!.suratJalanClicked(choosenList!!)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SuratJalanViewHolder {
        return SuratJalanViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row_surat_jalan, parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun clearData(list: List<SuratJalan>?){
        this.list = list
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: TruckingInterface.clickListenerSuratJalan) {
        detailClickListener = itemClickListener
    }


    class SuratJalanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun bind(suratJalan: SuratJalan) {
            itemView.tv_jarak.text = "Jarak : "+suratJalan.kmjarakkirim
            itemView.tv_kotaasal.text = "Asal : "+suratJalan.kirimdari
            itemView.tv_kotatujuan.text = "Tujuan : "+suratJalan.kirimke
            itemView.tv_platmobil.text = "Plat : "+suratJalan.platmobil
            itemView.tv_supir.text = "Supir : "+suratJalan.namasupir
            itemView.tv_suratJalan.text = suratJalan.csuratjalanid.toString()
            itemView.tv_tgl.text = Util().getShortDate(suratJalan.tglkirim!!)
        }
    }
}

