package com.sherif.postsmvvm.ui.post

import androidx.lifecycle.MutableLiveData
import com.sherif.postsmvvm.base.BaseViewModel
import com.sherif.postsmvvm.model.Post

class PostViewModel:BaseViewModel() {
    private val postTitle : MutableLiveData<String>  = MutableLiveData()
    private val postBody : MutableLiveData<String>  = MutableLiveData()

    fun bind (post :Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle( ): MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody( ): MutableLiveData<String>{
        return postBody
    }


}