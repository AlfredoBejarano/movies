package me.alfredobejarano.movies.data

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository class that knows where to fetch data, this class serves as the single
 * source of truth for the app.
 */
@Singleton
class MoviesRepository @Inject constructor(private val webService: MoviesWebService,
                                           private val dao: MovieDao) {
    var movies: MutableLiveData<List<Movie>?> = MutableLiveData()
    /**
     * Porperty that holds a reference when the last fetch was made.
     */
    private var lastFetch: Long = Calendar.getInstance().time.time

    /**
     * Retrieves the movie data from the cloud.
     */
    private fun getMoviesFromCloud() = webService.getMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe { response ->
                // Set the value to the LiveData object.
                movies.postValue(response?.movies)
                // Store the retrieved movies in another thread.
                response?.movies?.forEach {
                    dao.insertOrUpdate(it)
                }
            }

    /**
     * Retrieves the movies, if the cache is fresh or there is any cache at all,
     * it uses the local stored data, contrary to this, it proceeds to fetch the
     * data from the cloud.
     */
    fun getMovies(): Disposable? = dao.getAll()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe {
                // Check if the cached results are null or if the cache is old.
                if (it?.isNotEmpty() != true || isCacheOld()) {
                    // If so, retrieve the movies from the cloud.
                    getMoviesFromCloud()
                } else {
                    // If the cached list came with values or the cache is fresh,
                    // post the cached results.
                    movies.postValue(it)
                }
            }

    /**
     * Checks if the cache has expired.
     */
    private fun isCacheOld(): Boolean {
        // Create a date from the last fetch reference.
        val fetchDate = Date()
        fetchDate.time = lastFetch
        // Then, add 10 minutes to the last fetch date.
        val expireDate = Calendar.getInstance()
        expireDate.time = fetchDate
        expireDate.add(Calendar.MINUTE, 10)
        //Check if the current date is after the cache expiration date.
        return Date().after(expireDate.time)
    }
}