package com.example.transactionapp

import com.example.transactionapp.domain.model.TransactionDetails
import com.example.transactionapp.domain.repository.ApiRepository
import com.example.transactionapp.usecase.TransactionsUseCase
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TransactionUseCaseTest {

    lateinit var apiRepository: ApiRepository
    lateinit var transactionUseCase: TransactionsUseCase

    private val transactions: MutableList<TransactionDetails> = TransactionsMock.transactionList

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        apiRepository = Mockito.mock(ApiRepository::class.java)
        whenever(apiRepository.getTransactionsResponse()).thenReturn(this@TransactionUseCaseTest.transactions)
        transactionUseCase = TransactionsUseCase(apiRepository)
    }


    @Test
    fun `if ApiRepository return transactions then TransactionsUseCase returns same transactions`() = runBlockingTest {
        val transactionsFromUseCase = transactionUseCase.request()
        Assert.assertEquals(transactionsFromUseCase, this@TransactionUseCaseTest.transactions)
        Assert.assertEquals(transactionsFromUseCase[0].id, this@TransactionUseCaseTest.transactions[0].id)
        Assert.assertEquals(transactionsFromUseCase[0].amount, this@TransactionUseCaseTest.transactions[0].amount)
    }
}