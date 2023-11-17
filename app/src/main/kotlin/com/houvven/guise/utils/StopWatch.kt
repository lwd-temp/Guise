package com.houvven.guise.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StopWatch {
    private var startTime: Long = 0
    private var endTime: Long = 0

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun stop() {
        endTime = System.currentTimeMillis()
    }

    fun stop(callback: StopWatch.() -> Unit) {
        endTime = System.currentTimeMillis()
        callback()
    }

    fun prettyPrint(): String {
        val elapsed = endTime - startTime
        val elapsedSeconds = elapsed / 1000
        val elapsedMinutes = elapsedSeconds / 60
        val elapsedHours = elapsedMinutes / 60
        val elapsedDays = elapsedHours / 24

        val startFormatted =
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(startTime))
        val endFormatted = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(endTime))

        val elapsedSecondsStr = (elapsedSeconds % 60).toString().padStart(2, '0')
        val elapsedMinutesStr = (elapsedMinutes % 60).toString().padStart(2, '0')
        val elapsedHoursStr = (elapsedHours % 24).toString().padStart(2, '0')
        val elapsedDaysStr = elapsedDays.toString().padStart(2, '0')

        val timeCostSeconds = elapsed / 1000
        val timeCostStr = "$timeCostSeconds s"

        val separator = "-------------------------------------------------------"

        val startColumnWidth = maxOf(" start time ".length, startFormatted.length)
        val endColumnWidth = maxOf(" end time   ".length, endFormatted.length)
        val elapsedColumnWidth = maxOf(
            " elapsed ".length,
            "$elapsedDaysStr:$elapsedHoursStr:$elapsedMinutesStr:$elapsedSecondsStr".length
        )
        val timeCostColumnWidth = maxOf(" time cost ".length, timeCostStr.length)

        return buildString {
            appendLine(separator)
            appendLine(
                "| start time ".padEnd(startColumnWidth) + "| end time   ".padEnd(
                    endColumnWidth
                ) + "| elapsed ".padEnd(elapsedColumnWidth) + "| time cost ".padEnd(
                    timeCostColumnWidth
                ) + "|"
            )
            appendLine(separator)
            appendLine(
                "| $startFormatted ".padEnd(startColumnWidth) + "| $endFormatted ".padEnd(
                    endColumnWidth
                ) + "| $elapsedDaysStr:$elapsedHoursStr:$elapsedMinutesStr:$elapsedSecondsStr ".padEnd(
                    elapsedColumnWidth
                ) + "| $timeCostStr ".padEnd(timeCostColumnWidth) + "|"
            )
            appendLine(separator)
        }
    }


   fun prettyLog() {
        Timber.tag("StopWatch").d(prettyPrint())
    }
}
