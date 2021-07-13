package com.ksu.exercise3.utils

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ksu.exercise3.domain.NewsDomain

fun Fragment.navigate(@IdRes destination: Int) = findNavController().navigate(destination)

fun Fragment.navigateToDetail(@IdRes destination: Int, data: NewsDomain) = findNavController().navigate(destination, bundleOf("URL" to data.url))