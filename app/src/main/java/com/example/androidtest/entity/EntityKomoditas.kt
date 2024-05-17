package com.example.androidtest.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "komoditas_tbl")
data class EntityKomoditas (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "nama") var nama: String = "",
    @ColumnInfo(name = "jenis") var jenis: String = ""
)