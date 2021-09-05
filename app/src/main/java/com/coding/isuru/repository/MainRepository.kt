package com.coding.isuru.repository

import com.coding.isuru.repository.model.Post
import com.coding.isuru.repository.remote.BackendAPIClient
import retrofit2.Response

class MainRepository constructor(
    private val backendAPIClient: BackendAPIClient
) {

    suspend fun getPost():Response<Post>{
        return backendAPIClient.getPost()
    }
}