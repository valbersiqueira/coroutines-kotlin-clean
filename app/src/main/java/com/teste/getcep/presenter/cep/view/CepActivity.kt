package com.teste.getcep.presenter.cep.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.teste.getcep.R
import com.teste.getcep.core.fuctions.navigateTo
import com.teste.getcep.presenter.base.BaseActivity
import com.teste.getcep.presenter.cep.state.CepState
import com.teste.getcep.presenter.cep.viewmodel.CepViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CepActivity : BaseActivity() {

    private val cepViewModel: CepViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cep)
        setupViewModel()
        showSearchCep()
    }

    private fun setupViewModel() {
        cepViewModel.run {
            cepState.observe(this@CepActivity, Observer { state ->
                when (state) {
                    is CepState.ShowLoading -> showLoading()
                    is CepState.HideLoading -> hideLoading()
                    is CepState.ShowCep -> showCep(state)
                }
            })
        }
    }

    private fun showCep(state: CepState.ShowCep) {
        Log.d("CEP", state.cep.localidade)
    }

    private fun showSearchCep() {
        navigateTo(
            containerId = R.id.containerCep,
            fragment = CepInputFragment(),
            addToBackStack = false,
            tag = CepInputFragment.TAG
        )
    }
}
