package com.aharoldk.tracking.pojo

import com.google.gson.annotations.SerializedName

class Xstatus {

    @SerializedName("xStatus")
    var xStatus: Int = 0

    override fun toString(): String {
        return "Xstatus{" +
                "xStatus = '" + xStatus + '\'' +
                "}"
    }
}