package com.sherif.postsmvvm.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherif.postsmvvm.base.BaseViewModel
import com.sherif.postsmvvm.injection.NetworkModule
import com.sherif.postsmvvm.injection.component.DaggerViewModelInjector
import com.sherif.postsmvvm.injection.component.ViewModelInjector
import com.sherif.postsmvvm.network.PostApi
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel:BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi
    val loadingVisibility :MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    private fun loadPosts(){
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{onRetrievePostListStart()}
            .doOnTerminate{onRetrievePostListFinish()}
            .subscribe(
                {onRetrievePostListSuccess()}
                ,{onRetrievePostListError()}
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE

    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}