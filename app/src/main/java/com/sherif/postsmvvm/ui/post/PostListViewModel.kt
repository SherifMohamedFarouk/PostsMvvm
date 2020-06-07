package com.sherif.postsmvvm.ui.post

import com.sherif.postsmvvm.base.BaseViewModel
import com.sherif.postsmvvm.injection.NetworkModule
import com.sherif.postsmvvm.injection.component.DaggerViewModelInjector
import com.sherif.postsmvvm.injection.component.ViewModelInjector
import com.sherif.postsmvvm.network.PostApi
import javax.inject.Inject

class PostListViewModel:BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }

}