package com.cnx.newsfeed.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NewsApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeIO
