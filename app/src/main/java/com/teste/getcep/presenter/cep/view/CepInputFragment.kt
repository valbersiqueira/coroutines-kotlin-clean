package com.teste.getcep.presenter.cep.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teste.getcep.R
import com.teste.getcep.presenter.base.BaseActivity
import com.teste.getcep.presenter.cep.viewmodel.CepViewModel
import kotlinx.android.synthetic.main.fragment_cep_input.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CepInputFragment : Fragment() {

    private val cepViewModel: CepViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.fragment_cep_input,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        bindView()
    }

    private fun bindView() {
        buttonCepSearch.setOnClickListener {
            cepViewModel.getCepTwo(cepInput.text.toString())
//            cepViewModel.getCep(cepInput.text.toString())
        }
    }

    private fun setupToolbar() {
        context?.apply {
            (this as BaseActivity).setupToolbar(
                toolbar = toolbarCepInput,
                title = getString(R.string.cep_title_toolbar_input)
            )
        }
    }

    companion object {
        const val TAG = "CepInputFragment"
    }
}
