package com.example.ygo.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ygo.attr2Hanzi
import com.example.ygo.getColorByCardAttr

@Composable
fun CardAttribute(modifier: Modifier = Modifier, attr: String) {
    val bgColor = getColorByCardAttr(attr.lowercase())
    val hanZi = attr2Hanzi(attr.lowercase())
    val fontColor = if (attr == "light" || attr == "god") Color.Black else Color.White
    Box(
        Modifier
            .clip(CircleShape)
            .background(bgColor)
            .padding(horizontal = 6.dp, vertical = 3.dp)
            .shadow(8.dp)
    ) {
        Text(
            text = hanZi, modifier = modifier, color = fontColor
        )
    }

}

@Preview(showBackground = false)
@Composable
fun CardAttrPrev() {
    CardAttribute(attr = "dark")
}