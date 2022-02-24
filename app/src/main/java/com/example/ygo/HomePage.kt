package com.example.ygo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.airbnb.lottie.compose.*
import com.example.ygo.ui.theme.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomePage() {
    val viewModel: CardViewModel = viewModel()
    val cardList = viewModel.curCardList.observeAsState()
    val uiState = viewModel.uiState.observeAsState()
    val showDetail = viewModel.showDetailPage.observeAsState()
    val systemUiController = rememberSystemUiController()
    if(!showDetail.value!!){
        systemUiController.apply {
            setStatusBarColor(color = Color.White, darkIcons = true)
        }
    }
    Column(Modifier.fillMaxSize()) {
        SearchField()
        when (uiState.value) {
            CardViewModel.UiState.Empty -> {
                Empty()
            }
            CardViewModel.UiState.Loading -> {
                Loading()
            }
            CardViewModel.UiState.Error -> {
                Error()
            }
            CardViewModel.UiState.Complete -> {
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    cardList.value?.forEach {
                        item {
                            CardItem(card = it)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField() {
    val focus = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel: CardViewModel = viewModel()
    var value by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.CenterEnd
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Search card in English") },
            value = value,
            onValueChange = { value = it },
            singleLine = true,
            keyboardActions = KeyboardActions {
                if(value.isNotEmpty()){
                    viewModel.getCardByName(value)
                    keyboardController?.hide()
                    focus.clearFocus()
                }
            }, textStyle = TextStyle(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = {
            if(value.isNotEmpty()){
                viewModel.getCardByName(value)
                keyboardController?.hide()
                focus.clearFocus()
            }
        }) {
            Icon(Icons.Default.Search, "")
        }
    }
}

@Composable
fun CardItem(card: Card) {
    val viewModel:CardViewModel = viewModel()
    Card(modifier = Modifier
        .clickable {
            viewModel.curCard = card
            viewModel.showDetailPage.postValue(true)
        }
        .fillMaxWidth(), elevation = 8.dp) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = card.card_images[0].image_url_small, builder = {
                    placeholder(R.drawable.ygo_bg)
                    crossfade(true)
                }),
                contentDescription = "cardImage",
                modifier = Modifier.size(70.dp)
            )

            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = card.name,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                FlowRow(Modifier.fillMaxWidth()) {
                    Text(
                        text = card.type + " ",
                        color = getColorByCardType(card.type.lowercase(Locale.ROOT)),
                        fontSize = 14.sp
                    )
                    Text(text = ((card.attribute ?: ("" + " "))), fontSize = 14.sp)
                    Text(text = card.race + " ", fontSize = 14.sp)
                    Text(text = "${card.atk}/${card.def}", fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun Empty() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
    val progress by animateLottieCompositionAsState(
        composition = composition,
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(composition = composition, progress = progress)
            Text(text = "Search something...")
        }
    }
}

@Composable
fun Loading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(composition = composition, progress = progress)
    }
}

@Composable
fun Error() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
    val progress by animateLottieCompositionAsState(composition = composition)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(composition = composition, progress = progress)
            Text(text = "Oops,Something got error...")
        }
    }
}


