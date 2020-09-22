package com.example.transactionapp.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transactionapp.domain.model.TransactionDetails
import com.example.transactionapp.usecase.TransactionsUseCase
import com.example.transactionapp.utils.NetworkConnection
import com.example.transactionapp.utils.ScreenState
import com.example.transactionapp.utils.TransactionValidation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel
    @Inject constructor(private val networkConnection: NetworkConnection,
                        private val transactionsUseCase: TransactionsUseCase,
                        private val transactionValidation: TransactionValidation) : ViewModel() {

    private val coroutineContext: CoroutineContext get() = Job() + Dispatchers.Main
    private val viewModelScope = CoroutineScope(coroutineContext)

    private val _uiModel =
        MutableLiveData<List<TransactionDetails>>()
    val uiModel: LiveData<List<TransactionDetails>> = _uiModel

    private val _uiState = MutableLiveData<ScreenState>()
    val uiState: LiveData<ScreenState> = _uiState

    fun initialize() {
        loadData()
    }

    fun loadData() {
        _uiState.setValue(ScreenState.Loading)
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (networkConnection.isNetworkConnected()) setupObservers()
        else _uiState.setValue(ScreenState.Error)
    }

    private fun setupObservers() {
        observeTransactionsResponse()
    }

    private fun observeTransactionsResponse() {
        viewModelScope.launch {
            val response = transactionsUseCase.request()
            if (response.isNullOrEmpty())
                displayAccountTransactionsError()
            else
                createAndPostUiModel(
                    transactionValidation.removeDuplications(
                        transactionValidation.checkDateFormat(
                            response as MutableList<TransactionDetails>
                        )
                    )
                )
        }
    }

    private fun displayAccountTransactionsError() {
        _uiState.setValue(ScreenState.Error)
    }

    private fun createAndPostUiModel(response: List<TransactionDetails>) {
        viewModelScope.launch {
            _uiModel.setValue(response)
            _uiState.setValue(ScreenState.Success)
        }
    }
}