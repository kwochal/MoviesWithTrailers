package com.example.filmy

data class Movie(
    val id: Int,
    val title: String,
    val imageResId: Int,
    val description: String,
    val scenes: List<Int>,
    val actors: List<Pair<String,Int>>,
    val trailers : List<String> = emptyList()
)

