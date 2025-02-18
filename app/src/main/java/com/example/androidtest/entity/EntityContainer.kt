package com.example.androidtest.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "container_tbl")
data class EntityContainer (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "nama") var nama: String = "",
    @ColumnInfo(name = "jenis") var jenis: String = ""
)