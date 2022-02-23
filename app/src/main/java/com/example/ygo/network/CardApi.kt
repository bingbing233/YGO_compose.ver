package com.example.ygo.network

import com.example.ygo.CardResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApi {

    companion object{
        val baseUrl =  "https://db.ygoprodeck.com/api/v7/"

        private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        fun getInstance() = retrofit.create(CardApi::class.java)
    }

    //卡名

    @GET("cardinfo.php")
   suspend fun getCardByName(@Query("fname") name:String): CardResult


}