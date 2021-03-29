package com.test.githubjobs.commons.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.test.githubjobs.R

fun showSnackBar(view: View, text: String) {
    val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
    snackbar.setAction(R.string.snackbar_action) {
        snackbar.dismiss()
    }
    snackbar.show()
}