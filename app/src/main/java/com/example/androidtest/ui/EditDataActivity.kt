package com.example.androidtest.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidtest.MyApp
import com.example.androidtest.R
import com.example.androidtest.database.UserDao
import com.example.androidtest.databinding.ActivityEditDataBinding
import com.example.androidtest.entity.EntityContainer
import com.example.androidtest.entity.EntityKomoditas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditDataActivity : AppCompatActivity() {
    private val binding: ActivityEditDataBinding by lazy { ActivityEditDataBinding.inflate(layoutInflater) }
    private val transactionDao: UserDao by lazy { MyApp.database.userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val transaction = intent.getIntExtra(TRANSACTION, 0)
        val id = intent.getIntExtra(ID, 0)
        val nama = intent.getStringExtra(NAMA)
        val jenis = intent.getStringExtra(JENIS)

        binding.edtNama.setText("$nama")
        binding.edtJenis.setText("$nama")

        if(transaction == 1) {
            binding.tvTitle.text = "Isi menu container"
            binding.edtNama.setHint("Isi nama container")
            binding.edtJenis.setHint("Isi jenis container")
            binding.btnSave.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    val nama = binding.edtNama.text.toString()
                    val jenis = binding.edtJenis.text.toString()
                    val newContainer = EntityContainer()
                    newContainer.id = id
                    newContainer.nama = nama
                    newContainer.jenis = jenis
                    transactionDao.updateContainer(newContainer)
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
                    val newKomoditas = EntityKomoditas()
                    newKomoditas.id = id
                    newKomoditas.nama = asal
                    newKomoditas.jenis = jenis
                    transactionDao.updateKomoditas(newKomoditas)
                }
                finish()
            }
        }
    }


    companion object {
        const val TRANSACTION = "isContainer"
        const val ID = "id"
        const val NAMA = "nama"
        const val JENIS = "jenis"
    }


}