package me.alfredobejarano.movies.data

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Module that defines how a [MoviesRepositoryModule] implementation
 * is going to be provided when injecting.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 03:59 AM
 * @version 1.0
 */
@Module
class MoviesRepositoryModule(private val ctx: Context) {
    /**
     * Defines how a [MovieDao] implementation must be provided when injected.
     * @return [MovieDao] implementation for injection.
     */
    @Provides
    @Singleton
    fun provideMovieDao(): MovieDao =
            ApplicationDatabase.instance(ctx).movieDao()

    /**
     * Defines how a [MoviesRepository] implementation must be provided when injected.
     * @return [MoviesRepository] implementation for injection.
     */
    @Provides
    @Singleton
    fun provideMoviesRepository(dao: MovieDao, service: MoviesWebService): MoviesRepository =
            MoviesRepository(service, dao)
}