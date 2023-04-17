package com.example.hw53

data class PixaModel(
    var hits: ArrayList<ImageModel>
)

data class ImageModel(
    var largeImageURL: String
)