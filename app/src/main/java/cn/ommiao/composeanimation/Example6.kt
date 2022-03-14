package cn.ommiao.composeanimation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.Blue
import cn.ommiao.composeanimation.widget.TextButton

@Composable
fun Example6(title: String) {
    ExampleContainer(title = title) {
        // example 6-1, many animated-visibility composable
        Example6_1()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Example6_1() {
    Grid(
        // slide in/out
        {
            var visible by remember {
                mutableStateOf(false)
            }
            Column {
                TextButton(text = if (visible) "slideOut" else "slideIn") {
                    visible = !visible
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = slideIn {
                        IntOffset(-it.width, -it.height)
                    },
                    exit = slideOut {
                        IntOffset(-it.width, -it.height)
                    }
                ) {
                    ComposeFlight()
                }
            }
        },
        //expand in / shrink out
        {
            var visible by remember {
                mutableStateOf(false)
            }
            Column {
                TextButton(text = if (visible) "shrink" else "expand") {
                    visible = !visible
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = expandIn {
                        IntSize.Zero
                    },
                    exit = shrinkOut {
                        IntSize.Zero
                    }
                ) {
                    ComposeFlight()
                }
            }
        }
    )
}

@Composable
private fun Grid(vararg content: @Composable () -> Unit) {
    val rowCount = if (content.size % 2 == 0) content.size / 2 else content.size / 2 + 1
    Column {
        for (i in 0 until rowCount) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    content[i * 2].invoke()
                }
                Spacer(modifier = Modifier.size(8.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (i * 2 + 1 < content.size) {
                        content[i * 2 + 1].invoke()
                    }
                }
            }
        }
    }
}

@Composable
private fun ComposeFlight() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Blue.color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Compose",
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.body1
        )
    }
}
