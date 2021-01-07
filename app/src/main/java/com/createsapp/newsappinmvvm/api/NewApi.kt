package com.createsapp.newsappinmvvm.api

import com.createsapp.newsappinmvvm.model.NewsResponse
import com.createsapp.newsappinmvvm.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
            @Query("country")
            countryCode: String = "us",
            @Query("page")
            pageNumber:Int = 1,
            @Query("apiKey")
            apiKey: String = API_KEY

    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
            @Query("q")
            searchQuery: String,
            @Query("page")
            pageNumber:Int = 1,
            @Query("apiKey")
            apiKey: String = API_KEY

    ): Response<NewsResponse>
}