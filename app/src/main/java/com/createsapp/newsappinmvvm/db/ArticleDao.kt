package com.createsapp.newsappinmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.createsapp.newsappinmvvm.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}