package com.allocate.ontime.business_logic.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.business_logic.data.shared_preferences.SecureSharedPrefs
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.encryption.EDModel
import com.allocate.ontime.presentation_logic.model.SuperAdminResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DeviceInfoRepository,
    context: Context
) : ViewModel() {

    companion object{
        const val TAG = "MainViewModel"
    }

    init {
        viewModelScope.launch {
            val result = repository.postSuperAdminDetails()
            if (result.data != null){
                if (result.data?.isSuccessful == true) {
                    val data = result.data?.body()?.data
                    val decryptedData =
                        EDModel(data.toString()).getResponseModel(SuperAdminResponse::class.java)
                    SecureSharedPrefs(context).saveData(
                        Constants.USER_NAME,
                        decryptedData.ResponsePacket.UserName
                    )
                    SecureSharedPrefs(context).saveData(
                        Constants.PASSWORD,
                        decryptedData.ResponsePacket.Password
                    )
                }
            } else {
                Log.d(TAG,"result.data is null")
            }
        }
    }
}