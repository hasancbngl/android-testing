package com.cobanogluhasan.testing.data.model

data class ImagesResponse(
    val hits: List<ImageItem>,
    val total: Int,
    val totalHints: Int
)
