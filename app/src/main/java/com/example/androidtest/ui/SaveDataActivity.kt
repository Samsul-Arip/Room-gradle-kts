package com.example.androidtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidtest.entity.EntityKomoditas
import com.example.androidtest.MyApp
import com.example.androidtest.database.UserDao
import com.example.androidtest.databinding.ActivitySaveDataBinding
import com.example.androidtest.entity.EntityContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveDataActivity : AppCompatActivity() {

    private val binding: ActivitySaveDataBinding by lazy { ActivitySaveDataBinding.inflate(layoutInflater) }

    private val transactionDao: UserDao by lazy { MyApp.database.userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val transaction = intent.getIntExtra(WHAT_TRANSACTION, 0)

        if(transaction == 1) {
            binding.tvTitle.text = "Isi menu container"
            binding.edtNama.setHint("Isi nama container")
            binding.edtJenis.setHint("Isi jenis container")

            binding.btnSave.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    val nama = binding.edtNama.text.toString()
                    val jenis = binding.edtJenis.text.toString()
                    val newContainer = EntityContainer(nama = nama, jenis = jenis)
                    transactionDao.insertContainer(newContainer)
                }
                finish()
            }
        } else {
            binding.tvInputSatu.text = "Asal"
            binding.tvTitle.text = "Isi menu komoditas"
            binding.edtNama.setHint("Isi asal Komoditas")
            binding.edtJenis.setHint("Isi jenis Komoditas")

            binding.btnSave.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    val asal = binding.edtNama.text.toString()
                    val jenis = binding.edtJenis.text.toString()
                    val newKomoditas = EntityKomoditas(nama = asal, jenis = jenis)
                    transactionDao.insertKomoditas(newKomoditas)
                }
                finish()
            }
        }

    }


    companion object {
        const val WHAT_TRANSACTION = "whatTransaction"
    }
}