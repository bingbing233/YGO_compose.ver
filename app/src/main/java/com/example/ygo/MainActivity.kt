package com.example.ygo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ygo.HomePage
import com.example.ygo.ui.theme.YGOTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    private val viewModel:CardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGOTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val showDetail = viewModel.showDetailPage.observeAsState()
                    HomePage()
                    AnimatedVisibility(visible = showDetail.value!!) {
                        DetailPage(viewModel.curCard!!)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.showDetailPage.value!!){
            viewModel.showDetailPage.postValue(false)
        }
        else{
            super.onBackPressed()
        }
    }
}
