package com.aharoldk.tracking

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by aharoldk on 12/12/17.
 */
class Util {

    fun getShortDate(date: String): String {
        return formatDate(date, "dd-MM-yyyy")
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String, format: String): String {
        var result = ""

        val old = SimpleDateFormat("yyyy-MM-dd")
        try {
            val oldDate = old.parse(date)
            val newFormat = SimpleDateFormat(format)
            result = newFormat.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }
}