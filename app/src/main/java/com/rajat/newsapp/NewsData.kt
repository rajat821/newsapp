package com.rajat.newsapp

import java.io.Serializable

//data class NewsData(
//    var source : String,
//    var title : String,
//    val description : String,
//    val url : String,
//    val urlToImage : String,
//    val publishedAt : String,
//)


class NewsData : Serializable {

    lateinit var source: String
    lateinit var title: String
    lateinit var description: String
    lateinit var url: String
    lateinit var urlToImage: String
    lateinit var publishedAt: String

    constructor() {}

    constructor(source : String, title: String, description: String, url: String, urlToImage:String,publishedAt:String) {

        this.source = source
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
    }
}