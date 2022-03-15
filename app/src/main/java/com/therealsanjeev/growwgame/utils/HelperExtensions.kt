package com.therealsanjeev.growwgame.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Fragment.replaceChildFragment(
    fragment: Fragment, @IdRes frameId: Int,
    addBackStack: String? = null
) {
    val fragTransaction = childFragmentManager.beginTransaction()
    fragTransaction.apply {
        if (addBackStack != null) {
            addToBackStack(addBackStack)
        }
        replace(frameId, fragment)
        commitAllowingStateLoss()
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
