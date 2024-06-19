package com.example.psyapps.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.psyapps.databinding.ActivityProfileBinding
import com.example.psyapps.main.WelcomeActivity
import com.example.psyapps.preferences.SharedPreferences

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = SharedPreferences(this)
        val btnLogout = binding.btnLogout
        btnLogout.setOnClickListener {
            sharedPreferences.clearData()
            val intent = Intent(this@ProfileActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}