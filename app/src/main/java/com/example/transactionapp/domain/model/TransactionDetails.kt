package com.example.transactionapp.domain.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class TransactionDetails(
    val id: Int?,
    @SerializedName("date")
    val stringDate: String?,
    var formattedDate: Date? = null,
    val amount: Float?,
    val fee: Float?,
    val description: String?
) {
    fun dateToPrettyFormat(): String {
        formattedDate?.run {
            val prettyFormat = SimpleDateFormat("dd-MM")
            return prettyFormat.format(formattedDate)
        }
        return ""
    }

    fun finalAmount(): Float?{
        fee?.run {
            return (amount?.minus(fee))
        } ?: return amount
    }
}
