package cn.ommiao.composeanimation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import cn.ommiao.composeanimation.data.itemsList
import cn.ommiao.composeanimation.extension.toColor
import cn.ommiao.composeanimation.ui.theme.MediumSpace
import cn.ommiao.composeanimation.widget.TextButton
import kotlinx.coroutines.delay

@Composable
fun Example1() {
    ExampleContainer(title = "animateDpAsState") {
        // example 1-1, offset start to end
        Example1_1()

        // example 1-2, animating progress bar
        Example1_2()
    }
}

@Composable
private fun Example1_1() {
    var state by remember {
        mutableStateOf(State.START)
    }

    TextButton(
        text = if (state == State.START) "Offset To End" else "Offset To Start",
        topPadding = 0.dp
    ) {
        state = if (state == State.START) State.END else State.START
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val boxSize = 28.dp
    val endStateOffset = screenWidth - MediumSpace * 2 - boxSize
    val animateOffset by animateDpAsState(
        targetValue = if (state == State.START) 0.dp else endStateOffset,
        animationSpec = tween(2000)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .offset(x = animateOffset)
                .size(boxSize)
                .background(itemsList[2].color.toColor())
        )
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
                .background(itemsList[2].color.toColor())
        )
    }
}

enum class State {
    START, END
}
