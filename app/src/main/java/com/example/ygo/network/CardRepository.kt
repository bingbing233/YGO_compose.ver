package com.example.ygo.network

import com.example.ygo.CardResult

object CardRepository {

    private val api: CardApi = CardApi.getInstance()

   suspend fun getCardByName(name:String): CardResult {
       return api.getCardByName(name)
    }
}