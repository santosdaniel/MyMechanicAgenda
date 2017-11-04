package com.santosdaniel.mymechanicagenda.model.database

import com.santosdaniel.mymechanicagenda.R

/**
 * Time units supported
 */
enum class TimeUnit(symbolId: Int) {
    MINUTES(R.string.minutes),
    HOURS(R.string.hours),
    DAYS(R.string.days)
}
