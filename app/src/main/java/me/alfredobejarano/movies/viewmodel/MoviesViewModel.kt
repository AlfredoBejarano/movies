package me.alfredobejarano.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alfredobejarano.movies.data.Movie
import me.alfredobejarano.movies.data.MoviesRepository
import javax.inject.Inject

/**
 * Simple [ViewModel] subclass that fetches the movies for the user.
 */
class MoviesViewModel @Inject constructor(repository: MoviesRepository) : ViewModel() {
    /**
     * [MutableLiveData] object for the UI controllers to observe upon.
     */
    val movies = MutableLiveData<List<Movie>?>()

    init {
        // Observe changes in the repository LiveData object.
        repository.movies.observeForever {
            movies.postValue(it)
        }
        // Fetch the movies from the repository.
        repository.getMovies()
    }
}