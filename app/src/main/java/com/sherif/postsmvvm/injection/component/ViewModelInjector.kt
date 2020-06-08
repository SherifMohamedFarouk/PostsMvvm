package com.sherif.postsmvvm.injection.component

import com.sherif.postsmvvm.injection.module.NetworkModule
import com.sherif.postsmvvm.ui.post.PostListViewModel
import com.sherif.postsmvvm.ui.post.PostViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)
    fun inject(postViewModel: PostViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }



}