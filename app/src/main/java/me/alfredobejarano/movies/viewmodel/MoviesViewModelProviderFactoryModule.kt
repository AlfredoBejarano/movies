package me.alfredobejarano.movies.viewmodel

import dagger.Module
import dagger.Provides
import me.alfredobejarano.movies.data.MoviesRepository
import javax.inject.Singleton

/**
 * Module that defines how a [MoviesViewModelProviderFactory] implementation
 * is going to be provided when injecting.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 03:59 AM
 * @version 1.0
 */
@Module
class MoviesViewModelProviderFactoryModule {
    /**
     * Defines how a [MoviesViewModelProviderFactory] implementation must be provided when injected.
     * @return [MoviesViewModelProviderFactory] implementation for injection.
     */
    @Provides
    @Singleton
    fun provideMoviesViewModelProviderFactory(repo: MoviesRepository):
            MoviesViewModelProviderFactory = MoviesViewModelProviderFactory(repo)
}