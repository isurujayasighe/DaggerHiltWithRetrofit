package com.coding.isuru.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.coding.isuru.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = MainActivity::class.simpleName
    }

    private lateinit var viewModel: MainActivityViewModel

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainBinding.btn.setOnClickListener {
            mainBinding.indicator.visibility = View.VISIBLE
            viewModel.getPost()
        }

        viewModel.post.observe(this,{
            Log.d(TAG, "Post $it")
        })

        viewModel.isLoading.observe(this,{
            if(it){
                mainBinding.indicator.visibility = View.VISIBLE
            }else{
                mainBinding.indicator.visibility = View.GONE
            }
        })

    }
}


class UploadWorks(appContext: Context, workerParameters: WorkerParameters):Worker(appContext,workerParameters){
    override fun doWork(): Result {
        return Result.success()
    }
}
