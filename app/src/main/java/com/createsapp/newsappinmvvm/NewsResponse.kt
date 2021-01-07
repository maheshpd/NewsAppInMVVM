package com.createsapp.newsappinmvvm


import androidx.annotation.Keep

@Keep
data class NewsResponse(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
)