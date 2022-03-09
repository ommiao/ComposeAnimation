package cn.ommiao.composeanimation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.ui.theme.LargeSpace
import cn.ommiao.composeanimation.ui.theme.MediumSpace

@Composable
fun ExampleContainer(title: String, content: @Composable ColumnScope.() -> Unit) {
    var isExpand by remember {
        mutableStateOf(false)
    }
    Surface {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .clickable { isExpand = !isExpand }
                    .fillMaxWidth()
                    .padding(vertical = LargeSpace, horizontal = MediumSpace),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, style = MaterialTheme.typography.subtitle1)
                val arrowRotate by animateFloatAsState(targetValue = if (isExpand) 0f else -90f)
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "dropdown-arrow",
                    modifier = Modifier.rotate(arrowRotate)
                )
            }
            AnimatedVisibility(
                visible = isExpand,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Divider(startIndent = MediumSpace, thickness = 0.7.dp)
                Column(modifier = Modifier.fillMaxWidth().padding(MediumSpace)) {
                    content()
                }
            }
            Divider()
        }
    }
}
