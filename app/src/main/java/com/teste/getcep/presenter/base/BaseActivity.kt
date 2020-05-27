package com.teste.getcep.presenter.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.teste.getcep.R

abstract class BaseActivity : AppCompatActivity() {

    private val progressBar by lazy { DialogLoading(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        with(supportFragmentManager) {
            if (backStackEntryCount > 0) {
                popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }

    fun setupToolbar(
        toolbar: Toolbar,
        title: String,
        icon: Int = R.drawable.ic_arrow_left_white
    ) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(icon)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun showLoading() {
        progressBar.show()
    }

    fun hideLoading() {
        progressBar.hide()
    }
}