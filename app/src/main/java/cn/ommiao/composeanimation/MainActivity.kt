package cn.ommiao.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SystemUiController()
            Page()
        }
    }
}

@Composable
private fun SystemUiController() {
    val systemUiController = rememberSystemUiController()
    val darkIcons = true
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = darkIcons)
    }
}
