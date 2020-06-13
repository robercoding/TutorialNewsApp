package com.rober.tutorialnewsapp.models

import com.rober.tutorialnewsapp.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)