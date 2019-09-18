package com.cnx.newsfeed.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {
 /*   @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindThemeViewModel(viewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetsViewModel::class)
    abstract fun bindLegoSetsViewModel(viewModel: LegoSetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetViewModel::class)
    abstract fun bindLegoSetViewModel(viewModel: LegoSetViewModel): ViewModel*/

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
