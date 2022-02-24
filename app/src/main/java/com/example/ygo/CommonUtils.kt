package com.example.ygo

import androidx.compose.ui.graphics.Color
import com.example.ygo.ui.theme.*

fun getColorByCardType(type: String): Color {
    if (type.contains("normal"))
        return normalMonster
    if (type.contains("effect"))
        return effectMonster
    if (type.contains("fusion"))
        return fusionMonster
    if (type.contains("link"))
        return linkMonster
    if (type.contains("xyz"))
        return xyzMonster
    if (type.contains("ritual"))
        return ritualMonster
    if (type.contains("spell"))
        return spell
    if (type.contains("trap"))
        return trap
    return Color.Black
}

fun getColorByCardAttr(attr: String): Color {
    return when (attr) {
        "dark" -> dark
        "water" -> water
        "wind" -> wind
        "fire" -> fire
        "god" -> god
        "earth"-> earth
        else -> {
            dark
        }
    }
}

/**
 * 返回属性对应的汉字
 */
fun attr2Hanzi(attr:String): String {
    return when(attr){
        "dark" -> "闇"
        "water" -> "水"
        "wind" -> "風"
        "fire" -> "炎"
        "god" -> "神"
        "earth"-> "地"
        else -> ""
    }
}

fun getAttrImageId(attr: String):Int{
    return when(attr){
        "dark" -> R.drawable.dark
        "water" -> R.drawable.water
        "wind" -> R.drawable.wind
        "fire" -> R.drawable.fire
        "divine" -> R.drawable.god
        "earth"-> R.drawable.earth
        "light" -> R.drawable.light
        else -> 0
    }
}
