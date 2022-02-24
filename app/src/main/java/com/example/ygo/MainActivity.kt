package com.example.ygo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.ygo.ui.page.DetailPage
import com.example.ygo.ui.page.HomePage
import com.example.ygo.ui.theme.YGOTheme


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
