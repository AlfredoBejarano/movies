package me.alfredobejarano.movies.data

import io.reactivex.Observable
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * Interface that contains the API definitions for the app.
 * @author Alfredo Bejarano
 * @since October 10th, 2018 - 12:51 AM
 */
@Singleton
interface MoviesWebService {
    /**
     * Endpoint that fetches a list of movies from the cloud.
     * - Method: GET
     */
    @GET("/api/movies")
    fun getMovies(): Observable<MoviesDataWrapper?>
}