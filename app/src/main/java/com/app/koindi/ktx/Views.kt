package com.app.koindi.ktx

import android.widget.Toast
import com.app.koindi.typealiases.ApplicationFragment

fun ApplicationFragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}