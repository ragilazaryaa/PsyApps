package com.example.psyapps.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Log.d("abcde" , "null : $sharedPreferences")
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this@ProfileActivity, WelcomeActivity::class.java)
            sharedPreferences.clearData()
            startActivity(intent)
            finish()
        }
        binding.ivBackProfile.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}