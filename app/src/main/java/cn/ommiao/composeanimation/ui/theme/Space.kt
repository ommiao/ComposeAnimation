package cn.ommiao.composeanimation.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val MediumSpace = 16.dp

val LargeSpace = 24.dp

@Composable
fun Dp.Spacer() {
    Spacer(modifier = Modifier.size(this))
}
