package com.example.transactionapp

import com.example.transactionapp.client.RetrofitApiClientImplementation
import com.example.transactionapp.domain.model.TransactionDetails
import com.example.transactionapp.domain.repository.ApiRepository
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ApiRepositoryTest {

    lateinit var retrofitApiClientImplementation: RetrofitApiClientImplementation
    lateinit var apiRepository: ApiRepository

    private val transactions: MutableList<TransactionDetails> = TransactionsMock.transactionList

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        retrofitApiClientImplementation = Mockito.mock(RetrofitApiClientImplementation::class.java)
        whenever(retrofitApiClientImplementation.getTransactionsResponse()).thenReturn(this@ApiRepositoryTest.transactions)
        apiRepository = ApiRepository(retrofitApiClientImplementation)
    }


    @Test
    fun `if RetrofitApiClientImplementation return transactions then ApiRepository returns same transactions`() = runBlockingTest {
        val transactionsFromClientImpl = apiRepository.getTransactionsResponse()
        assertEquals(transactionsFromClientImpl, this@ApiRepositoryTest.transactions)
        assertEquals(transactionsFromClientImpl[0].id, this@ApiRepositoryTest.transactions[0].id)
        assertEquals(transactionsFromClientImpl[0].amount, this@ApiRepositoryTest.transactions[0].amount)
    }
}