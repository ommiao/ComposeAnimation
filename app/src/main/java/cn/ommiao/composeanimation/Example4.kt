package cn.ommiao.composeanimation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.Blue
import cn.ommiao.composeanimation.data.Green
import cn.ommiao.composeanimation.data.Pink
import cn.ommiao.composeanimation.widget.TextButton

@Composable
fun Example4(title: String) {
    ExampleContainer(title = title) {
        //example 4-1, simple cross fade
        Example4_1()
    }
}

@Composable
private fun Example4_1() {
    var crossfadeState by remember {
        mutableStateOf(CrossfadeState.LOADING)
    }
    Row {
        CrossfadeState.values().forEach {
            TextButton(text = it.name, endPadding = 16.dp) {
                crossfadeState = it
            }
        }
    }
    Crossfade(
        targetState = crossfadeState,
        animationSpec = tween(2000)
    ) {
        when (it) {
            CrossfadeState.LOADING -> {
                Loading()
            }
            CrossfadeState.SUCCESS -> {
                Success()
            }
            CrossfadeState.ERROR -> {
                Error()
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(CrossfadeState.LOADING.color),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color.White)
            Text(text = "Loading", color = Color.White)
        }
    }
}

@Composable
private fun Success() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(CrossfadeState.SUCCESS.color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Success", color = Color.White)
    }
}

@Composable
private fun Error() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(CrossfadeState.ERROR.color),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = "error",
                tint = Color.White
            )
            Text(text = "Loading", color = Color.White)
        }
    }
}

private enum class CrossfadeState(val color: Color) {
    LOADING(Blue.color),
    SUCCESS(Green.color),
    ERROR(Pink.color),
}
