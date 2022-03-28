package cn.ommiao.composeanimation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.LightBlue
import cn.ommiao.composeanimation.ui.theme.MediumSpace
import cn.ommiao.composeanimation.widget.TextButton

@Composable
fun ExampleX(title: String) {
    ExampleContainer(title = title) {
        // example x-1
        ExampleX_1()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ExampleX_1() {
    var state by remember {
        mutableStateOf(State.START)
    }
    var animationSpecOption by remember {
        mutableStateOf(AnimationSpecOption.SPRING)
    }

    TextButton(
        text = if (state == State.START) "Offset To End" else "Offset To Start",
        topPadding = 0.dp
    ) {
        state = if (state == State.START) State.END else State.START
    }

    Row(
        modifier = Modifier
            .padding(bottom = MediumSpace)
    ) {
        AnimationSpecOption.values().forEach {
            FilterChip(
                modifier = Modifier.padding(end = MediumSpace),
                selected = it == animationSpecOption,
                onClick = { animationSpecOption = it }) {
                Text(text = it.name)
            }
        }
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val boxSize = 56.dp
    val endStateOffset = screenWidth - MediumSpace * 2 - boxSize
    val targetValue = if (state == State.START) 0.dp else endStateOffset
    val animationSpec = when (animationSpecOption) {
        AnimationSpecOption.SPRING -> spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
        AnimationSpecOption.TWEEN -> tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        )
        AnimationSpecOption.KEYFRAMES -> keyframes<Dp> {
            durationMillis = 2000
            (endStateOffset / 2) at 200
            (endStateOffset / 2) at 400
        }
    }
    val animateOffset by animateDpAsState(
        targetValue = targetValue,
        animationSpec = animationSpec
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize * 2)
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = animateOffset)
                .size(boxSize)
                .background(LightBlue.color)
        )
    }
}

enum class AnimationSpecOption {
    SPRING, TWEEN, KEYFRAMES
}
