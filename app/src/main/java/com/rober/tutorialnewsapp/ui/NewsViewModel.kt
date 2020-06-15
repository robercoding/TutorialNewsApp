package com.rober.tutorialnewsapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rober.tutorialnewsapp.models.Article
import com.rober.tutorialnewsapp.models.NewsResponse
import com.rober.tutorialnewsapp.repository.NewsRepository
import com.rober.tutorialnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val TAG = "NewsViewModel"

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse : NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse : NewsResponse? = null

    init{
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getSearchNews(searchQuery: String) = viewModelScope.launch {

        searchNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let {newsResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null){
                    println("size null: $breakingNewsResponse")
                    breakingNewsResponse = newsResponse
                    println("size after null: $breakingNewsResponse")
                } else {
                    println("size before old: $breakingNewsResponse")
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = newsResponse.articles
                    oldArticles?.addAll(newArticles)
                    println("size after old: $breakingNewsResponse")
                }
                println("size: $breakingNewsResponse")
                return Resource.Success(breakingNewsResponse ?: newsResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let {newsResponse ->
                searchNewsPage++
                if(searchNewsResponse == null){
                    searchNewsResponse = newsResponse
                }else{
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = newsResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: newsResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}