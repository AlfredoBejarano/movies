package me.alfredobejarano.movies.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Model class that represents the base node from the movies JSON response,
 * it is a data named key wrapping the list of movies.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 1:03 AM
 * @version 1.0
 */
data class MoviesDataWrapper(
        @Expose
        @SerializedName("data")
        val movies: List<Movie>?)