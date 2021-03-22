package com.example.test_khttp

import android.media.Image

class Movie_Info (
    val movieId: String,
    val name: String,
    val description: String,
    val age: String,
    val images: List<String>,
    val poster: String,
    val tags: List<Map<String, String>>)
{

    override fun toString(): String {
        return "Movie:\n" +
                "movieID: ${this.movieId} ;\n\n" +
                "name: ${this.name} ;\n\n" +
                "description: ${this.description} ;\n\n" +
                "age: ${this.age} ; \n\n" +
                "images: ${this.images} ; \n\n" +
                "poster: ${this.poster} ; \n\n" +
                "tags: ${this.tags}"
    }

}