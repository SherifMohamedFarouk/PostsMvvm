package com.sherif.postsmvvm.base

import androidx.lifecycle.ViewModel
import com.sherif.postsmvvm.injection.NetworkModule
import com.sherif.postsmvvm.injection.component.DaggerViewModelInjector
import com.sherif.postsmvvm.injection.component.ViewModelInjector
import com.sherif.postsmvvm.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {

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