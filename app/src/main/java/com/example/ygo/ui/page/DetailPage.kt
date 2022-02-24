package com.example.ygo.ui.page

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.ygo.*
import com.example.ygo.R
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
        val state = rememberScrollableState {
            1f
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .scrollable(state, orientation = Orientation.Vertical,),
        ) {
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

            card.level?.let {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    repeat(it) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Star",
                            Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                    card.attribute?.let {
                        Image(
                            painter = painterResource(id = getAttrImageId(it.lowercase())),
                            contentDescription = "attr"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = card.name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp, color = getColorByCardType(card.type.lowercase()))
            )

            Row(Modifier.fillMaxWidth()) {
                card.race?.let { Text(text = "种族:$it") }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "卡片类型:${card.type}")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "卡片描述")
            Text(text = card.desc)
        }
    }
}