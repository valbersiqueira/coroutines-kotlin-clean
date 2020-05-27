package com.teste.getcep.domain.entities

import com.teste.getcep.core.fuctions.empty

data class Cep(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val unidade: String,
    val ibge: String,
    val gia: String
) {
    companion object {
        fun empty() = Cep(
            cep = String.empty(),
            bairro = String.empty(),
            gia = String.empty(),
            ibge = String.empty(),
            localidade = String.empty(),
            logradouro = String.empty(),
            uf = String.empty(),
            unidade = String.empty()
        )
    }
}
