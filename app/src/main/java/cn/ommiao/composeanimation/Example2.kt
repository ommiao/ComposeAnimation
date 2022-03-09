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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.itemsList
import cn.ommiao.composeanimation.widget.TextButton

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

