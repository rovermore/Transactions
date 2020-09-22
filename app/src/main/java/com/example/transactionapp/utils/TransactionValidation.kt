package com.example.transactionapp.utils

import com.example.transactionapp.domain.model.TransactionDetails
import java.text.ParseException
import java.text.SimpleDateFormat

class TransactionValidation {

    fun checkDateFormat(response: MutableList<TransactionDetails>): MutableList<TransactionDetails> {
        val dateValidatedList = mutableListOf<TransactionDetails>()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        for (transaction in response){

            if (isDateFormatCorrect(transaction.stringDate, dateFormat)) {
                transaction.stringDate?.run {
                    transaction.formattedDate = dateFormat.parse(transaction.stringDate)
                }
                dateValidatedList.add(transaction)
            }
        }
        return dateValidatedList
    }

    fun removeDuplications(response: MutableList<TransactionDetails>): MutableList<TransactionDetails> {
        response.sortByDescending { it.formattedDate }
        return response.distinctBy { it.id }.toMutableList()
    }

    private fun isDateFormatCorrect(stringDate: String?, dateFormat: SimpleDateFormat): Boolean {
        return try {
            dateFormat.parse(stringDate)
            true
        } catch (e: ParseException){
            false
        }
    }
}