package me.alfredobejarano.movies.data

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.Component
import me.alfredobejarano.movies.viewmodel.MoviesViewModelProviderFactory
import me.alfredobejarano.movies.viewmodel.MoviesViewModelProviderFactoryModule
import javax.inject.Singleton

/**
 * Module class that defines all the providers for the app dependency injection.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 12:38 AM
 */
@Singleton
@Component(modules = [MoviesWebServiceModule::class, MoviesRepositoryModule::class,
    MoviesViewModelProviderFactoryModule::class])
interface ApplicationComponent {
    /**
     * Injects annotated parameters with @Inject for a ViewModel class.
     * @param viewModel The activity ready for injection.
     */
    fun inject(viewModel: ViewModel)

    /**
     * Injects annotated parameters with @Inject for an activity.
     * @param activity The activity ready for injection.
     */
    fun inject(activity: AppCompatActivity)

    /**
     * Provides injection for a [MoviesWebService] implementation.
     * @return Built [MoviesWebService] implementation that contains API definitions.
     */
    fun provideMoviesWebService(): MoviesWebService

    fun provideMovieDao(): MovieDao

    fun provideMoviesRepository(): MoviesRepository

    fun provideMoviesViewModelProviderFactoryModule(): MoviesViewModelProviderFactory
}