package com.cnx.newsfeed.di


import com.cnx.newsfeed.news.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): NewsListFragment

}
