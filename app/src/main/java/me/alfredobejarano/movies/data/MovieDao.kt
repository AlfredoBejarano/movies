package me.alfredobejarano.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

/**
 * Interface that defines the Database operations for the [Movie] entity.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 01:55 AM
 */
@Dao
interface MovieDao {
    /**
     * Inserts a movie into the database, if it already exists it gets replaced.
     * @param movie The movie to be inserted or updated in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(movie: Movie)

    /**
     * Fetches all the records from the local database.
     * @return [Observable] object reporting the records.
     */
    @Query("SELECT * FROM movies_table ORDER BY pk DESC")
    fun getAll(): Observable<List<Movie>?>
}