package com.example.androidtest.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_tbl")
data class EntityTransaction(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "jenis_container") var container: String = "",
    @ColumnInfo(name = "jenis_komoditas") var komoditas: String = "",
    @ColumnInfo(name = "jumlah") var jumlah: String = ""
)
