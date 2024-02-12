package com.allocate.ontime



import android.annotation.SuppressLint
import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.app.admin.SystemUpdatePolicy
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.UserManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.utils.Utils
import com.allocate.ontime.presentation_logic.navigation.OnTimeNavigation
import com.allocate.ontime.business_logic.viewmodel.super_admin.SuperAdminSettingViewModel
import com.allocate.ontime.presentation_logic.theme.CI_OnTimeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mAdminComponentName: ComponentName
    private lateinit var mDevicePolicyManager: DevicePolicyManager


    lateinit var imei: String
    private val REQUEST_CODE = 101

    companion object {
        const val LOCK_ACTIVITY_KEY = "com.allocate.ontime.MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnTimeApp()
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE),
                REQUEST_CODE
            )
        } else {
            // Permission already granted
            getImei()
//            Utils.getImei()
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

    @SuppressLint("HardwareIds")
    private fun getImei() {
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        // Check if the device is running Android 10 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Use getImei() for Android 10 and higher
            imei = telephonyManager.imei
        } else {
            // Use getDeviceId() for Android 9 and below
            imei = telephonyManager.deviceId
        }
        Utils.imei = imei
    }




//    @Deprecated("Deprecated in Java")
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, now get the IMEI
//                getImei()
//            } else {
//                // Permission denied
//                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


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


