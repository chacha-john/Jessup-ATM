package com.mirror.jessupbank.utils

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snack(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
}