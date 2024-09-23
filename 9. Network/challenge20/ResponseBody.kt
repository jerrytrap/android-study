package com.sample.doitandroid

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Rss(
    @JacksonXmlProperty(isAttribute = true, localName = "version")
    val version: String,
    @JacksonXmlProperty(localName = "channel")
    val channel: Channel
)

data class Channel(
    @JacksonXmlProperty(localName = "title")
    val title: String,

    @JacksonXmlProperty(localName = "link")
    val link: String,

    @JacksonXmlProperty(localName = "description")
    val description: String,

    @JacksonXmlProperty(localName = "language")
    val language: String,

    @JacksonXmlProperty(localName = "copyright")
    val copyright: String,

    @JacksonXmlProperty(localName = "lastBuildDate")
    val lastBuildDate: String,

    @JacksonXmlProperty(localName = "item")
    val items: ArrayList<Item>
)

data class Item(
    @JacksonXmlProperty(localName = "")
    val no: String,

    @JacksonXmlProperty(localName = "title")
    val title: String,

    @JacksonXmlProperty(localName = "link")
    val link: String,

    @JacksonXmlProperty(localName = "category")
    val category: String,

    @JacksonXmlProperty(localName = "author")
    val author: String,

    @JacksonXmlProperty(localName = "pubDate")
    val pubDate: String,

    @JacksonXmlProperty(localName = "description")
    val description: String,

    @JacksonXmlProperty(isAttribute = true, localName = "content")
    val mediaContent: MediaContent?
) {
    fun toNews() = News(
        title = title,
        description = description,
        imageUrl = mediaContent?.url
    )
}

data class MediaContent(
    @JacksonXmlProperty(localName = "medium")
    val medium: String,
    @JacksonXmlProperty(localName = "url")
    val url: String
)