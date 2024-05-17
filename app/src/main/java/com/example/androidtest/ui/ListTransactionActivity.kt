package com.example.androidtest.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.MyApp
import com.example.androidtest.database.UserDao
import com.example.androidtest.databinding.ActivityListTransactionBinding
import com.example.androidtest.entity.EntityContainer
import com.example.androidtest.entity.EntityKomoditas
import com.example.androidtest.ui.adapter.EntityContainerAdapter
import com.example.androidtest.ui.adapter.EntityKomoditasAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTransactionActivity : AppCompatActivity() {

    private val binding: ActivityListTransactionBinding by lazy {
        ActivityListTransactionBinding.inflate(layoutInflater)
    }

    private val komoditasAdapter: EntityKomoditasAdapter by lazy { EntityKomoditasAdapter() }
    private val containerAdapter: EntityContainerAdapter by lazy { EntityContainerAdapter() }

    private val transactionDao: UserDao by lazy { MyApp.database.userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val transaction = intent.getIntExtra(TRANSACTION, 0)

        if(transaction == 1) {
            binding.rvTransaction.apply {
                layoutManager = LinearLayoutManager(this@ListTransactionActivity)
                adapter = containerAdapter
            }

            transactionDao.getAllContainer().observe(this) {
                if(it.isEmpty()) {
                    binding.apply {
                        rvTransaction.visibility = View.GONE
                        tvPlaceholder.text = "Data tersimpan untuk container, kosong.."
                        tvPlaceholder.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        rvTransaction.visibility = View.VISIBLE
                        tvPlaceholder.visibility = View.GONE
                    }
                }

                containerAdapter.submitList(it)

            }
            containerAdapter.setOnItemClickContainer(object : EntityContainerAdapter.OnItemClickContainer  {
                override fun onItemclickContainer(entity: EntityContainer) {
                    showOptionsDialogContainer(entity)
                }

            })
            binding.btnSave.setOnClickListener {
                startActivity(Intent(this, SaveDataActivity::class.java).putExtra(SaveDataActivity.WHAT_TRANSACTION, 1))
                finish()
            }
        } else {
            binding.rvTransaction.apply {
                layoutManager = LinearLayoutManager(this@ListTransactionActivity)
                adapter = komoditasAdapter
            }
            transactionDao.getAllKomoditas().observe(this) {
                if(it.isEmpty()) {
                    binding.apply {
                        rvTransaction.visibility = View.GONE
                        tvPlaceholder.text = "Data tersimpan untuk komoditas, kosong.."
                        tvPlaceholder.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        rvTransaction.visibility = View.VISIBLE
                        tvPlaceholder.visibility = View.GONE
                    }
                }
                komoditasAdapter.submitList(it)
            }

            komoditasAdapter.setOnItemClickKomoditas(object : EntityKomoditasAdapter.OnItemClickKomoditas {
                override fun onItemClickKomoditas(entity: EntityKomoditas) {
                    showOptionsDialogKomoditas(entity)
                }

            })
            binding.btnSave.setOnClickListener {
                startActivity(Intent(this, SaveDataActivity::class.java).putExtra(SaveDataActivity.WHAT_TRANSACTION, 2))
            }
        }


    }

    private fun showOptionsDialogContainer(entity: EntityContainer) {
        val options = arrayOf("Edit", "Delete")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an option")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    startActivity(Intent(this, EditDataActivity::class.java)
                        .putExtra(EditDataActivity.TRANSACTION, 1)
                        .putExtra(EditDataActivity.ID, entity.id)
                        .putExtra(EditDataActivity.NAMA, entity.nama)
                        .putExtra(EditDataActivity.JENIS, entity.jenis)
                    )
                }
                1 -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val container = EntityContainer()
                        container.id = entity.id
                        transactionDao.deleteContainer(container)
                    }

                }
            }
        }
        builder.show()
    }

    private fun showOptionsDialogKomoditas(entity: EntityKomoditas) {
        val options = arrayOf("Edit", "Delete")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an option")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    startActivity(Intent(this, EditDataActivity::class.java)
                        .putExtra(EditDataActivity.TRANSACTION, 2)
                        .putExtra(EditDataActivity.ID, entity.id)
                        .putExtra(EditDataActivity.NAMA, entity.nama)
                        .putExtra(EditDataActivity.JENIS, entity.jenis)
                    )
                }
                1 -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val komoditas = EntityKomoditas()
                        komoditas.id = entity.id
                        transactionDao.deleteKomoditas(komoditas)
                    }

                }
            }
        }
        builder.show()
    }

    companion object {
        const val TRANSACTION = "isKomoditas"
    }
}