package com.example.androidtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtest.entity.EntityContainer
import com.example.androidtest.entity.EntityKomoditas
import com.example.androidtest.entity.EntityTransaction

@Database(entities = [
    EntityKomoditas::class,
    EntityContainer::class,
    EntityTransaction::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}