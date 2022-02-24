package com.example.ygo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.ygo.ui.theme.Purple700
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DetailPage(card: Card) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.apply {
            setStatusBarColor(color = Purple700)
        }
    }
    val viewModel: CardViewModel = viewModel()
    Scaffold(Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = card.name) }, navigationIcon = {
            IconButton(onClick = {
                viewModel.showDetailPage.postValue(false)
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)) {
            Box(Modifier.padding(top = 20.dp)) {
                Image(
                    painter = rememberImagePainter(
                        data = card.card_images[0].image_url,
                        builder = {
                            placeholder(R.drawable.ygo_bg)
                            crossfade(true)
                        }), contentDescription = "", modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = card.name, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
            card.level?.let { Text(text = "level:$it") }
            card.attribute?.let { 
                CardAttribute(attr = it)
            }
            card.race?.let { Text(text = it) }
            Text(text = "type:${card.type}")
            Text(text = card.desc)
        }
    }
}