package com.example.theuniverseapp.apod.utils

import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.apod.domain.model.ApodModel

fun ApodDto.toApodModel():ApodModel=
    ApodModel(
        copyright = this.copyright,
        date = this.date,
        explanation = this.explanation,
        hdurl = this.hdurl,
        title = this.title,
        url = this.url
    )