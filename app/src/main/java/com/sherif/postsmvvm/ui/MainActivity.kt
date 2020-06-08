package com.sherif.postsmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sherif.postsmvvm.R
import com.sherif.postsmvvm.databinding.ActivityPostListBinding
import com.sherif.postsmvvm.injection.ViewModelFactory
import com.sherif.postsmvvm.ui.post.PostListViewModel




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel
    private  var errorSnackbar : Snackbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage -> if(errorMessage !=null ) showError(errorMessage) else hideError()})
        binding.viewModel = viewModel




    }

    private fun showError(errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root,errorMessage,Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry,viewModel.errorClickListener)
        errorSnackbar?.show()

    }
    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}