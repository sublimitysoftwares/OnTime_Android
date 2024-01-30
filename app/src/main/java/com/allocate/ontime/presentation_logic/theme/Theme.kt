package com.allocate.ontime.presentation_logic.theme

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.allocate.ontime.MainActivity

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun CI_OnTimeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    activity: Activity = LocalContext.current as MainActivity,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val window: WindowSizeClass = calculateWindowSizeClass(activity = activity)
    val config: Configuration = LocalConfiguration.current

    var typography = MediumTypography1
    var appDimens = MediumDimens1

    when(window.widthSizeClass){
        WindowWidthSizeClass.Medium -> {
            if(config.screenWidthDp < 680){
                appDimens = MediumDimens1
                typography = MediumTypography1
            } else if (config.screenWidthDp < 760){
                appDimens = MediumDimens2
                typography = MediumTypography2
            } else if(config.screenWidthDp < 840){
                appDimens = MediumDimens3
                typography = MediumTypography3
            }
        }
        WindowWidthSizeClass.Expanded -> {
            if (config.screenWidthDp < 900){
                appDimens = ExpandedDimens2
                typography = ExpandedTypography2

            }else if (config.screenWidthDp < 960){
                appDimens = ExpandedDimens1
                typography = ExpandedTypography1

            }else if (config.screenWidthDp < 1020){
                appDimens = ExpandedDimens3
                typography = ExpandedTypography3

            }else{
                appDimens = ExpandedDimens
                typography = ExpandedTypography
            }

        }
    }

    AppUtils(appDimens = appDimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )

    }
}

val MaterialTheme.dimens
@Composable
get() = LocalAppDimens.current