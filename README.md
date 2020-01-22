# News-Feed
An app which fetches news from an open source news api, having offline persistence and room pagination.


# ScreenShots

<img src = "https://github.com/kanch231004/News-Feed/blob/master/screenshots/NewsList%20Page.jpg" width = 350 /> <img src = "https://github.com/kanch231004/News-Feed/blob/master/screenshots/News%20Detail%20Page.jpg" width = 350/>

# Tech-Stack

* __Retrofit__ : For Network calls
* __Architecture__ : MVVM
* __Coroutines__ for background operations like fetching network response
* __Room database__ : For offline persistence and Paging Library
* __Live Data__ : To notify view for change
* __Dagger__ : For dependency injection
* __Language__ : Kotlin

# Architecture Diagram
This application strictly follows the below architecture 

<img src = "https://github.com/kanch231004/News-Feed/blob/master/screenshots/Architecture.png" width = 450 />

# API Key
Get your api key from here https://newsapi.org/docs/authentication
