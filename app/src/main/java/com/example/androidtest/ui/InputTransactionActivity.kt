package com.example.androidtest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidtest.MyApp
import com.example.androidtest.R
import com.example.androidtest.database.UserDao
import com.example.androidtest.databinding.ActivityInputTransactionBinding
import com.example.androidtest.entity.EntityContainer
import com.example.androidtest.entity.EntityKomoditas
import com.example.androidtest.entity.EntityTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InputTransactionActivity : AppCompatActivity() {

    private val binding: ActivityInputTransactionBinding by lazy { ActivityInputTransactionBinding.inflate(layoutInflater) }
    private val transactionDao: UserDao by lazy { MyApp.database.userDao() }
    var komoditas: String = ""
    var container: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        transactionDao.getAllKomoditas().observe(this) {
            updateSpinnerKomoditas(it)
        }

        binding.spinContainer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                Log.d("SPIN", selectedItem)
                container = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        transactionDao.getAllContainer().observe(this) {

            updateSpinnerContainer(it)
        }

        binding.spinKomoditas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                Log.d("SPIN", selectedItem)
                komoditas = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val transaction = EntityTransaction()
                transaction.komoditas = komoditas
                transaction.container = container
                transaction.jumlah = binding.edtJenis.text.toString()
                transactionDao.insertTransaction(transaction)
            }

            Toast.makeText(this, "Berhasil mengirim data", Toast.LENGTH_SHORT).show()
        }



    }

    private fun updateSpinnerContainer(entities: List<EntityContainer>) {
        val items = if (entities.isEmpty()) {
            listOf("Container    kosong")
        } else {
            entities.map { it.nama }
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinContainer.adapter = adapter
    }

    private fun updateSpinnerKomoditas(entities: List<EntityKomoditas>) {
        val items = if (entities.isEmpty()) {
            listOf("Komoditas kosong")
        } else {
            entities.map { it.nama }
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinKomoditas.adapter = adapter
    }


}