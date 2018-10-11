package me.alfredobejarano.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.movies.data.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Simple [ViewModelProvider.Factory] subclass that defines how to create
 * a [MoviesViewModel] object.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 03:58 AM
 */
@Singleton
class MoviesViewModelProviderFactory @Inject constructor(private val repo: MoviesRepository)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MoviesViewModel(repo) as T
}