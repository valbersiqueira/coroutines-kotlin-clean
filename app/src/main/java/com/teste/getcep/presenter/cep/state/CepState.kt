package com.teste.getcep.presenter.cep.state

import com.teste.getcep.domain.entities.Cep


sealed class CepState {
    data class ShowCep(val cep: Cep) : CepState()

    data class ShowErrorCep(val message: String?): CepState()

    object ShowLoading : CepState()
    object HideLoading : CepState()
}