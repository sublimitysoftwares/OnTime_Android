package com.allocate.ontime.business_logic.data

data class DataOrException<T,E:Exception>(
    var data:T? = null,
    var e:E? = null
)