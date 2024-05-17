package com.example.androidtest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidtest.R
import com.example.androidtest.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity() {

    private val binding: ActivityTransactionBinding by lazy { ActivityTransactionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



    }

}