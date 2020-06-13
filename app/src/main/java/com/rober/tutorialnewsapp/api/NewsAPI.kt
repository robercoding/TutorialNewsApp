package com.rober.tutorialnewsapp.api

import com.rober.tutorialnewsapp.models.NewsResponse
import com.rober.tutorialnewsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingews(
        @Query("country") countryCode : String = "us",
        @Query("page") pageNumber : Int = 1,
        @Query("apiKey") apiKey : String = Constants.API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") query : String,
        @Query("page") pageNumber : Int = 1,
        @Query("apiKey") apiKey : String = Constants.API_KEY
    ) : Response<NewsResponse>

}