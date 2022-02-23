package com.example.ygo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ygo.network.CardRepository
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {

    private val TAG = "cardViewModel"
    val curCardList = MutableLiveData<List<Card>>()
    var uiState = MutableLiveData(UiState.Empty)
    var showDetailPage = MutableLiveData(false)
    var curCard: Card? = null

    fun getCardByName(name: String) {
        uiState.postValue(UiState.Loading)
        viewModelScope.launch {
            kotlin.runCatching {
                CardRepository.getCardByName(name)
            }.onSuccess {
                curCardList.postValue(it.data)
                Log.e(TAG, "getCardByName: $it")
                uiState.postValue(UiState.Complete)
            }.onFailure {
                uiState.postValue(UiState.Error)
                Log.e(TAG, "getCardByName: ", it)
            }
        }
    }

    enum class UiState {
        Loading,
        Error,
        Empty,
        Complete,
    }

    enum class Page {
        HomePage,
        DetailPage
    }
}