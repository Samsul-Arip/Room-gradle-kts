package com.example.androidtest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtest.R
import com.example.androidtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTransaction.setOnClickListener {
            startActivity(Intent(this, InputTransactionActivity::class.java))
        }

        binding.btnContainer.setOnClickListener {
            startActivity(Intent(this, ListTransactionActivity::class.java)
                .putExtra(ListTransactionActivity.TRANSACTION, 1))
        }

        binding.btnKomoditas.setOnClickListener {
            startActivity(Intent(this, ListTransactionActivity::class.java)
                .putExtra(ListTransactionActivity.TRANSACTION, 2))
        }

    }
}