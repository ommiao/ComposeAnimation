package cn.ommiao.composeanimation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.widget.TextButton

@Composable
fun Example3(title: String) {
    ExampleContainer(title = title) {
        // example 3-1, color
        Example3_1()
    }
}

@Composable
private fun Example3_1() {

    val boxSize = 56.dp

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            var state by remember {
                mutableStateOf(State.START)
            }
            TextButton(
                text = if (state == State.START) "Change to blue" else "Change to red",
                topPadding = 0.dp
            ) {
                state = if (state == State.START) State.END else State.START
            }

            val animateColorLong = animateValueAsState(
                targetValue = if (state == State.START) 0xFFFF0000 else 0xFF0000FF,
                typeConverter = TwoWayConverter(
                    { AnimationVector1D(it.toFloat()) }, { it.value.toLong() }
                ),
                animationSpec = tween(1000)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxSize)
                    .background(Color(animateColorLong.value))
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            var state by remember {
                mutableStateOf(State.START)
            }
            TextButton(
                text = if (state == State.START) "Change to blue" else "Change to red",
                topPadding = 0.dp
            ) {
                state = if (state == State.START) State.END else State.START
            }

            val animateColor by animateColorAsState(
                targetValue = if (state == State.START) Color.Red else Color.Blue,
                animationSpec = tween(1000)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxSize)
                    .background(animateColor)
            )
        }
    }
}

