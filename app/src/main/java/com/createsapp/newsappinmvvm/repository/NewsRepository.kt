package com.createsapp.newsappinmvvm.repository

import com.createsapp.newsappinmvvm.api.RetrofitInstance
import com.createsapp.newsappinmvvm.db.ArticelDatabase

class NewsRepository(val db: ArticelDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}