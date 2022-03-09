package cn.ommiao.composeanimation.data

import androidx.compose.runtime.mutableStateListOf

data class Item(
    val color: String,
    val title: String,
    val content: String
)

val itemsList = mutableStateListOf(
    Item("ef5350", "Red", "red red red"),
    Item("ec407a", "Pink", "pink pink pink"),
    Item("ab47bc", "Purple", "purple purple purple"),
    Item("7e57c2", "Deep Purple", "deep purple deep purple deep purple"),
    Item("5c6bc0", "Indigo", "indigo indigo indigo"),
    Item("42a5f5", "Blue", "blue blue blue"),
    Item("29b6f6", "Light Blue", "light blue light blue light blue"),
    Item("26c6da", "Cyan", "cyan cyan cyan"),
    Item("26a69a", "Teal", "teal teal teal"),
    Item("66bb6a", "Green", "green green green"),
    Item("9ccc65", "Light Green", "light green light green light green"),
    Item("d4e157", "Lime", "lime lime lime"),
)
