package cn.ommiao.composeanimation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.Blue
import cn.ommiao.composeanimation.data.Green
import cn.ommiao.composeanimation.data.Lime
import cn.ommiao.composeanimation.ui.theme.MediumSpace
import cn.ommiao.composeanimation.ui.theme.Spacer
import cn.ommiao.composeanimation.widget.TextButton

@Composable
fun ExampleX(title: String) {
    ExampleContainer(title = title) {
        //example x-1, animated number
        ExampleX_1()

        MediumSpace.Spacer()

        //example x-2, animated size
        ExampleX_2()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ExampleX_1() {
    var count by remember {
        mutableStateOf(0)
    }
    Row {
        TextButton(text = "Add", endPadding = 16.dp) {
            count += 1
        }
        TextButton(text = "Minus", endPadding = 16.dp) {
            count -= 1
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Blue.color),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .border(width = 1.dp, color = Lime.color)
        ) {
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    if (targetState > initialState) {
                        ContentTransform(
                            targetContentEnter = slideInVertically { height -> height } + fadeIn(),
                            initialContentExit = slideOutVertically { height -> -height } + fadeOut(),
                            sizeTransform = SizeTransform(clip = false)
                        )
                    } else {
                        ContentTransform(
                            targetContentEnter = slideInVertically { height -> -height } + fadeIn(),
                            initialContentExit = slideOutVertically { height -> height } + fadeOut(),
                            sizeTransform = SizeTransform(clip = false)
                        )
                    }
                }
            ) { target ->
                Text(
                    text = "$target",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun ExampleX_2() {
    var expanded by remember { mutableStateOf(false) }
    Row {
        TextButton(text = if (expanded) "Collapse" else "Expand") {
            expanded = !expanded
        }
    }
    Surface(color = Green.color) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                ContentTransform(
                    targetContentEnter = fadeIn(animationSpec = tween(200, 200)),
                    initialContentExit = fadeOut(animationSpec = tween(200)),
                    sizeTransform = SizeTransform { initialSize, targetSize ->
                        if (targetState) {
                            keyframes {
                                // Expand horizontally first.
                                IntSize(targetSize.width, initialSize.height) at 200
                                durationMillis = 400
                            }
                        } else {
                            keyframes {
                                // Shrink vertically first.
                                IntSize(initialSize.width, targetSize.height) at 200
                                durationMillis = 400
                            }
                        }
                    }
                )
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Expanded()
            } else {
                ContentIcon()
            }
        }
    }
}

@Composable
fun Expanded() {
    Text(
        text = stringResource(id = R.string.long_text),
        color = Color.White,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ContentIcon() {
    Icon(
        imageVector = Icons.Outlined.Phone,
        contentDescription = "phone",
        tint = Color.White,
        modifier = Modifier.padding(16.dp)
    )
}
