package com.coding.isuru.repository.remote

import com.coding.isuru.repository.model.Post
import retrofit2.Response
import javax.inject.Inject

class BackendAPIClient (private val backendAPI: BackendAPI){

    suspend fun getPost(): Response<Post> {
        return backendAPI.getPost()
    }

}