package cn.ommiao.composeanimation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.LightBlue
import cn.ommiao.composeanimation.data.itemsList
import cn.ommiao.composeanimation.ui.theme.MediumSpace
import cn.ommiao.composeanimation.widget.TextButton
import kotlinx.coroutines.delay

@Composable
fun Example2(title: String) {
    ExampleContainer(title = title) {
        // example 2-1, offset chain top to end
        Example2_1()
    }
}

@Composable
private fun Example2_1() {
    var state by remember {
        mutableStateOf(State.START)
    }

    TextButton(
        text = if (state == State.START) "Offset To Bottom" else "Offset To Top",
        topPadding = 0.dp
    ) {
        state = if (state == State.START) State.END else State.START
    }

    val boxSize = 28.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize * 2)
            .background(Color.LightGray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemsList.take(10).forEachIndexed { index, item ->
                val animateOffset by animateDpAsState(
                    targetValue = if (state == State.START) 0.dp else boxSize,
                    animationSpec = tween(500, delayMillis = index * 100)
                )
                Box(
                    modifier = Modifier
                        .offset(y = animateOffset)
                        .size(boxSize)
                        .background(item.color)
                )
            }
        }
    }
}

@Composable
private fun Example1_2() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val maxProgress = screenWidth - MediumSpace * 2
    val boxSize = 28.dp
    var progressAnimationKey by remember {
        mutableStateOf(0)
    }
    var progress by remember {
        mutableStateOf(0.dp)
    }
    LaunchedEffect(progressAnimationKey) {
        if (progressAnimationKey > 0) {
            listOf(0.1f, 0.25f, 0.44f, 0.65f, 0.7f, 0.8f, 0.95f, 1.0f)
                .forEach {
                    progress = (maxProgress.value * it).dp
                    delay(1000)
                }
        }
    }

    TextButton(
        text = when (progress) {
            0.dp -> "Start Progress"
            maxProgress -> "Reset"
            else -> "Animating..."
        }
    ) {
        when (progress) {
            0.dp -> {
                progressAnimationKey += 1
            }
            maxProgress -> {
                progress = 0.dp
            }
        }
    }

    val animateWidth by animateDpAsState(
        targetValue = progress,
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .width(animateWidth)
                .height(boxSize)
                .background(LightBlue.color)
        )
    }
}
