package com.eugenio.marvelsuperheroes.core.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private val defaultLocale = Locale.getDefault()
    /**
     * Format a date to a String.
     * @param date Date to format.
     * @param pattern Pattern type (by default "dd/MM/yyyy").
     * @return Date format to String.
     */
    fun formatDate(date: Date, pattern: String = "dd/MM/yyyy"): String {
        val dateFormat = SimpleDateFormat(pattern, defaultLocale)
        return dateFormat.format(date)
    }

    /**
     * Convert a date in ISO format (such as "2023-10-01T00:00:00-0500") to a Date
     * @param isoDate String in ISO format.
     * @return Date as an Date object.
     */
    fun parseISODate(isoDate: String): Date? {
        return try {
            val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
            isoFormat.parse(isoDate)
        } catch (e: ParseException) {
            null
        }
    }
}