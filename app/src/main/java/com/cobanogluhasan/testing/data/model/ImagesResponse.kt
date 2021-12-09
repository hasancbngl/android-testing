package com.cobanogluhasan.testing.data.model

import java.io.Serializable

data class ImagesResponse(
    val hits: List<ImageItem>,
    val total: Int,
    val totalHits: Int
) : Serializable
