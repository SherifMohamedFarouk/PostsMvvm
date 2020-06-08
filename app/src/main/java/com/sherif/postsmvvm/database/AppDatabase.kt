package com.sherif.postsmvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sherif.postsmvvm.model.Post
import com.sherif.postsmvvm.model.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}