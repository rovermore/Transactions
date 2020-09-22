package com.example.transactionapp

import com.example.transactionapp.domain.model.TransactionDetails
import java.text.SimpleDateFormat
import java.util.*

object TransactionsMock {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var transactionList: MutableList<TransactionDetails> = mutableListOf(
        TransactionDetails(4734,"2018-07-11T22:49:24.000Z",null, -193.38f, -3.18f, "Lorem ipsum dolor sit amet"),
        TransactionDetails(2210,"2018-07-14T16:54:27.000Z",null, 165.36f, null, "Est ullamco mollit ad in in proident."),
        TransactionDetails(2911,"2018-07-21T19:13:23.000Z",null, 37.74f, 0.0f, "Lorem et incididunt aute cillum."),
        TransactionDetails(2911,"2018-07-29T17:56:43.000Z",null, 87.84f, -1.11f, "Veniam sit ut pariatur do."),
        TransactionDetails(2857,"07-22T13:51:12.000Z",null, -144.63f, -4.74f, "Tempor dolor laboris minim cupidatat duis nisi ad."),
        TransactionDetails(2911,"2018-07-21T19:13:23.000Z",null, 37.74f, 0.0f, "Lorem et incididunt aute cillum.")
    )

    var validDateTransactions: MutableList<TransactionDetails> = mutableListOf(
        TransactionDetails(2210,"2018-07-14T16:54:27.000Z",dateFormat.parse("2018-07-14T16:54:27.000Z"), 165.36f, null,"Est ullamco mollit ad in in proident."),
        TransactionDetails(2001,"2018-07-29T17:56:43.000Z",dateFormat.parse("2018-07-29T17:56:43.000Z"), 87.84f, -1.11f, "Veniam sit ut pariatur do."),
        TransactionDetails(2911,"2018-07-21T19:13:23.000Z",dateFormat.parse("2018-07-21T19:13:23.000Z"), 37.74f, 0.0f, "Lorem et incididunt aute cillum."),
        TransactionDetails(4734,"2018-07-11T22:49:24.000Z", dateFormat.parse("2018-07-11T22:49:24.000Z"), -193.38f, -3.18f, "Lorem ipsum dolor sit amet"),
        TransactionDetails(2911,"2018-07-24T19:13:23.000Z",dateFormat.parse("2018-07-24T19:13:23.000Z"), 37.74f, 0.0f, "Lorem et incididunt aute cillum."),
        TransactionDetails(2911,"2018-07-23T19:13:23.000Z",dateFormat.parse("2018-07-23T19:13:23.000Z"), 37.74f, 0.0f, "Lorem et incididunt aute cillum.")

    )
    var notDuplicatedSortedList: MutableList<TransactionDetails> = mutableListOf(
        TransactionDetails(2001,"2018-07-29T17:56:43.000Z",null, 87.84f, -1.11f, "Veniam sit ut pariatur do."),
        TransactionDetails(2911,"2018-07-30T19:13:23.000Z",dateFormat.parse("2018-07-29T19:13:23.000Z"), 37.74f, 0.0f, "Lorem et incididunt aute cillum."),
        TransactionDetails(2210,"2018-07-14T16:54:27.000Z",null, 165.36f, null, "Est ullamco mollit ad in in proident."),
        TransactionDetails(4734,"2018-07-11T22:49:24.000Z",null, -193.38f, -3.18f, "Lorem ipsum dolor sit amet")
    )
}