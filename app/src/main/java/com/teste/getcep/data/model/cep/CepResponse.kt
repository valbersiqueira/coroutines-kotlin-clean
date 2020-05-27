package com.teste.getcep.data.model.cep

import com.google.gson.annotations.SerializedName
import com.teste.getcep.core.fuctions.empty
import com.teste.getcep.domain.entities.Cep

data class CepResponse(
    @SerializedName("cep")
    val cep: String,

    @SerializedName("logradouro")
    val logradouro: String,

    @SerializedName("bairro")
    val bairro: String,

    @SerializedName("localidade")
    val localidade: String,

    @SerializedName("uf")
    val uf: String,

    @SerializedName("unidade")
    val unidade: String,

    @SerializedName("ibge")
    val ibge: String,

    @SerializedName("gia")
    val gia: String
)

fun CepResponse.toCep() =
    Cep(
        cep = cep,
        bairro = bairro,
        gia = gia,
        ibge = ibge,
        localidade = localidade,
        logradouro = logradouro,
        uf = uf,
        unidade = unidade
    )
