package com.example.jetpackcomposevisualnoteapp.common.extensions

import android.widget.TextView
import java.text.DateFormat
import java.util.*

fun TextView.millisToDate(timeMillis: Long) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeMillis
    this.text = DateFormat.getDateInstance().format(calendar.time).toString()
}