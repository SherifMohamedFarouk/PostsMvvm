package com.sherif.postsmvvm.ui.post

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sherif.postsmvvm.R
import com.sherif.postsmvvm.base.BaseViewModel
import com.sherif.postsmvvm.model.Post
import com.sherif.postsmvvm.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel:BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter : PostListAdapter = PostListAdapter()
    val loadingVisibility :MutableLiveData<Int> = MutableLiveData()
    val errorMessage : MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable
    init {
        loadPosts()
    }

    private fun loadPosts(){
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{onRetrievePostListStart()}
            .doOnTerminate{onRetrievePostListFinish()}
            .subscribe(
                {result -> onRetrievePostListSuccess(result)
                Log.v("heyyy", result.toString())}
                ,{onRetrievePostListError()}
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null

    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)

    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}