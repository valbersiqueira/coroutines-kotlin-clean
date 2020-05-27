package com.teste.getcep.core.fuctions

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


fun View.show(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun FragmentActivity.navigateTo(
    containerId: Int,
    fragment: Fragment,
    tag: String,
    addToBackStack: Boolean = true
) {
    with(supportFragmentManager.beginTransaction()) {
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(containerId, fragment, tag)
        commit()
    }
}

fun <T : AppCompatActivity> AppCompatActivity.navigatorActivity(activity: Class<T>) {
    startActivity(Intent(this, activity))
}