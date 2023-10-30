package com.hackathon.onecard.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Locale


fun formatToNaira(data: Double): String {
    val newformat: NumberFormat = NumberFormat.getNumberInstance()
    newformat.maximumFractionDigits = 0
    return newformat.format(data)
}

object Util  {
  //  val DOLLAR = Currency("USD", "Dollar")
    //val NAIRA = Currency("NGN", "Naira", 1.0)

}

sealed class ResultWrapper<out T> {

    /**
     * The call was a success
     * @param data The data that is to be returned in a successful call event
     * */
    data class Success<T>(val data: T) : ResultWrapper<T>()

    /**
     * The call failed
     * @param error The exception thrown on a failed call event
     * */
    open class Failure(val error: Throwable) : ResultWrapper<Nothing>() {
        constructor(message: String) : this(Throwable(message))
    }
}

fun formatDateTime(date: String): String {
    val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.getDefault())
    val billerDateFormat = SimpleDateFormat("d MMMM yyyy 'at' h:mm a", Locale.getDefault())
    val actualPaymentDate = apiDateFormat.parse(date)
    val formattedDate: String = billerDateFormat.format(actualPaymentDate)

    return formattedDate

}

fun formatDate(date: String): String {
    val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.getDefault())
    val billerDateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    val actualPaymentDate = apiDateFormat.parse(date)
    val formattedDate: String = billerDateFormat.format(actualPaymentDate)

    return formattedDate
}

class NumberCommaTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(text.text.toLongOrNull().formatWithComma()),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return text.text.toLongOrNull().formatWithComma().length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }
            }
        )
    }
}

fun Long?.formatWithComma(): String =
    NumberFormat.getNumberInstance(Locale.ENGLISH).format(this ?: 0)


/**
 * To format count down timer
 * */
fun formatTimeToCountDown(timeInMillis: Long): String {
    val minutes = (timeInMillis / 1000) / 60
    val seconds = (timeInMillis / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}

val LocalActivity =
    staticCompositionLocalOf<ComponentActivity> { error("No activity provided!") }



