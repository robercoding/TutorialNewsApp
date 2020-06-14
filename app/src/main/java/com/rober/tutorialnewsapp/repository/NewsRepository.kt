package com.rober.tutorialnewsapp.repository

import com.rober.tutorialnewsapp.api.RetrofitInstace
import com.rober.tutorialnewsapp.db.ArticleDatabase
import com.rober.tutorialnewsapp.models.Article

class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstace.api.getBreakingews(countryCode, pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstace.api.getAllNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) : Long = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}