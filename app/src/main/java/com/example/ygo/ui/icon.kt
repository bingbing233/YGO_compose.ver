package com.example.ygo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ygo.R

@Preview
@Composable
fun Icon() {
    Box(Modifier.size(100.dp)) {
        Image(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = "",Modifier.fillMaxSize())
    }
}

