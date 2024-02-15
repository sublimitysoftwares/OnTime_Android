package com.allocate.ontime.business_logic.viewmodel


import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object NavigationTimeoutHandler {
    private const val TIMEOUT_DURATION_MS = 20000L // 10 seconds

    private var handler: Handler? = null
    private var timeoutJob: Job? = null

    fun startTimeout(onTimeout: () -> Unit) {
        stopTimeout() // Reset previous timeout if any

        handler = Handler(Looper.getMainLooper())
        timeoutJob = CoroutineScope(Dispatchers.Main).launch {
            delay(TIMEOUT_DURATION_MS)
            onTimeout.invoke()
        }
    }

    fun stopTimeout() {
        handler?.removeCallbacksAndMessages(null)
        timeoutJob?.cancel()
    }
}