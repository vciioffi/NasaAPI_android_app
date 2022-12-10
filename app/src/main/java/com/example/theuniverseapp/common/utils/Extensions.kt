package com.example.theuniverseapp.common.utils

import com.example.theuniverseapp.apod.data.db.ApodDb
import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.apod.domain.model.ApodModel

fun ApodDto.toApodModel(): ApodModel =
    ApodModel(
        copyright = this.copyright,
        date = this.date,
        explanation = this.explanation,
        hdurl = this.hdurl,
        title = this.title,
        url = this.url
    )

fun ApodDto.toApodDb(): ApodDb =
    ApodDb(
        copyright = this.copyright,
        date = this.date,
        explanation = this.explanation,
        hdurl = this.hdurl,
        title = this.title,
        url = this.url
    )

fun ApodDb.toApodModel():ApodModel =
    ApodModel(
        copyright = this.copyright,
        date = this.date,
        explanation = this.explanation,
        hdurl = this.hdurl,
        title = this.title,
        url = this.url
    )