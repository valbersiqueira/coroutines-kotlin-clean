package com.teste.getcep

import android.os.Bundle
import android.os.StrictMode
import com.teste.getcep.core.fuctions.navigatorActivity
import com.teste.getcep.di.initKoin
import com.teste.getcep.presenter.base.BaseActivity
import com.teste.getcep.presenter.cep.view.CepActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initKoin(this)
        navigatorActivity(CepActivity::class.java)
    }
}
