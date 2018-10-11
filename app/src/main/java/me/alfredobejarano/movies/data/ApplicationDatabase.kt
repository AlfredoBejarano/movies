package me.alfredobejarano.movies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.alfredobejarano.movies.BuildConfig

/**
 * Abstract class that defines the access for the application database.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 01:41 PM
 * @version 1.0
 */
@Database(entities = [Movie::class], version = BuildConfig.VERSION_CODE, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    /**
     * Accessor for the database [MovieDao] object.
     * @return A [MovieDao] reference.
     */
    abstract fun movieDao(): MovieDao

    // Singleton usage of the app database.
    companion object {
        // Singleton initialization.
        @Volatile
        private var instance: ApplicationDatabase? = null

        /**
         * Allows access to the local app database class.
         * @return an [ApplicationDatabase] object, providing access to the entities dao objects.
         */
        fun instance(ctx: Context): ApplicationDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(ctx).also { instance = it }
        }

        /**
         * Builds the database instance for the app.
         * @param ctx The context starting the database access.
         */
        private fun buildDatabase(ctx: Context): ApplicationDatabase =
                Room.databaseBuilder(ctx, ApplicationDatabase::class.java,
                        "${BuildConfig.APPLICATION_ID}-db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}