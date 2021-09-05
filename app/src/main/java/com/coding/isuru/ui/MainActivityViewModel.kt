package com.coding.isuru.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coding.isuru.repository.MainRepository
import com.coding.isuru.repository.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val post = MutableLiveData<Post>()

    private val errorMsg = MutableLiveData<String>()

    private val isLoading = MutableLiveData<Boolean>()

    private var job: Job? = null

    fun getPost(){

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getPost()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    isLoading.postValue(false)
                    post.postValue(response.body())
                }else {
                    errorMsg.postValue(response.errorBody().toString())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()

    }

}