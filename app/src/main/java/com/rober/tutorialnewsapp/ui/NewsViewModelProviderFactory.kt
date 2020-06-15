package com.rober.tutorialnewsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rober.tutorialnewsapp.repository.NewsRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class NewsViewModelProviderFactory(
    val app: Application,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(app, newsRepository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}