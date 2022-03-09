package cn.ommiao.composeanimation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.ommiao.composeanimation.ui.theme.MediumSpace

@Composable
fun TextButton(
    text: String,
    topPadding: Dp = MediumSpace,
    bottomPadding: Dp = MediumSpace,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = MediumSpace)
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                onClick?.invoke()
            }
    )
}
