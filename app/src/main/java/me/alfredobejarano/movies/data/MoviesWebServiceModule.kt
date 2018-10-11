package me.alfredobejarano.movies.data

import dagger.Module
import dagger.Provides
import me.alfredobejarano.movies.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module that defines how a [MoviesWebServiceModule] implementation
 * is going to be provided when injecting.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 12:25 AM
 * @version 1.0
 */
@Module
class MoviesWebServiceModule {
    /**
     * Defines how a [MoviesWebService] implementation must be provided when injected.
     * @return [MoviesWebService] implementation for injection.
     */
    @Provides
    @Singleton
    fun provideMoviesWebService(): MoviesWebService {
        // Create a new OkHttpClient with a timeout of 10 seconds.
        val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
        // If the app is in debug mode, use a LoggingInterceptor.
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }
        // Create the retrofit object for the web service.
        val retrofit = Retrofit.Builder()
                .client(client.build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        // Return the MoviesWebService implementation created by Retrofit.
        return retrofit.create(MoviesWebService::class.java)
    }

}