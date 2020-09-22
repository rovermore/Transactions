package com.example.transactionapp

import com.example.transactionapp.domain.model.TransactionDetails
import com.example.transactionapp.utils.TransactionValidation
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TransactionValidationTest {

    lateinit var transactionValidation: TransactionValidation

    private val mockedTransactionList: MutableList<TransactionDetails> = TransactionsMock.transactionList
    private val validDateTransactions: MutableList<TransactionDetails> = TransactionsMock.validDateTransactions

    @Before
    fun setup() {
        transactionValidation = TransactionValidation()
    }

    @Test
    fun `check if checkDateFormat function works`() {
        val validDateList = transactionValidation.checkDateFormat(mockedTransactionList)
        TestCase.assertEquals(validDateList[0].stringDate, mockedTransactionList[0].stringDate)
        TestCase.assertEquals(validDateList[0].id, mockedTransactionList[0].id)
        TestCase.assertEquals(validDateList[0].amount, mockedTransactionList[0].amount)

        Assert.assertThat(
            validDateList[4].stringDate,
            CoreMatchers.not(mockedTransactionList[4].stringDate)
        )
        Assert.assertThat(validDateList[4].id, CoreMatchers.not(mockedTransactionList[4].id))
        Assert.assertThat(
            validDateList[4].amount,
            CoreMatchers.not(mockedTransactionList[4].amount)
        )
    }

    @Test
    fun `check if removeDuplications function works`() {
        val notDuplicatedSortedList = transactionValidation.removeDuplications(validDateTransactions)
        Assert.assertThat(
            notDuplicatedSortedList[2].stringDate,
            CoreMatchers.not(validDateTransactions[2].stringDate)
        )
        Assert.assertThat(
            notDuplicatedSortedList[2].id,
            CoreMatchers.not(validDateTransactions[2].id)
        )
        Assert.assertThat(
            notDuplicatedSortedList[2].amount,
            CoreMatchers.not(validDateTransactions[2].amount)
        )
    }
}