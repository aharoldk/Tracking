package com.aharoldk.tracking.pojo

import com.google.gson.annotations.SerializedName
import com.google.gson.Gson



class SuratJalan {

    @SerializedName("TGL_KIRIM")
    var tglkirim: String? = null

    @SerializedName("KIRIM_KE")
    var kirimke: String? = null

    @SerializedName("KIRIM_DARI")
    var kirimdari: String? = null

    @SerializedName("PLAT_MOBIL")
    var platmobil: String? = null

    @SerializedName("NAMA_SUPIR")
    var namasupir: String? = null

    @SerializedName("KM_JARAK_KIRIM")
    var kmjarakkirim: Int = 0

    @SerializedName("CSURATJALANID")
    var csuratjalanid: String? = null


    override fun toString(): String {
        return "SuratJalan{" +
                "tGL_KIRIM = '" + tglkirim + '\'' +
                ",kIRIM_KE = '" + kirimke + '\'' +
                ",kIRIM_DARI = '" + kirimdari + '\'' +
                ",pLAT_MOBIL = '" + platmobil + '\'' +
                ",nAMA_SUPIR = '" + namasupir + '\'' +
                ",kM_JARAK_KIRIM = '" + kmjarakkirim + '\'' +
                ",cSURATJALANID = '" + csuratjalanid + '\'' +
                "}"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

    fun fromJson(newsJson: String): SuratJalan {
        return Gson().fromJson<SuratJalan>(newsJson, SuratJalan::class.java!!)
    }


}