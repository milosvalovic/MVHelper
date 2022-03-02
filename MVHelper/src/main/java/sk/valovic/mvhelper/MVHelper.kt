package sk.valovic.mvhelper

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.ImageView
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object MVHelper {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Int): Int {
        val r: Resources = Resources.getSystem()
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            px.toFloat(),
            r.displayMetrics).toInt()
    }

    fun timeDifferenceInSec(startDT: String?, endDT: String?): Int {
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN)
        var endDate = Date()
        var startDate = Date()
        try {
            startDate = format.parse(startDT)
            endDate = format.parse(endDT)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diffInMs = endDate.time - startDate.time
        val diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        return diffInSec.toInt()
    }

    fun secToMin(sec: Int): String? {
        val minutes = sec % 3600 / 60
        val seconds = sec % 60
        return String.format("%02d:%02d min", minutes, seconds)
    }

    fun timeDifferenceInSec(endDT: String?): Int {
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN)
        var endDate = Date()
        val startDate = Calendar.getInstance().time
        try {
            endDate = format.parse(endDT)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diffInMs = endDate.time - startDate.time
        val diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        return diffInSec.toInt()
    }

    fun convertDate(dateInString: String): String? {
        var dateInString = dateInString
        dateInString = "$dateInString 00:00:00.000"
        val format: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)
        var date: Date? = null
        try {
            date = format.parse(dateInString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return format.format(date)
    }

    fun timeDifference(start: String?, end: String?): String? {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        var startDate: Date? = null
        var difference: Long = 0
        try {
            startDate = simpleDateFormat.parse(start)
            val endDate = simpleDateFormat.parse(end)
            difference = endDate.time - startDate.time
            if (difference < 0) {
                val dateMax = simpleDateFormat.parse(start)
                val dateMin = simpleDateFormat.parse(start)
                difference = dateMax.time - startDate.time + (endDate.time - dateMin.time)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val mins = (difference / (1000 * 60)).toInt()
        return String.format(Locale.ENGLISH, "%d", mins)
    }

    fun timeDifferenceFullSK(start: String?, end: String?): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var startDate: Date? = null
        var difference: Long = 0
        try {
            startDate = simpleDateFormat.parse(start)
            val endDate = simpleDateFormat.parse(end)
            difference = endDate.time - startDate.time
            if (difference < 0) {
                val dateMax = simpleDateFormat.parse(start)
                val dateMin = simpleDateFormat.parse(start)
                difference = dateMax.time - startDate.time + (endDate.time - dateMin.time)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        difference = difference / 1000
        val days = (difference / (60 * 60 * 24))
        val hours = ((difference - 60 * 60 * 24 * days) / (60 * 60))
        val min = (difference - 60 * 60 * 24 * days - 60 * 60 * hours) / 60
        var date = ""
        if (days > 0) {
            date = if (days == 1L) "1 deň " else if (days <= 4) "$days dni " else "$days dní "
        }
        if (hours > 0) {
            date += if (hours == 1L) "1 hodinu " else if (hours <= 4) "$hours hodiny " else "$hours hodín "
        }
        if (min > 0 && hours == 0L && days == 0L) date += if (min == 1L) "1 minuta" else if (min <= 4) "$min minuty" else "$min minút"
        return date
    }

    fun dateConverter(dateInString: String?, desiredFormat: String?): String? {
        val formatFrom: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN)
        val formatTo: DateFormat = SimpleDateFormat(desiredFormat, Locale.GERMAN)
        var date: Date? = null
        try {
            date = formatFrom.parse(dateInString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formatTo.format(date)
    }


    fun setImageSaturation(view: ImageView, amount: Int) {
        val matrix = ColorMatrix()
        matrix.setSaturation(amount.toFloat())
        val cf = ColorMatrixColorFilter(matrix)
        view.colorFilter = cf
    }

    fun dateConverter(
        dateInString: String?,
        sourceFormat: String?,
        desiredFormat: String?
    ): String? {
        val formatFrom: DateFormat = SimpleDateFormat(sourceFormat, Locale.GERMAN)
        val formatTo: DateFormat = SimpleDateFormat(desiredFormat, Locale.GERMAN)
        var date: Date? = null
        try {
            date = formatFrom.parse(dateInString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formatTo.format(date)
    }

    fun addToTime(
        currentDateandTime: String?,
        additionalHour: Int,
        additionalMinute: Int,
        additionalSeconds: Int
    ): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        var date: Date? = null
        try {
            date = sdf.parse(currentDateandTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.HOUR, additionalHour)
        calendar.add(Calendar.MINUTE, additionalMinute)
        calendar.add(Calendar.SECOND, additionalSeconds)
        date = calendar.time
        return sdf.format(date)
    }

    fun isDateBefore(dateTime: String?): Boolean {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var strDate: Date? = null
        try {
            strDate = sdf.parse(dateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return if (Date().before(strDate)) {
            true
        } else false
    }

    fun isDateBefore(dateTime: String?, format: String?): Boolean {
        val sdf = SimpleDateFormat(format)
        var strDate: Date? = null
        try {
            strDate = sdf.parse(dateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return if (Date().before(strDate)) {
            true
        } else false
    }

    fun isDateBefore(startDateTime: String?, endDateTime: String?, format: String?): Boolean {
        val sdf = SimpleDateFormat(format)
        var strDateStart: Date? = null
        var strDateEnd: Date? = null
        try {
            strDateStart = sdf.parse(startDateTime)
            strDateEnd = sdf.parse(endDateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        assert(strDateStart != null)
        return if (strDateStart!!.before(strDateEnd)) {
            true
        } else false
    }

    fun timeFormater(t: String?): String? {
        val format: DateFormat = SimpleDateFormat("HH:mm", Locale.GERMAN)
        var time: Date? = null
        try {
            time = format.parse(t)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return format.format(time)
    }

    fun currentDateTime(): String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val time = Calendar.getInstance().time
        return format.format(time)
    }

    fun getDisplayHeightInPixels(ctx: Context): Int {
        val displayMetrics = DisplayMetrics()
        (ctx as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun getDisplayWidthInPixels(ctx: Context): Int {
        val displayMetrics = DisplayMetrics()
        (ctx as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getDisplayHeightInDp(ctx: Context): Int {
        val displayMetrics = DisplayMetrics()
        (ctx as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return pxToDp(displayMetrics.heightPixels)
    }

    fun getDisplayWidthInDp(ctx: Context): Int {
        val displayMetrics = DisplayMetrics()
        (ctx as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return pxToDp(displayMetrics.widthPixels)
    }


}