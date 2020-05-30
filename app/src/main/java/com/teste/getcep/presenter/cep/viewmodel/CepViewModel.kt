package com.teste.getcep.presenter.cep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teste.getcep.core.fuctions.flow
import com.teste.getcep.core.request.DisposableTaskJob
import com.teste.getcep.data.model.error.FailureError
import com.teste.getcep.domain.usecase.CepUseCase
import com.teste.getcep.presenter.cep.state.CepState
import kotlinx.coroutines.*

class CepViewModel(
    private val cepUseCase: CepUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _cepState by lazy { MutableLiveData<CepState>() }
    private val disposableJob by lazy { DisposableTaskJob() }

    val cepState: LiveData<CepState>
        get() = _cepState

    fun getCep(cep: String) {
        _cepState.value = CepState.ShowLoading
        disposableJob.add(CoroutineScope(dispatcher).launch {
            withContext(Dispatchers.Main) {
                cepUseCase.getCep(cep) {
                    flow({
                        _cepState.value = CepState.HideLoading
                        it?.let { cep ->
                            _cepState.postValue(CepState.ShowCep(cep))
                        }
                    }, {
                        val error: Throwable? = (it as FailureError.FailureServerThrowable).error
                        _cepState.run {
                            value = CepState.HideLoading
                            postValue(CepState.ShowErrorCep(error?.message))
                        }
                    })
                }
            }
        })
    }

    fun getCepTwo(cep: String) {
        _cepState.value = CepState.ShowLoading
        viewModelScope.launch {
            cepUseCase.getCepTwo(cep) {
                flow({
                    _cepState.value = CepState.HideLoading
                    _cepState.postValue(CepState.ShowCep(it))
                }, {
                    _cepState.run {
                        value = CepState.HideLoading
                        postValue(CepState.ShowErrorCep("erro"))
                    }
                })
            }
        }
    }

    override fun onCleared() {
        disposableJob.clear()
        super.onCleared()
    }
}