package com.teste.getcep.presenter.base

import android.app.Dialog
import android.content.Context
import android.view.View.inflate
import android.view.ViewGroup
import android.view.Window
import com.teste.getcep.R
import com.teste.getcep.core.fuctions.show
import kotlinx.android.synthetic.main.dialog_loading.*

class DialogLoading(context: Context) : Dialog(context, android.R.style.Theme_Black) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(R.color.bg_dialog_loading)

        inflate(context, R.layout.dialog_loading, null as ViewGroup?)
            .run { setContentView(this) }

        progressBarDialogLoading.isIndeterminate = true
        setCancelable(false)
    }

    override fun show() {
        progressBarDialogLoading.show(true)
        super.show()
    }

    override fun hide() {
        progressBarDialogLoading.show(false)
        super.hide()
    }
}