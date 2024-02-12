package com.allocate.ontime.business_logic.utils


object Constants {
      var imei: String? = null
//      const val BASE_URL = "http://api.cpair.uk/"
        const val BASE_URL = "http://cpaapi.sublimitysoft.com/"
      const val databaseName = "OnTime_DB"
      val getDeviceInfoUrlEndPoint = "GetDevice?IMEI=$imei"
      const val getCISuperAdminDetails = "EmployeeAPI/GetAppManageUser"
      const val editDeviceInfoUrlEndPoint = "editDeviceAppData"



      // Screens route
      const val SplashScreen = "SplashScreen"
      const val HomeScreen = "HomeScreen"
      const val AdminScreen = "AdminScreen"
      const val SuperAdminScreen = "SuperAdminScreen"
      const val AdminRegistrationScreen = "AdminRegistrationScreen"
      const val FobRegisterScreen = "FobRegisterScreen"
      const val SuperAdminSettingScreen = "SuperAdminSettingScreen"
}


