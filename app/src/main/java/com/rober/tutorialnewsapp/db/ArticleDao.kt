package com.rober.tutorialnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rober.tutorialnewsapp.Article

@Dao
interface AritcleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}