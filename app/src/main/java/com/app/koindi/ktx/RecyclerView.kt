package com.app.koindi.ktx

import androidx.recyclerview.widget.LinearLayoutManager
import com.app.koindi.typealiases.ApplicationFragment

fun ApplicationFragment.horizontalLayoutManager() =
    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
