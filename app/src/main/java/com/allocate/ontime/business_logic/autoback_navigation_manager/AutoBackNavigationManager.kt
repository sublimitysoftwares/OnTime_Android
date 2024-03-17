package com.allocate.ontime.business_logic.autoback_navigation_manager

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AutoBackNavigationManager @Inject constructor() {

    companion object{
        const val TAG = "AutoBackNavigationManager"
    }

    private val navigateScreens: HashMap<String, Long> = hashMapOf()
    private var navigationFlowInternal = MutableStateFlow(false)
    var navigationFlow = navigationFlowInternal.asStateFlow()

    private var timeoutJob: Job? = null
    private val TIMEOUT_DURATION_MS = 10000L


    fun addOrUpdateScreens(screen: String) {
        navigateScreens[screen] = System.currentTimeMillis()
        Log.d(TAG,"addOrUpdateScreens called..")
        startTimeout(screen)
    }

    private fun removeScreens(screen: String) {
        navigateScreens.remove(screen)
        Log.d(TAG,"removeScreens called..")
        navigationFlowInternal.value = false
        Log.d(TAG,"after remove screen..${navigationFlowInternal.value}")
    }

    private fun startTimeout(screenName : String) {
        stopTimeout()
        Log.d(TAG,"startTimeout called..")
        timeoutJob = CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG,"Entered in coroutine..")
                checkScreenInteractions(screenName)
        }
    }

     private fun checkScreenInteractions(screenName: String) {
         Log.d(TAG,"checkScreenInteractions called..")
        val currentTime = System.currentTimeMillis()
         Log.d(TAG,"current time: $currentTime")
        if (navigateScreens.isNotEmpty()) {
            val screenLastTime = navigateScreens[screenName]
            Log.d(TAG,"screenLastTime: $screenLastTime")
            Log.d(TAG,"map entry: ${navigateScreens.entries.first()}")
            screenLastTime?.let {screenLastTimes ->
                Log.d(TAG,"timeDifference: ${currentTime - screenLastTimes}")
                if (currentTime - screenLastTimes >= TIMEOUT_DURATION_MS){
                    Log.d(TAG,"after 10 second timeDifference: ${currentTime - screenLastTimes}")
                    Log.d(TAG,"10 seconds completed..")
                    removeScreens(screenName)
                    CoroutineScope(Dispatchers.Main).launch {
                        navigationFlowInternal.value = true
                        Log.d(TAG,"after 10 second..${navigationFlowInternal.value}")
                    }
                }
            }

        } else {
            navigationFlowInternal.value = false
            Log.d(TAG,"hashmap is empty..${navigationFlowInternal.value}")
            stopTimeout()
        }

    }

    private fun stopTimeout() {
        timeoutJob?.cancel()
    }

}



