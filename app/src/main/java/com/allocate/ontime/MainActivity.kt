package com.allocate.ontime


import android.annotation.SuppressLint
import android.app.admin.DevicePolicyManager
import android.app.admin.SystemUpdatePolicy
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.os.UserManager
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.fragment.NavHostFragment
import com.allocate.ontime.business_logic.utils.Constants.HomeScreen
import com.allocate.ontime.business_logic.viewmodel.NavigationTimeoutHandler
import com.allocate.ontime.presentation_logic.navigation.OnTimeNavigation
import com.allocate.ontime.business_logic.viewmodel.super_admin.SuperAdminSettingViewModel
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import com.allocate.ontime.presentation_logic.screens.home.HomeScreen
import com.allocate.ontime.presentation_logic.theme.CI_OnTimeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mAdminComponentName: ComponentName
    private lateinit var mDevicePolicyManager: DevicePolicyManager

    companion object {
        const val LOCK_ACTIVITY_KEY = "com.allocate.ontime.MainActivity"
    }

    private lateinit var navController: NavController

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startNavigationTimeout()

        setContent {
            OnTimeApp()
        }

        mAdminComponentName = MyDeviceAdminReceiver.getComponentName(this)
        mDevicePolicyManager =
            getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        mDevicePolicyManager.removeActiveAdmin(mAdminComponentName)

        val isAdmin = isAdmin()

        if (mDevicePolicyManager.isDeviceOwnerApp(packageName)) {
            setKioskPolicies(true, isAdmin)
        } else {
//            setKioskPolicies(false, isAdmin)
//            val intent = Intent(applicationContext, MainActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            }
//            intent.putExtra(LOCK_ACTIVITY_KEY, false)
//            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        startNavigationTimeout()
    }
    override fun onPause() {
        super.onPause()
        NavigationTimeoutHandler.stopTimeout()
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        restartNavigationTimeout()
        return super.dispatchTouchEvent(ev)
    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        restartNavigationTimeout()
    }


    @SuppressLint("RestrictedApi")
    private fun startNavigationTimeout() {
        NavigationTimeoutHandler.startTimeout {
            onBackPressedDispatcher.onBackPressed()
            navController = NavController(applicationContext)
            val currentDestination = navController.currentDestination?.route
            if (currentDestination == OnTimeScreens.HomeScreen.name){
                NavigationTimeoutHandler.stopTimeout()
            } else{
                restartNavigationTimeout()
            }
        }
    }


    private fun restartNavigationTimeout() {
        NavigationTimeoutHandler.stopTimeout()
        startNavigationTimeout()
    }

    override fun onStop() {
        super.onStop()
        NavigationTimeoutHandler.stopTimeout()
    }


    private fun isAdmin() = mDevicePolicyManager.isDeviceOwnerApp(packageName)

    private fun setKioskPolicies(enable: Boolean, isAdmin: Boolean) {
        if (isAdmin) {
            setRestrictions(enable)
            enableStayOnWhilePluggedIn(enable)
            setUpdatePolicy(enable)
            setAsHomeApp(enable)
            setKeyGuardEnabled(enable)
        }
        setLockTask(enable, isAdmin)
        setImmersiveMode(enable)
    }

    // region restrictions
    private fun setRestrictions(disallow: Boolean) {
        setUserRestriction(UserManager.DISALLOW_SAFE_BOOT, disallow)
        setUserRestriction(UserManager.DISALLOW_FACTORY_RESET, disallow)
        setUserRestriction(UserManager.DISALLOW_ADD_USER, disallow)
        setUserRestriction(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA, disallow)
        setUserRestriction(UserManager.DISALLOW_ADJUST_VOLUME, disallow)
        mDevicePolicyManager.setStatusBarDisabled(mAdminComponentName, disallow)
    }

    private fun setUserRestriction(restriction: String, disallow: Boolean) = if (disallow) {
        mDevicePolicyManager.addUserRestriction(mAdminComponentName, restriction)
    } else {
        mDevicePolicyManager.clearUserRestriction(mAdminComponentName, restriction)
    }
    // endregion

    private fun enableStayOnWhilePluggedIn(active: Boolean) = if (active) {
        mDevicePolicyManager.setGlobalSetting(
            mAdminComponentName,
            Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
            (BatteryManager.BATTERY_PLUGGED_AC
                    or BatteryManager.BATTERY_PLUGGED_USB
                    or BatteryManager.BATTERY_PLUGGED_WIRELESS).toString()
        )
    } else {
        mDevicePolicyManager.setGlobalSetting(
            mAdminComponentName,
            Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
            "0"
        )
    }

    private fun setLockTask(start: Boolean, isAdmin: Boolean) {
        if (isAdmin) {
            mDevicePolicyManager.setLockTaskPackages(
                mAdminComponentName, if (start) arrayOf(packageName) else arrayOf()
            )
        }
        if (start) {
            startLockTask()
        } else {
            stopLockTask()
        }
    }

    private fun setUpdatePolicy(enable: Boolean) {
        if (enable) {
            mDevicePolicyManager.setSystemUpdatePolicy(
                mAdminComponentName,
                SystemUpdatePolicy.createWindowedInstallPolicy(60, 120)
            )
        } else {
            mDevicePolicyManager.setSystemUpdatePolicy(mAdminComponentName, null)
        }
    }

    private fun setAsHomeApp(enable: Boolean) {
        if (enable) {
            val intentFilter = IntentFilter(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                addCategory(Intent.CATEGORY_DEFAULT)
            }
            mDevicePolicyManager.addPersistentPreferredActivity(
                mAdminComponentName,
                intentFilter,
                ComponentName(packageName, MainActivity::class.java.name)
            )
        } else {
            mDevicePolicyManager.clearPackagePersistentPreferredActivities(
                mAdminComponentName, packageName
            )
        }
    }

    private fun setKeyGuardEnabled(enable: Boolean) {
        mDevicePolicyManager.setKeyguardDisabled(mAdminComponentName, !enable)
    }

    @Suppress("DEPRECATION")
    private fun setImmersiveMode(enable: Boolean) {
        if (enable) {
            val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            window.decorView.systemUiVisibility = flags
        } else {
            val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.decorView.systemUiVisibility = flags
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OnTimeApp(viewModel: SuperAdminSettingViewModel = hiltViewModel()) {
    CI_OnTimeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnTimeNavigation(viewModel = viewModel)
            }
        }
    }
}


