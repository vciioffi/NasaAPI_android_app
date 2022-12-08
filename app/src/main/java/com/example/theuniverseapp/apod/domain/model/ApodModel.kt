package com.example.theuniverseapp.apod.domain.model


data class ApodModel(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val title: String,
    val url: String
)