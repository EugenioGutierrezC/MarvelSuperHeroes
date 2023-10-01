package com.eugenio.marvelsuperheroes.core.utils

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import java.util.Date

class DateUtilsTest {
    @Test
    fun `test formatDate with default pattern`() {
        val date = Date(1234567890L)
        val expectedFormattedDate = "15/01/1970"

        val result = DateUtils.formatDate(date)

        assertEquals(expectedFormattedDate, result)
    }

    @Test
    fun `test formatDate with custom pattern`() {
        val date = Date(1234567890L)
        val expectedFormattedDate = "15-01-70"

        val result = DateUtils.formatDate(date, "dd-MM-yy")

        assertEquals(expectedFormattedDate, result)
    }

    @Test
    fun `test parseISODate with invalid ISO date`() {
        val isoDate = "invalid-date"

        val result = DateUtils.parseISODate(isoDate)

        assertNull(result)
    }
}