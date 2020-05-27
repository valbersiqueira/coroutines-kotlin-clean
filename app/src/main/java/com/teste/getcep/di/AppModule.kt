package com.teste.getcep.di

import android.content.Context
import com.teste.getcep.core.request.CallApi
import com.teste.getcep.core.request.CallApiImpl
import com.teste.getcep.core.request.OnRequest
import com.teste.getcep.core.request.OnRequestImpl
import com.teste.getcep.core.retrofit
import com.teste.getcep.data.remote.CepServiceApi
import com.teste.getcep.data.repositories.CepRepositoryImpl
import com.teste.getcep.data.services.CepService
import com.teste.getcep.domain.repositories.CepRepository
import com.teste.getcep.domain.usecase.CepUseCase
import com.teste.getcep.presenter.cep.viewmodel.CepViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


private val appModule = module(override = true) {
    factory<OnRequest> { OnRequestImpl() }
    factory<CallApi> { CallApiImpl() }
    factory<CepServiceApi> { CepService(retrofitInit) }
    factory<CepRepository> { CepRepositoryImpl(get(), get(), Dispatchers.IO, get()) }
    factory { CepUseCase(get()) }
    viewModel { CepViewModel(get()) }
}

fun initKoin(applicationContext: Context) {
    startKoin {
        androidLogger(Level.DEBUG)
        androidContext(applicationContext)
        modules(arrayListOf(appModule))
    }
}


private val retrofitInit by lazy { retrofit("https://viacep.com.br/ws/") }