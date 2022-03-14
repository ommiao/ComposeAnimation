package cn.ommiao.composeanimation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.data.Green
import cn.ommiao.composeanimation.widget.TextButton

@Composable
fun Example5(title: String) {
    ExampleContainer(title = title) {
        //example 5-1, Modifier.animateContentSize
        Example5_1()
    }
}

@Composable
private fun Example5_1() {
    var maxLines by remember {
        mutableStateOf(3)
    }
    Row {
        TextButton(text = "maxLines + 1", endPadding = 16.dp) {
            maxLines += 1
        }
        TextButton(text = "maxLines - 1", endPadding = 16.dp) {
            maxLines = (maxLines - 1).coerceAtLeast(1)
        }
    }
    Surface(color = Green.color) {
        Text(
            text = stringResource(id = R.string.long_text).repeat(10),
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(),
            maxLines = maxLines
        )
    }
}
