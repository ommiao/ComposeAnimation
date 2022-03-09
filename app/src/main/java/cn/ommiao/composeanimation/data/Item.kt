package cn.ommiao.composeanimation.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color

data class Item(
    val color: Color,
    val title: String,
    val content: String
)

val Red = Item(Color(0xffef5350), "Red", "red red red")
val Pink = Item(Color(0xffec407a), "Pink", "pink pink pink")
val Purple = Item(Color(0xffab47bc), "Purple", "purple purple purple")
val DeepPurple = Item(Color(0xff7e57c2), "Deep Purple", "deep purple deep purple deep purple")
val Indigo = Item(Color(0xff5c6bc0), "Indigo", "indigo indigo indigo")
val Blue = Item(Color(0xff42a5f5), "Blue", "blue blue blue")
val LightBlue = Item(Color(0xff29b6f6), "Light Blue", "light blue light blue light blue")
val Cyan = Item(Color(0xff26c6da), "Cyan", "cyan cyan cyan")
val Teal = Item(Color(0xff26a69a), "Teal", "teal teal teal")
val Green = Item(Color(0xff66bb6a), "Green", "green green green")
val LightGreen = Item(Color(0xff9ccc65), "Light Green", "light green light green light green")
val Lime = Item(Color(0xffd4e157), "Lime", "lime lime lime")

val itemsList = mutableStateListOf(
    Red,
    Pink,
    Purple,
    DeepPurple,
    Indigo,
    Blue,
    LightBlue,
    Cyan,
    Teal,
    Green,
    LightGreen,
    Lime,
)
