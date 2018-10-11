package me.alfredobejarano.movies.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Model class that represents a single movie record.
 * @author Alfredo Bejarano
 * @since October 11th, 2018 - 01:02 am
 * @version 1.0
 */
@Entity(tableName = "movies_table")
data class Movie(
        @Expose
        @ColumnInfo(name = "pk")
        @SerializedName("id")
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        @Expose
        @SerializedName("title")
        val title: String?,
        @Expose
        @SerializedName("year")
        val year: Int?,
        @Expose
        @SerializedName("genre")
        val genre: String?,
        @Expose
        @SerializedName("poster")
        val poster: String?,
        /**
         * Gets the sysdate milliseconds where this item was fetched.
         */
        val fetched: Long = Calendar.getInstance().time.time)