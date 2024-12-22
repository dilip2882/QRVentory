package com.dilip.common

import android.util.Log
import com.dilip.common.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}
