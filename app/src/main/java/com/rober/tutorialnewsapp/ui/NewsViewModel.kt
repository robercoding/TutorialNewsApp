package com.rober.tutorialnewsapp.ui

import androidx.lifecycle.ViewModel
import com.rober.tutorialnewsapp.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

}