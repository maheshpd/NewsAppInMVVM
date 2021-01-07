package com.createsapp.newsappinmvvm.model


import androidx.annotation.Keep
import com.createsapp.newsappinmvvm.model.Article

@Keep
data class NewsResponse(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)