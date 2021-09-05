package com.coding.isuru.repository.remote

import com.coding.isuru.repository.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface BackendAPI {

    @GET("/todos/1")
    suspend fun getPost(): Response<Post>

}