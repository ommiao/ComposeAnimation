package cn.ommiao.composeanimation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import cn.ommiao.composeanimation.ui.theme.MediumSpace
import cn.ommiao.composeanimation.ui.theme.OverviewLazyListTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets

val statusBarColor = Color(0xFFF1F1F1)

@Composable
fun Page() {
    OverviewLazyListTheme {
        ProvideWindowInsets {
            val insets = LocalWindowInsets.current
            val statusBarHeight = with(LocalDensity.current) {
                insets.statusBars.top.toDp()
            }
            Scaffold(topBar = {
                MyAppBar(statusBarHeight)
            }) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(it)
                        .verticalScroll(scrollState)
                ) {
                    Example1()
                }
            }
        }
    }
}

@Composable
private fun MyAppBar(statusBarHeight: Dp) {
    TopAppBar(
        contentPadding = PaddingValues(top = statusBarHeight),
        backgroundColor = statusBarColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MediumSpace),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h6)
        }
    }
}
