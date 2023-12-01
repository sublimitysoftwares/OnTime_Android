package com.example.ontime_jitendra

import WindowInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ontime_jitendra.navigation.OnTimeNavigation
import com.example.ontime_jitendra.ui.theme.OnTime_JitendraTheme
import rememberWindowInfo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowInfo = rememberWindowInfo()
            OnTimeApp(windowInfo)
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OnTimeApp(windowInfo: WindowInfo) {
    OnTime_JitendraTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                OnTimeNavigation(windowInfo)
            }


        }
    }
}


