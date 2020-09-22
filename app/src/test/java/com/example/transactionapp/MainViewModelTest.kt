package com.example.transactionapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.transactionapp.domain.model.TransactionDetails
import com.example.transactionapp.screen.MainViewModel
import com.example.transactionapp.usecase.TransactionsUseCase
import com.example.transactionapp.utils.NetworkConnection
import com.example.transactionapp.utils.ScreenState
import com.example.transactionapp.utils.TransactionValidation
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var transactionUseCase: TransactionsUseCase
    lateinit var mainViewModel: MainViewModel
    lateinit var networkConnection: NetworkConnection

    private val mockedTransactionList: MutableList<TransactionDetails> = TransactionsMock.transactionList

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        transactionUseCase = Mockito.mock(TransactionsUseCase::class.java)
        networkConnection = Mockito.mock(NetworkConnection::class.java)
        whenever(transactionUseCase.request()).thenReturn(mockedTransactionList)
        whenever(networkConnection.isNetworkConnected()).thenReturn(true)
        mainViewModel = MainViewModel(networkConnection,transactionUseCase, TransactionValidation())
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `checks uiModel is null when transaction request is empty`() = runBlocking {
        whenever(transactionUseCase.request()).thenReturn(mutableListOf())
        mainViewModel.initialize()
        Truth.assertThat(mainViewModel.uiModel.value).isNull()
    }

    @Test
    fun `checks uiModel is not empty when transaction request is not empty`() = runBlocking {
        mainViewModel.initialize()
        Truth.assertThat(mainViewModel.uiModel.value).isNotEmpty()
    }

    @Test
    fun `checks uiState is Error when transaction request is empty`() = runBlocking {
        whenever(transactionUseCase.request()).thenReturn(mutableListOf())
        mainViewModel.initialize()
        Truth.assertThat(mainViewModel.uiState.value).isEqualTo(ScreenState.Error)
    }

    @Test
    fun `checks uiState is Success when transaction request is not empty`() = runBlocking {
        mainViewModel.initialize()
        Truth.assertThat(mainViewModel.uiState.value).isEqualTo(ScreenState.Success)
    }
}