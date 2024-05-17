package com.example.androidtest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidtest.entity.EntityContainer
import com.example.androidtest.entity.EntityKomoditas
import com.example.androidtest.entity.EntityTransaction

@Dao
interface UserDao {
    /**
     * this is komoditas
     */
    @Query("SELECT * FROM komoditas_tbl")
    fun getAllKomoditas(): LiveData<List<EntityKomoditas>>

    @Insert
    fun insertKomoditas(komoditas: EntityKomoditas)

    @Update
    fun updateKomoditas(komoditas: EntityKomoditas)

    @Delete
    fun deleteKomoditas(user: EntityKomoditas)


    /**
     * this is container
     */
    @Query("SELECT * FROM container_tbl")
    fun getAllContainer(): LiveData<List<EntityContainer>>

    @Insert
    fun insertContainer(container: EntityContainer)

    @Update
    fun updateContainer(container: EntityContainer)

    @Delete
    fun deleteContainer(container: EntityContainer)

    /**
     * transaksi
     */
    @Insert
    fun insertTransaction(transaction: EntityTransaction)

}