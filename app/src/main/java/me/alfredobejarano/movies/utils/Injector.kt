package me.alfredobejarano.movies.utils

import android.app.Application
import me.alfredobejarano.movies.data.ApplicationComponent
import me.alfredobejarano.movies.data.DaggerApplicationComponent
import me.alfredobejarano.movies.data.MoviesRepositoryModule
import me.alfredobejarano.movies.data.MoviesWebServiceModule
import me.alfredobejarano.movies.viewmodel.MoviesViewModelProviderFactoryModule

/**
 * Singleton access to the Dagger component for the app.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 01:19 AM
 * @version 1.0
 */
object Injector {
    private lateinit var mApplication: Application

    fun create(application: Application) {
        mApplication = application
    }

    /**
     * Singleton access for the dagger [ApplicationComponent].
     */
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .moviesRepositoryModule(MoviesRepositoryModule(mApplication))
                .moviesViewModelProviderFactoryModule(MoviesViewModelProviderFactoryModule())
                .moviesWebServiceModule(MoviesWebServiceModule())
                .build()
    }
}