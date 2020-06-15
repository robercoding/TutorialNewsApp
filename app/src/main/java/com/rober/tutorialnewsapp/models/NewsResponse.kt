package com.rober.tutorialnewsapp.models

import com.rober.tutorialnewsapp.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)