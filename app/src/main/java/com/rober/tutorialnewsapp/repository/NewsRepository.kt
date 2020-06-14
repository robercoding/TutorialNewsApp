package com.rober.tutorialnewsapp.repository

import com.rober.tutorialnewsapp.api.RetrofitInstace
import com.rober.tutorialnewsapp.db.ArticleDatabase

class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstace.api.getBreakingews(countryCode, pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstace.api.getAllNews(searchQuery, pageNumber)
}